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
            InitializeComponent();
            var vm = new InserisciCodiceCasaViewModel();
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