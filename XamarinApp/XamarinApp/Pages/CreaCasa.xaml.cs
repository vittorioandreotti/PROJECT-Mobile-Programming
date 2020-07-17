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
    public partial class CreaCasa : ContentPage
    {
        public CreaCasa()
        {
            InitializeComponent();
            var vm = new CreazioneCasaViewModel();
            this.BindingContext = vm;
            vm.DisplayErrore = DisplayErrore;
            vm.DisplaySuccesso = DisplaySuccessoInserimento;
        }

        async void DisplayErrore()
        {
            await DisplayAlert("Errore", "Qualcosa è andato storto", "CHIUDI");
        }

        Task DisplaySuccessoInserimento()
        {
            return DisplayAlert("Successo!", "Partecipazione alla casa effettuata con successo.", "CHIUDI");
        }
    }
}