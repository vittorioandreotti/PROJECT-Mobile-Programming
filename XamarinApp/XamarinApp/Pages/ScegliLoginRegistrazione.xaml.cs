using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ScegliLoginRegistrazione : ContentPage
    {
        public ScegliLoginRegistrazione()
        {
            InitializeComponent();
        }

        private void Login_Clicked(object sender, EventArgs e)
        {
            this.Navigation.PushAsync(new Login());
        }

        private void Registrazione_Clicked(object sender, EventArgs e)
        {
            this.Navigation.PushAsync(new Registrazione());
        }
    }
}