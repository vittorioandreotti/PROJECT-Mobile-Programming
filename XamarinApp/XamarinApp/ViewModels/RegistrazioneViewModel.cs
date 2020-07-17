using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Input;
using Xamarin.Forms;
using System.Text;
using XamarinApp.Utils;
using System.Threading.Tasks;
using XamarinApp.Utils.Interfaces;
using Xamarin.Essentials;
using XamarinApp.LoadingService;
using Rg.Plugins.Popup.Services;
using XamarinApp.Pages;

namespace XamarinApp.ViewModels
{
    class RegistrazioneViewModel : INotifyPropertyChanged
    {
        public Action DisplayInvalidLoginPrompt;
        public Action DisplayInvalidPasswordPrompt;
        public Action DisplayRegistrazioneFallita;

        public event PropertyChangedEventHandler PropertyChanged = delegate { };
        public ICommand SubmitCommand { protected set; get; }
        private IFirebaseAuth firebaseAuth;


        public RegistrazioneViewModel()
        {
            SubmitCommand = new Command(OnSubmit);
            firebaseAuth = DependencyService.Get<IFirebaseAuth>();
        }

        private string nome;
        public string Nome
        {
            get { return nome; }
            set
            {
                nome = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Nome"));
            }
        }

        private string cognome;
        public string Cognome
        {
            get { return cognome; }
            set
            {
                cognome = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Cognome"));
            }
        }

        private string email;
        public string Email
        {
            get { return email; }
            set
            {
                email = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Email"));
            }
        }

        private string password;
        public string Password
        {
            get { return password; }
            set
            {
                password = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Password"));
            }
        }

        private string conferma_password;
        public string ConfermaPassword
        {
            get { return conferma_password; }
            set
            {
                conferma_password = value;
                PropertyChanged(this, new PropertyChangedEventArgs("ConfermaPassword"));
            }
        }

        public void OnSubmit()
        {
            if (Nome.Length == 0 || Cognome.Length == 0 || email.Length == 0 || password.Length == 0 || conferma_password.Length == 0)
            {
                DisplayInvalidLoginPrompt();
                return;
            }

            if(!password.Equals(conferma_password))
            {
                DisplayInvalidPasswordPrompt();
                return;
            }

            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });

            UtentePreferences utentePreferences = new UtentePreferences();

            firebaseAuth
                .RegisterWithEmailAndPassword(email, password)
                .ContinueWith( ( Task<string> taskRegistrazione ) => {
                    taskRegistrazione.Wait();
                    string token = taskRegistrazione.Result;

                    if (token != "")
                    {
                        utentePreferences.SetAuthToken(token);
                        utentePreferences.SetEmail(Email);
                        utentePreferences.SetLoginUsername(Email);
                        utentePreferences.SetLoginPassword(Password);

                        FirebaseFunctionHelper functionHelper = new FirebaseFunctionHelper();

                        functionHelper
                            .RegistraUtente(Nome, Cognome)
                            .ContinueWith((Task<bool> taskRegistraUtente) =>
                            {
                                taskRegistraUtente.Wait();
                                if (taskRegistraUtente.Result)
                                {
                                    // Registrazione avvenuta
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        App.Current.MainPage = new NavigationPage(new ScegliProprietarioAffittuario());
                                    });
                                }
                                else
                                {
                                    // Registrazione fallita
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        DisplayRegistrazioneFallita();
                                    });
                                }
                            });
                    }
                    else
                    {
                        Device.BeginInvokeOnMainThread(() => {
                            StopLoading();
                            DisplayRegistrazioneFallita();
                        });
                    }

                });

        }

        private async void StartLoading()
        {
            LoadingPage loadingPage = new LoadingPage();

            await PopupNavigation.Instance.PushAsync(loadingPage);
        }

        private async void StopLoading()
        {
            await PopupNavigation.Instance.PopAsync();
        }
    }
}
