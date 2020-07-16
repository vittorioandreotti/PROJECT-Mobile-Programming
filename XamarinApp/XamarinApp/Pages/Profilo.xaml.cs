using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Utils;
using XamarinApp.ViewModels;

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

        }

        private void CancellaAccount(object sender, EventArgs e)
        {
            
        }
    }
}