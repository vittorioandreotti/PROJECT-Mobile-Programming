using Rg.Plugins.Popup.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.LoadingService;
using XamarinApp.Utils;
using XamarinApp.Utils.Interfaces;
using XamarinApp.ViewModels;

namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Login : ContentPage
    {
        private IFirebaseAuth firebaseAuth;
        private UtentePreferences utentePreferences;

        public Login()
        {
            firebaseAuth = DependencyService.Get<IFirebaseAuth>();
            utentePreferences = new UtentePreferences();

            var vm = new LoginViewModel(firebaseAuth);
            this.BindingContext = vm;
            vm.Login = LoginAction;
            vm.DisplayInvalidLoginPrompt += () => DisplayAlert("Errore", "Inserire una mail valida e una password.", "OK");
            InitializeComponent();
        }

        public void LoginAction(string Email, string Password)
        {
            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });
            firebaseAuth
                .LoginWithEmailPassword(Email, Password)
                .ContinueWith( ( Task<string> taskLogin ) =>
                {
                    taskLogin.Wait();
                    string Token = taskLogin.Result;

                    if (Token != "")
                    {
                        utentePreferences.SetAuthToken(Token);
                        utentePreferences.SetLoginUsername(Email);
                        utentePreferences.SetLoginPassword(Password);

                        FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();

                        firebaseFunction
                            .IsUserInitialized()
                            .ContinueWith((Task<bool> taskIsUserInitialized) => {
                                taskIsUserInitialized.Wait();

                                if(taskIsUserInitialized.Result)
                                {
                                    // Inizializzato
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                    });
                                    App.Current.MainPage = new NavigationDrawer();
                                }
                                else
                                {
                                    // Non inizializzato
                                    Device.BeginInvokeOnMainThread(() => {
                                        StopLoading();
                                        App.Current.MainPage = new NavigationPage(new ScegliProprietarioAffittuario());
                                    });
                                }
                            });

                    }
                    else
                    {
                        Device.BeginInvokeOnMainThread(() => {
                            StopLoading();
                            DisplayAlert("Authentication Fallita", "E-mail o password errate.", "OK");
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