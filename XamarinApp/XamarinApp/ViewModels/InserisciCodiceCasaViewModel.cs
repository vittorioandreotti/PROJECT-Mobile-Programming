using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Input;
using Xamarin.Forms;
using System.Text;
namespace XamarinApp.ViewModels
{
    class InserisciCodiceCasaViewModel : INotifyPropertyChanged
    {
        public Action DisplayInvalidCodiceCasaPrompt;
        public event PropertyChangedEventHandler PropertyChanged = delegate { };
        private string codice_casa;
        public string CodiceCasa
        {
            get { return codice_casa; }
            set
            {
                codice_casa = value;
                PropertyChanged(this, new PropertyChangedEventArgs("CodiceCasa"));
            }
        }
        
        public ICommand SubmitCommand { protected set; get; }
        public InserisciCodiceCasaViewModel()
        {
            SubmitCommand = new Command(OnSubmit);
        }
        public void OnSubmit()
        {
            if (codice_casa.Length == 0 )
            {
                DisplayInvalidCodiceCasaPrompt();
                return;
            }

            // Azione di partecipazione alla casa con firebase
        }
    }
}
