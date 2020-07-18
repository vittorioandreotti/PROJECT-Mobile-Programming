using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Input;
using Xamarin.Forms;
using System.Text;
using Xamarin.Forms.Xaml;
using XamarinApp.LoadingService;
using Rg.Plugins.Popup.Services;
using XamarinApp.Utils;
using System.Threading.Tasks;

namespace XamarinApp.ViewModels
{
    class CreazioneCasaViewModel : INotifyPropertyChanged
    {
        public Action DisplayInvalidCreaCasaPrompt;
        public Action DisplayErrore;
        public Func<Task> DisplaySuccesso;
        public event PropertyChangedEventHandler PropertyChanged = delegate { };
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
        private string indirizzo;
        public string Indirizzo
        {
            get { return indirizzo; }
            set
            {
                indirizzo = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Indirizzo"));
            }
        }
        public ICommand SubmitCommand { protected set; get; }
        public CreazioneCasaViewModel()
        {
            SubmitCommand = new Command(OnSubmit);
        }
        public void OnSubmit()
        {
            if (nome.Length == 0 || indirizzo.Length == 0)
            {
                DisplayInvalidCreaCasaPrompt();
                return;
            }

            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });

            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                            .CreaCasa(nome, indirizzo)
                            .ContinueWith((Task<bool> taskCreaCasa) => {
                                taskCreaCasa.Wait();

                                if (taskCreaCasa.Result)
                                {

                                    firebaseFunction
                                        .InserisciProprietario()
                                        .ContinueWith((Task<bool> taskInserisciProprietario) => {
                                            taskInserisciProprietario.Wait();

                                            if (taskInserisciProprietario.Result)
                                            {
                                                Device.BeginInvokeOnMainThread(async () => {
                                                    await StopLoading();
                                                    await DisplaySuccesso();
                                                    App.Current.MainPage = new NavigationDrawer();
                                                });
                                                return;
                                            }
                                            else
                                            {
                                                Device.BeginInvokeOnMainThread(() => {
                                                    StopLoading();
                                                    DisplayErrore();
                                                });
                                            }
                                        });
                                }
                                else
                                {
                                    //casa non creata
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        DisplayInvalidCreaCasaPrompt();
                                    });
                                }
                            });
        }
        private Task StartLoading()
        {
            LoadingPage loadingPage = new LoadingPage();

            return PopupNavigation.Instance.PushAsync(loadingPage);
        }

        private Task StopLoading()
        {
            return PopupNavigation.Instance.PopAsync();
        }
    }
}

