using System;
using System.Collections.Generic;
using XamarinApp.Helpers;
using XamarinApp.Utils;

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
        public DateTime DataPagamento
        {
            get { return _dataPagamento; }
            set
            {
                if (value != _dataPagamento)
                {
                    _dataPagamento = value;
                    OnPropertyChanged(nameof(DataPagamento));
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

        public Spesa() { }

        public Boolean IsSpesaPagata()
        {
            return 
                new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc)
                    .ToUniversalTime()
                    .Subtract(this.DataPagamento)
                    .TotalMilliseconds != 0;
        }



        public void CreateFromHashMap(Dictionary<String, Object> SpesaSingola)
        {

            this.Prezzo = (Double)SpesaSingola["prezzo"];
            this.IdSpesa = (String)SpesaSingola["idSpesa"];
            this.Descrizione = (String)SpesaSingola["descrizione"];
            this.Tipo = (String)SpesaSingola["tipo"];
            this.Titolo = (String)SpesaSingola["titolo"];
            this.Categoria = (String)SpesaSingola["categoria"];

            this.NomeUtente = (String)SpesaSingola["nomeUtente"];
            this.CognomeUtente = (String)SpesaSingola["cognomeUtente"];

            
            this.DataInserimento = Helper.FromMillisToDate((long)SpesaSingola["dataInserimento"]);
            this.DataPagamento = Helper.FromMillisToDate((long)SpesaSingola["dataPagamento"]);
            this.DataScadenza = Helper.FromMillisToDate((long)SpesaSingola["dataScadenza"]);
            

            this.Nome = (String)SpesaSingola["nome"];
            this.IdUtente = (String)SpesaSingola["idUtente"];
        }
    }

    
}
