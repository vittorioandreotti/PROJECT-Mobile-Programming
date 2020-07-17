using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Utils;
using XamarinApp.ViewModels;
using XamarinApp.LoadingService;
using Rg.Plugins.Popup.Services;
using XamarinApp.Utils.Interfaces;



namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Profilo : ContentPage
    {
        UtentePreferences utentePreferences = new UtentePreferences();
        public Profilo()
        {
            InitializeComponent();
            Nome.Text = utentePreferences.GetNome();
            Cognome.Text = utentePreferences.GetCognome();
            Email.Text = utentePreferences.GetEmail();
        }

        private void ModificaPassword(object sender, EventArgs e)
        {
            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });

            string NuovaPass = this.NuovaPassword.Text;
            string RepeatPass = this.ConfermaPassword.Text;

            if (! NuovaPass.Equals(RepeatPass)) {
                DisplayAlert("Errore", "Le password devono essere uguali", "OK");
                return;
            }
            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                            .ModificaPassword(NuovaPass, RepeatPass)
                            .ContinueWith((Task<bool> taskModificaPassword) => {
                                taskModificaPassword.Wait();

                                if (taskModificaPassword.Result)
                                {
                                    //Modificata 
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        DisplayAlert("Password modificata", "La password è stata modificta con successo", "OK");
                                        utentePreferences.ClearPreferences();
                                        App.Current.MainPage = new NavigationPage(new ScegliLoginRegistrazione());                                      
                                    });
                                    return;
                                }
                                else
                                {
                                    //NON MODIFICATA
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        DisplayAlert("Errore", "La password NON è stata modificata", "OK");
                                    });
                                }
                            });
        }

        private void CancellaAccount(object sender, EventArgs e)
        {
            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });
             
            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                            .Disiscrizione()
                            .ContinueWith((Task<bool> taskDisiscrizione) => {
                                taskDisiscrizione.Wait();

                                if (taskDisiscrizione.Result)
                                {
                                    //Cancellato 
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        DisplayAlert("Cancellazione avvenuta", "Cancellazione account avvenuta con successo", "OK");
                                        App.Current.MainPage = new ScegliLoginRegistrazione();
                                    });
                                }
                                else
                                {
                                    //NON CANCELLATO
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        DisplayAlert("Cancellazione fallita", "Errore nella cancellazione dell'account", "OK");
                                    });
                                }
                            });
        }

        private void LogOut(object sender, EventArgs e)
        {
            utentePreferences.ClearPreferences();
            App.Current.MainPage = new NavigationPage(new ScegliLoginRegistrazione());
            return;
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