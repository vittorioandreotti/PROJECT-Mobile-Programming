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
    class InserisciCodiceCasaViewModel : INotifyPropertyChanged
    {
        public Action DisplayInvalidCodiceCasaPrompt;
        public event PropertyChangedEventHandler PropertyChanged = delegate { };
        private string codice_casa;
        public string CodiceCasa
        {
            get { return codice_casa; }
            set
            {
                codice_casa = value;
                PropertyChanged(this, new PropertyChangedEventArgs("CodiceCasa"));
            }
        }
        
        public ICommand SubmitCommand { protected set; get; }
        public InserisciCodiceCasaViewModel()
        {
            SubmitCommand = new Command(OnSubmit);
        }
        public void OnSubmit()
        {
            if (codice_casa.Length == 0 )
            {
                DisplayInvalidCodiceCasaPrompt();
                return;
            }

            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });
                   
            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                            .PartecipaCasa(codice_casa)
                            .ContinueWith((Task<bool> taskPartecipaCasa) => {
                                taskPartecipaCasa.Wait();

                                if (taskPartecipaCasa.Result)
                                {
                                    //Codice casa valido 
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        App.Current.MainPage = new NavigationDrawer();
                                    });
                                    return;
                                }
                                else
                                {
                                    //Codice casa non valido
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        DisplayInvalidCodiceCasaPrompt();
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
