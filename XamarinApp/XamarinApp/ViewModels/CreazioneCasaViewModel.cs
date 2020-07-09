using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Input;
using Xamarin.Forms;
using System.Text;
namespace XamarinApp.ViewModels
{
    class CreazioneCasaViewModel : INotifyPropertyChanged
    {
        public Action DisplayInvalidCreaCasaPrompt;
        public event PropertyChangedEventHandler PropertyChanged = delegate { };
        private string nome;
        public string Nome
        {
            get { return nome; }
            set
            {
                nome = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Nome"));
            }
        }
        private string indirizzo;
        public string Indirizzo
        {
            get { return indirizzo; }
            set
            {
                indirizzo = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Indirizzo"));
            }
        }
        public ICommand SubmitCommand { protected set; get; }
        public CreazioneCasaViewModel()
        {
            SubmitCommand = new Command(OnSubmit);
        }
        public void OnSubmit()
        {
            if (nome.Length == 0 || indirizzo.Length == 0)
            {
                DisplayInvalidCreaCasaPrompt();
                return;
            }

            // Azione di creazione casa con firebase
        }
    }
}
