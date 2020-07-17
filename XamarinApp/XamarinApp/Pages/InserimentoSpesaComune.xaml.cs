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
    public partial class InserimentoSpesaComune : ContentPage
    {
        InserimentoSpesaComuneViewModel viewModel;

        public InserimentoSpesaComune()
        {
            InitializeComponent();
            viewModel = new InserimentoSpesaComuneViewModel();
            viewModel.DisplayInserisciTuttiICampi = DisplayInserisciTuttiICampi;
            viewModel.DisplayErrore = DisplayErrore;
            viewModel.DisplaySuccesso = DisplaySuccessoInserimento;
            BindingContext = viewModel;
        }

        async void DisplayInserisciTuttiICampi()
        {
            await DisplayAlert("Errore", "Inserisci tutti i campi per continuare", "CHIUDI");
        }

        async void DisplayErrore()
        {
            await DisplayAlert("Errore", "Qualcosa è andato storto", "CHIUDI");
        }

        Task DisplaySuccessoInserimento()
        {
            return DisplayAlert("Successo!", "Spesa inserita correttamente.", "CHIUDI");
        }
    }
}