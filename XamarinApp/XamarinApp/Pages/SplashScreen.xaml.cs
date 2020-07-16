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

namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class SplashScreen : ContentPage
    {
        public SplashScreen()
        {
            InitializeComponent();
            InitUi();
        }

        private void InitUi()
        {
            UtentePreferences utentePreferences = new UtentePreferences();

            if (utentePreferences.IsLoggedIn() && utentePreferences.IsInitialized())
            {
                // Vai alla homepage
                Task.Run(() => { Task.Delay(1700).ContinueWith(t => new NavigationDrawer()); });

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
                            // Mostra errore

                            return;
                        }

                        bool isUserInitialized = (bool)cloudFunctionResponse.JsonData;

                        if (isUserInitialized)
                        {
                            // Logged in and data set -> Redirect to home activity
                            new NavigationDrawer();
                        }
                        else
                        {
                            // Logged in but data is not set -> Redirect to scegli proprietario affittuario
                            new ScegliProprietarioAffittuario();
                        }
                    });
                return;
            }
            else
            {
                Task.Run(() => { Task.Delay(1700).ContinueWith(t => new ScegliLoginRegistrazione()); });
            }


        }
    }
}