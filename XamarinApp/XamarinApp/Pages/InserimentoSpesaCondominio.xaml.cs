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
    public partial class InserimentoSpesaCondominio : ContentPage
    {
        InserimentoSpesaCondominioViewModel viewModel;

        public InserimentoSpesaCondominio()
        { 
            InitializeComponent();
            viewModel = new InserimentoSpesaCondominioViewModel();
            viewModel.DisplayInserisciTuttiICampi = DisplayInserisciTuttiICampi;
            BindingContext = viewModel;
        }

        async void DisplayInserisciTuttiICampi(Spesa spesa)
        {
            await DisplayAlert("DATI", spesa.GetTitolo(), "OK");
            await DisplayAlert("Errore", "Inserisci tutti i campi per continuare", "CHIUDI");
        }
    }
}