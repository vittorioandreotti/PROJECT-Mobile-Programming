using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.ViewModels;

namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ScegliProprietarioAffittuario : ContentPage
    {
        public ScegliProprietarioAffittuario()
        {
            InitializeComponent();
        }

        private void Proprietario_Clicked(object sender, EventArgs e)
        {
            this.Navigation.PushAsync(new CreaCasa());
        }

        private void Affittuario_Clicked(object sender, EventArgs e)
        {
            this.Navigation.PushAsync(new InserisciCodiceCasa());
        }

    }
}