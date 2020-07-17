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
    public partial class Registrazione : ContentPage
    {
        public Registrazione()
        {
            var vm = new RegistrazioneViewModel();
            this.BindingContext = vm;
            vm.DisplayInvalidLoginPrompt += () => DisplayAlert("Errore nella registrazione", "Inserire tutti i campi.", "OK");
            vm.DisplayInvalidPasswordPrompt += () => DisplayAlert("Errore nella registrazione", "La password deve combaciare con la password di conferma.", "OK");
            vm.DisplayRegistrazioneFallita += () => DisplayAlert("Registrazione Fallita", "Registrazione fallita.", "OK");
            InitializeComponent();
        }
    }
}