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
                                    //Casa creata   
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        App.Current.MainPage = new NavigationDrawer();
                                    });
                                    return;
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

