using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Text;
using System.Windows.Input;
using Xamarin.Forms;
using XamarinApp.LoadingService;
using Rg.Plugins.Popup.Services;
using XamarinApp.Utils;
using System.Threading.Tasks;
using Xamarin.Forms.Shapes;

namespace XamarinApp.ViewModels
{
    class InserimentoAffittoViewModel : INotifyPropertyChanged
    {
        public Action DisplayInserisciTuttiICampi; 
        public Action DisplayErrore;
        public Func<Task> DisplaySuccesso;


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

        public double PrezzoSpesa
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

            if (PrezzoSpesa == 0 || TitoloSpesa.Length == 0 )
            {
                DisplayInserisciTuttiICampi();
                return;
            }

            Device.BeginInvokeOnMainThread(async () => {
                await StartLoading();
            });


            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                            .InserisciSpesaAffitto(PrezzoSpesa, TitoloSpesa, Helper.FormatDateToString(DataInserimentoSpesa), Helper.FormatDateToString(DataScadenzaSpesa))
                            .ContinueWith((Task<bool> taskInserisciSpesaAffitto) => {
                                taskInserisciSpesaAffitto.Wait();

                                if (taskInserisciSpesaAffitto.Result)
                                {
                                    //Spesa inserita   
                                    Device.BeginInvokeOnMainThread(async () => {
                                        await StopLoading();
                                        await DisplaySuccesso();
                                        App.Current.MainPage = new NavigationDrawer();
                                    });
                                    return;
                                }
                                else
                                {
                                    //Spesa non inserita
                                    Device.BeginInvokeOnMainThread(async () => {
                                        await StopLoading();
                                        DisplayErrore();
                                    });
                                }
                            });
        }


        private Task StartLoading()
        {
            LoadingPage loadingPage = new LoadingPage();

            return PopupNavigation.Instance.PushAsync(loadingPage);
        }

        private Task StopLoading()
        {
            return PopupNavigation.Instance.PopAsync();
        }

        public event PropertyChangedEventHandler PropertyChanged;
        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
