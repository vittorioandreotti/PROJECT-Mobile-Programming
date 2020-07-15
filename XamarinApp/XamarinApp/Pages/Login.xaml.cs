using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
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
            vm.Login = LoginActionAsync;
            vm.DisplayInvalidLoginPrompt += () => DisplayAlert("Errore", "Inserire una mail valida e una password.", "OK");
            InitializeComponent();

        }

        public async void LoginActionAsync(string Email, string Password)
        {
            string Token = await firebaseAuth.LoginWithEmailPassword(Email, Password);
            if (Token != "")
            {
                utentePreferences.SetAuthToken(Token);

                string token = utentePreferences.GetAuthToken();

                // await Navigation.PushAsync(new LoggedPage());
                await DisplayAlert("Autenticazione Eseguita con successo!", "Token: " + token, "OK");

                // TODO: Da finire!!
            }
            else
            {
                await DisplayAlert("Authentication Fallita", "E-mail o password errate.", "OK");
            }
        }

    }
}