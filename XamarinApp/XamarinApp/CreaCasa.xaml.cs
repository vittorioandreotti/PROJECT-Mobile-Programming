using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.ViewModels;

namespace XamarinApp
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class CreaCasa : ContentPage
    {
        public CreaCasa()
        {
            var vm = new CreazioneCasaViewModel();
            this.BindingContext = vm;
            vm.DisplayInvalidCreaCasaPrompt += () => DisplayAlert("Error", "Inserire tutti i campi", "OK");
            InitializeComponent();

        }
    }
}