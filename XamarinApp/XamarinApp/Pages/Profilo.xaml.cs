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
    public partial class Profilo : ContentPage
    {
        public Profilo()
        {
            InitializeComponent();
        }

        private void ModificaPassword(object sender, EventArgs e)
        {

        }

        private void CancellaAccount(object sender, EventArgs e)
        {
            
        }
    }
}