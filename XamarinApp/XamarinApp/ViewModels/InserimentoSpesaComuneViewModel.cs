using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Text;
using System.Windows.Input;
using Xamarin.Forms;

namespace XamarinApp.ViewModels
{
    class InserimentoSpesaComuneViewModel : INotifyPropertyChanged
    {
        public Action<Spesa> DisplayInserisciTuttiICampi;
        public ICommand SubmitCommand { protected set; get; }

        public Spesa _SpesaModel;
        public Spesa SpesaModel
        {
            get
            {
                return _SpesaModel;
            }
            set
            {
                _SpesaModel = value;
                OnPropertyChanged();
            }
        }


        public InserimentoSpesaComuneViewModel()
        {
            SubmitCommand = new Command(InserisciSpesaComune);
            SpesaModel = new Spesa();
        }

        private void InserisciSpesaComune()
        {
            DisplayInserisciTuttiICampi(SpesaModel);
            // if( DataScadenza )
        }




        public event PropertyChangedEventHandler PropertyChanged;
        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
