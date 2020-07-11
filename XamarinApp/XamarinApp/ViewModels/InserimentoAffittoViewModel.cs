using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Text;
using System.Windows.Input;
using Xamarin.Forms;

namespace XamarinApp.ViewModels
{
    class InserimentoAffittoViewModel : INotifyPropertyChanged
    {
        public Action<Spesa> DisplayInserisciTuttiICampi;
        public ICommand SubmitCommand { protected set; get; }

        public Spesa _SpesaModel;

        public string TitoloSpesa
        {
            get
            {
                return _SpesaModel.Titolo;
            }
            set
            {
                _SpesaModel.Titolo = value;
                OnPropertyChanged(nameof(TitoloSpesa));
            }
        }

        public DateTime DataInserimentoSpesa
        {
            get
            {
                return _SpesaModel.DataInserimento;
            }
            set
            {
                _SpesaModel.DataInserimento = value;
                OnPropertyChanged(nameof(DataInserimentoSpesa));
            }
        }

        public DateTime DataScadenzaSpesa
        {
            get
            {
                return _SpesaModel.DataScadenza;
            }
            set
            {
                _SpesaModel.DataScadenza = value;
                OnPropertyChanged(nameof(DataScadenzaSpesa));
            }
        }

        public Double PrezzoSpesa
        {
            get
            {
                return _SpesaModel.Prezzo;
            }
            set
            {
                _SpesaModel.Prezzo = value;
                OnPropertyChanged(nameof(PrezzoSpesa));
            }
        }


        public InserimentoAffittoViewModel()
        {
            SubmitCommand = new Command(InserisciAffitto);
            _SpesaModel = new Spesa();
            _SpesaModel.DataInserimento = DateTime.Now;
            _SpesaModel.DataScadenza = DateTime.Now.AddDays(30);
        }

        private void InserisciAffitto()
        {
            DisplayInserisciTuttiICampi(_SpesaModel);
            // if( DataScadenza )
        }




        public event PropertyChangedEventHandler PropertyChanged;
        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
