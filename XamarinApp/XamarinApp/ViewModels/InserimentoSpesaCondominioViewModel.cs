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
    class InserimentoSpesaCondominioViewModel : INotifyPropertyChanged
    {
        public Action DisplayInserisciTuttiICampi;
        public Action DisplayErrore;
        public Func<Task> DisplaySuccesso;


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
            if (PrezzoSpesa == 0 || TitoloSpesa.Length == 0)
            {
                DisplayInserisciTuttiICampi();
                return;
            }

            Device.BeginInvokeOnMainThread(async () => {
                await StartLoading();
            });

            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                            .InserisciSpesaCondominio(PrezzoSpesa, TitoloSpesa, Helper.FormatDateToString(DataInserimentoSpesa))
                            .ContinueWith((Task<bool> taskInserisciSpesaCondominio) => {
                                taskInserisciSpesaCondominio.Wait();

                                if (taskInserisciSpesaCondominio.Result)
                                {
                                    //Spesa inserita 
                                    Device.BeginInvokeOnMainThread(async () => {
                                        await StopLoading();
                                        App.Current.MainPage = new NavigationDrawer();
                                    });
                                    return;
                                }
                                else
                                {
                                    //Spesa non inserita
                                    Device.BeginInvokeOnMainThread(async () => {
                                        await StopLoading();
                                        await DisplaySuccesso();
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
