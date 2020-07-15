using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Text;
using System.Windows.Input;
using Xamarin.Forms;

namespace XamarinApp.ViewModels
{
    class InserimentoSpesaCondominioViewModel : INotifyPropertyChanged
    {
        public Action<Spesa> DisplayInserisciTuttiICampi;
        public ICommand InserisciSpesaCondominioClick { protected set; get; }

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


        public InserimentoSpesaCondominioViewModel()
        {
            InserisciSpesaCondominioClick = new Command(InserisciSpesaCondominio);
            _SpesaModel = new Spesa();
            _SpesaModel.DataInserimento = DateTime.Now;

        }

        private void InserisciSpesaCondominio()
        {

            if( _SpesaModel.Titolo.Length == 0 || _SpesaModel.DataInserimento.ToString().Length == 0 || _SpesaModel.Prezzo.ToString().Length == 0 ) 
               {

               }else { DisplayInserisciTuttiICampi(_SpesaModel); }
               
        }





        public event PropertyChangedEventHandler PropertyChanged;
        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
