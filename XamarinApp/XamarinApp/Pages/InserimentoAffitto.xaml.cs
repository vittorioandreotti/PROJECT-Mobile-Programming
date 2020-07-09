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
    public partial class InserimentoAffitto: ContentPage
    {
        InserimentoAffittoViewModel viewModel;

        public InserimentoAffitto()
        {
            InitializeComponent();
            viewModel = new InserimentoAffittoViewModel();
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