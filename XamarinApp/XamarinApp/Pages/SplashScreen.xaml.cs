using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Models.Helpers;
using XamarinApp.Utils;
using XamarinApp.Utils.Interfaces;

namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class SplashScreen : ContentPage
    {
        IFirebaseAuth firebaseAuth;
        UtentePreferences utentePreferences;

        public SplashScreen()
        {
            firebaseAuth = DependencyService.Get<IFirebaseAuth>();
            utentePreferences = new UtentePreferences();
            InitializeComponent();

            // utentePreferences.ClearPreferences();

            if(utentePreferences.Contains(UtentePreferences.KeyLoginUsername) && utentePreferences.Contains(UtentePreferences.KeyLoginPassword) )
            {
                string Email = utentePreferences.GetLoginUsername();
                string Password = utentePreferences.GetLoginPassword();
                firebaseAuth
                    .LoginWithEmailPassword(Email, Password)
                    .ContinueWith(loginUser)
                    .ContinueWith(InitUi);
            }
            else
            {
                // Email e password non settata. Cancello il token di autenticazione obsoleto.
                utentePreferences.SetAuthToken("");
                Task.Factory.StartNew(InitUi);
            }
        }

        private void loginUser(Task<string> task)
        {
            task.Wait();
            string token = task.Result;
            string Email = utentePreferences.GetLoginUsername();
            utentePreferences.SetEmail(Email);
            utentePreferences.SetAuthToken(token);
        }

        private void InitUi(Task task)
        {
            task.Wait();
            InitUi();
        }


        private void InitUi()
        {
            if (utentePreferences.IsLoggedIn() && utentePreferences.IsInitialized())
            {
                // Vai alla homepage
                Task.Delay(1700)
                    .ContinueWith((t) => {
                        App.Current.MainPage = new NavigationDrawer();
                    });
                return;

            }else if (utentePreferences.IsLoggedIn())
            {
                HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("isUserInitialized");

                functionsCaller
                    .Call()
                    .ContinueWith((Task<CloudFunctionResponse> response) =>
                    {
                        response.Wait();
                        CloudFunctionResponse cloudFunctionResponse = response.Result;

                        if (cloudFunctionResponse.HasError)
                        {
                            // L'utente non ha il token di autenticazione valido. Rifaccio il login.
                            Device.BeginInvokeOnMainThread(() => {
                                App.Current.MainPage = new Login();
                            });
                            return;
                        }

                        object objectIsUserInitialized = cloudFunctionResponse.ObjectData;
                        bool isUserInitialized = (bool)cloudFunctionResponse.BooleanData;

                        if (isUserInitialized)
                        {
                            // Logged in and data set -> Redirect to home activity
                            Device.BeginInvokeOnMainThread(() => {
                                App.Current.MainPage = new NavigationDrawer();
                            }); 
                        }
                        else
                        {
                            // Logged in but data is not set -> Redirect to scegli proprietario affittuario
                            Device.BeginInvokeOnMainThread(() => {
                                App.Current.MainPage = new NavigationPage(new ScegliProprietarioAffittuario());
                            });
                        }
                    });
                return;
            }
            else
            {
                Task.Delay(1700).ContinueWith((t) => {
                    Device.BeginInvokeOnMainThread(() => {
                        App.Current.MainPage = new NavigationPage(new ScegliLoginRegistrazione());
                    });
                });

            }

        }
    }
}