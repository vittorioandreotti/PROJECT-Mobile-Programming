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

namespace XamarinApp.ViewModels
{
    class InserimentoBolletteViewModel : INotifyPropertyChanged
    {
        public Action DisplayInserisciTuttiICampi;
        public Action DisplayErrore;
        public Func<Task> DisplaySuccesso;


        public ICommand SubmitCommand { protected set; get; }

        public Spesa _SpesaModel;

        public string CategoriaSpesa
        {
            get
            {
                return _SpesaModel.Categoria;
            }
            set
            {
                _SpesaModel.Categoria = value;
                OnPropertyChanged(nameof(CategoriaSpesa));
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

        public InserimentoBolletteViewModel()
        {
            SubmitCommand = new Command(InserisciBollette);
            _SpesaModel = new Spesa();
            _SpesaModel.DataInserimento = DateTime.Now;
            _SpesaModel.DataScadenza = DateTime.Now.AddDays(30);
        }

        private void InserisciBollette()
        {
            if (PrezzoSpesa == 0 || CategoriaSpesa.Length == 0)
            {
                DisplayInserisciTuttiICampi();
                return;
            }

            Device.BeginInvokeOnMainThread(async () => {
                await StartLoading();
            });

            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                            .InserisciSpesaBolletta(PrezzoSpesa, CategoriaSpesa, Helper.FormatDateToString(DataInserimentoSpesa), Helper.FormatDateToString(DataScadenzaSpesa))
                            .ContinueWith((Task<bool> taskInserisciSpesaBolletta) => {
                                taskInserisciSpesaBolletta.Wait();

                                if (taskInserisciSpesaBolletta.Result)
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
