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
        public Action DisplayErrore;
        public Func<Task> DisplaySuccesso;
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

                        firebaseFunction
                            .InserisciAffittuario()
                            .ContinueWith((Task<bool> taskInserisciAffittuario) => {
                                taskInserisciAffittuario.Wait();

                                if (taskInserisciAffittuario.Result)
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
                        //Codice casa non valido
                        Device.BeginInvokeOnMainThread(() => {
                            StopLoading();
                            DisplayInvalidCodiceCasaPrompt();
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
