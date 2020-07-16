using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Utils;

namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MostraCodiceCasa : ContentPage
    {
        UtentePreferences utentePreferences;
        public MostraCodiceCasa()
        {
            InitializeComponent();
            utentePreferences = new UtentePreferences();
            Codice.Text = utentePreferences.GetIdCasa();
        }

        private async void Condividi(object sender, EventArgs e)
        {
            await Share.RequestAsync(new ShareTextRequest
            {
                Text = Codice.Text,
                Title = "Condividi"
            }); 
        }

    }
}