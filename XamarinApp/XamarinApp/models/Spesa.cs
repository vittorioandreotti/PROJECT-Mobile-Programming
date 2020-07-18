using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Windows.Input;
using Xamarin.Forms;
using XamarinApp.Helpers;
using XamarinApp.Models.Helpers;
using XamarinApp.Utils;
using XamarinApp.LoadingService;
using Rg.Plugins.Popup.Services;
using System.Threading.Tasks;

namespace XamarinApp
{
    public class Spesa : ObservableObject
    {
        private string _idSpesa;
        public string IdSpesa
        {
            get { return _idSpesa; }
            set
            {
                if (value != _idSpesa)
                {
                    _idSpesa = value;
                    OnPropertyChanged(nameof(IdSpesa));
                }
            }
        }

        private string _idUtente;
        public string IdUtente
        {
            get { return _idUtente; }
            set
            {
                if (value != _idUtente)
                {
                    _idUtente = value;
                    OnPropertyChanged(nameof(IdUtente));
                }
            }
        }

        private string _nome;
        public string Nome
        {
            get { return _nome; }
            set
            {
                if (value != _nome)
                {
                    _nome = value;
                    OnPropertyChanged(nameof(Nome));
                }
            }
        }

        private string _descrizione;
        public string Descrizione
        {
            get { return _descrizione; }
            set
            {
                if (value != _descrizione)
                {
                    _descrizione = value;
                    OnPropertyChanged(nameof(Descrizione));
                }
            }
        }

        private string _categoria;
        public string Categoria
        {
            get { return _categoria; }
            set
            {
                if (value != _categoria)
                {
                    _categoria = value;
                    OnPropertyChanged(nameof(Categoria));
                }
            }
        }

        private string _titolo;
        public string Titolo
        {
            get { return _titolo; }
            set
            {
                if (value != _titolo)
                {
                    _titolo = value;
                    OnPropertyChanged(nameof(Titolo));
                }
            }
        }

        private string _tipo;
        public string Tipo
        {
            get { return _tipo; }
            set
            {
                if (value != _tipo)
                {
                    _tipo = value;
                    OnPropertyChanged(nameof(Tipo));
                }
            }
        }

        private Double _prezzo;
        public Double Prezzo
        {
            get { return _prezzo; }
            set
            {
                if (value != _prezzo)
                {
                    _prezzo = value;
                    OnPropertyChanged(nameof(Prezzo));
                }
            }
        }

        private DateTime _dataInserimento;
        public DateTime DataInserimento
        {
            get { return _dataInserimento; }
            set
            {
                if (value != _dataInserimento)
                {
                    _dataInserimento = value;
                    OnPropertyChanged(nameof(DataInserimento));
                }
            }
        }

        private DateTime _dataPagamento;
        public string DataPagamentoStr
        {
            get
            {
                return Helper.FormatDateToStringWithHour(this._dataPagamento);
            }
        }
        public DateTime DataPagamento
        {
            get { return _dataPagamento; }
            set
            {
                if (value != _dataPagamento)
                {
                    _dataPagamento = value;
                    OnPropertyChanged(nameof(DataPagamento));
                    OnPropertyChanged(nameof(DataPagamentoStr));

                    OnPropertyChanged(nameof(VisibilityButtonPaga));
                    OnPropertyChanged(nameof(VisibilityNonPagata));
                    OnPropertyChanged(nameof(VisibilityPagata));
                }
            }
        }

        private DateTime _dataScadenza;
        public DateTime DataScadenza
        {
            get { return _dataScadenza; }
            set
            {
                if (value != _dataScadenza)
                {
                    _dataScadenza = value;
                    OnPropertyChanged(nameof(DataScadenza));
                }
            }
        }

        private string _nomeUtente;
        public string NomeUtente
        {
            get { return _nomeUtente; }
            set
            {
                if (value != _nomeUtente)
                {
                    _nomeUtente = value;
                    OnPropertyChanged(nameof(NomeUtente));
                }
            }
        }

        private string _cognomeUtente;
        public string CognomeUtente
        {
            get { return _cognomeUtente; }
            set
            {
                if (value != _cognomeUtente)
                {
                    _cognomeUtente = value;
                    OnPropertyChanged(nameof(CognomeUtente));
                }
            }
        }

        public bool VisibilityNonPagata
        {
            get { return !this.IsSpesaPagata(); }
        }

        public bool VisibilityPagata
        {
            get { return this.IsSpesaPagata(); }
        }


        UtentePreferences utentePreferences = new UtentePreferences();
        public bool VisibilityAffittuario
        {
            get { return utentePreferences.IsProprietario(); }
        }

        public bool VisibilityButtonPaga
        {
            get { return utentePreferences.IsAffittuario() && !this.IsSpesaPagata(); }
        }

        public Spesa() { }

        private Command pagaSpesaCommand;
        public ICommand PagaSpesaCommand
        {
            get
            {
               pagaSpesaCommand = new Command( () => PagaSpesa(IdSpesa));
               return pagaSpesaCommand;
            }
        }

        public void PagaSpesa (object Parameter)
        {
            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });

            FirebaseFunctionHelper firebaseFunction = new FirebaseFunctionHelper();
            firebaseFunction
                    .PagaSpesa(IdSpesa)
                    .ContinueWith((Task<bool> taskPagaSpesa) => {
                        taskPagaSpesa.Wait();

                        if (taskPagaSpesa.Result)
                        {
                            //Spesa pagata  
                            Device.BeginInvokeOnMainThread(() => {
                                StopLoading();
                                DataPagamento = DateTime.Now;
                            });
                            return;
                        }
                        else
                        {
                            //Spesa non pagata
                            Device.BeginInvokeOnMainThread(() => {
                                StopLoading();
                                
                            });
                        }
                    });
        }
        private async void StartLoading()
        {
            LoadingPage loadingPage = new LoadingPage();

            await PopupNavigation.Instance.PushAsync(loadingPage);
        }

        private async void StopLoading()
        {
            await PopupNavigation.Instance.PopAsync();
        }
    

        public Boolean IsSpesaPagata()
        {
            return this.DataPagamento != null && this.DataPagamento.Year > 1970;
                /*
                 * new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc)
                    .ToUniversalTime()
                    .Subtract(this.DataPagamento)
                    .TotalMilliseconds != 0;*/
        }

        public void CreateFromHashMap(JObject SpesaSingola)
        {
            JToken tmp;

            if(SpesaSingola.TryGetValue("prezzo", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)) )
                this.Prezzo = (Double)tmp;
            
            if (SpesaSingola.TryGetValue("idSpesa", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.IdSpesa = (String)tmp;
            
            if (SpesaSingola.TryGetValue("descrizione", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.Descrizione = (String)tmp;
            
            if (SpesaSingola.TryGetValue("tipo", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.Tipo = (String)tmp;
            
            if (SpesaSingola.TryGetValue("titolo", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.Titolo = (String)tmp;
            
            if (SpesaSingola.TryGetValue("categoria", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.Categoria = (String)tmp;

            if (SpesaSingola.TryGetValue("nomeUtente", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.NomeUtente = (String)tmp;

            if (SpesaSingola.TryGetValue("cognomeUtente", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.CognomeUtente = (String)tmp;

            if (SpesaSingola.TryGetValue("dataInserimento", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.DataInserimento = Helper.FromMillisToDate((long) tmp);

            if (SpesaSingola.TryGetValue("dataPagamento", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.DataPagamento = Helper.FromMillisToDate((long)tmp);

            if (SpesaSingola.TryGetValue("dataScadenza", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.DataScadenza = Helper.FromMillisToDate((long)tmp);

            if (SpesaSingola.TryGetValue("nome", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.Nome = (String)tmp;

            if (SpesaSingola.TryGetValue("idUtente", out tmp) && !(JsonHelper.IsJsonTokenNull(tmp)))
                this.IdUtente = (String)tmp;

        }
    }

    
}
