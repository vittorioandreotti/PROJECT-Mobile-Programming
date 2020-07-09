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
    public partial class InserisciCodiceCasa : ContentPage
    {
        public InserisciCodiceCasa()
        {
            var vm = new InserisciCodiceCasaViewModel();
            this.BindingContext = vm;
            vm.DisplayInvalidCodiceCasaPrompt += () => DisplayAlert("Error", "Inserire il codice della casa corretto", "OK");
            InitializeComponent();
        }
    }
}