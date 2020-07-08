using Java.Util;
using Java.Sql;
using System;
using System.Collections.Generic;

namespace XamarinApp
{
    class Spesa
    {
        private String IdSpesa;
        private String IdUtente;
        private String Nome;
        private String Descrizione;
        private String Categoria;
        private String Titolo;
        private String Tipo;
        private Double Prezzo;
        private DateTime DataInserimento;
        private DateTime DataPagamento;
        private DateTime DataScadenza;
        private String NomeUtente;
        private String CognomeUtente;

        public Spesa() { }

        public Boolean IsSpesaPagata()
        {
            return this.DataPagamento != null;
        }

        public String GetIdSpesa()
        {
            return IdSpesa;
        }

        public void SetIdSpesa(String IdSpesa)
        {
            this.IdSpesa = IdSpesa;
        }

        public String GetIdUtente()
        {
            return IdUtente;
        }

        public void SetIdUtente(String IdUtente)
        {
            this.IdUtente = IdUtente;
        }

        public String GetNome()
        {
            return Nome;
        }

        public void SetNome(String Nome)
        {
            this.Nome = Nome;
        }

        public String GetTitolo()
        {
            return Titolo;
        }

        public void SetTitolo(String Titolo)
        {
            this.Titolo = Titolo;
        }

        public String GetCategoria()
        {
            return Categoria;
        }

        public void SetCategoria(String Categoria)
        {
            this.Categoria = Categoria;
        }

        public String GetDescrizione()
        {
            return Descrizione;
        }

        public void SetDescrizione(String Descrizione)
        {
            this.Descrizione = Descrizione;
        }

        public String GetTipo()
        {
            return Tipo;
        }

        public void SetTipo(String Tipo)
        {
            this.Tipo = Tipo;
        }

        public Double GetPrezzo()
        {
            return Prezzo;
        }

        public void SetPrezzo(Double Prezzo)
        {
            this.Prezzo = Prezzo;
        }

        public DateTime GetDataInserimento()
        {
            return DataInserimento;
        }

        public void SetDataInserimento(DateTime DataInserimento)
        {
            this.DataInserimento = DataInserimento;
        }

        public DateTime GetDataPagamento()
        {
            return DataPagamento;
        }

        public void SetDataPagamento(DateTime DataPagamento)
        {
            this.DataPagamento = DataPagamento;
        }

        public String GetNomeUtente()
        {
            return NomeUtente;
        }

        public void SetNomeUtente(String NomeUtente)
        {
            this.NomeUtente = NomeUtente;
        }

        public String GetCognomeUtente()
        {
            return CognomeUtente;
        }

        public void SetCognomeUtente(String CognomeUtente)
        {
            this.CognomeUtente = CognomeUtente;
        }

        public DateTime GetDataScadenza()
        {
            return DataScadenza;
        }

        public void SetDataScadenza(DateTime DataScadenza)
        {
            this.DataScadenza = DataScadenza;
        }

        public void CreateFromHashMap(Dictionary<String, Object> SpesaSingola)
        {

            this.SetPrezzo((Double)SpesaSingola["prezzo"]);
            this.SetIdSpesa((String)SpesaSingola["idSpesa"]);
            this.SetDescrizione((String)SpesaSingola["descrizione"]);
            this.SetTipo((String)SpesaSingola["tipo"]);
            this.SetTitolo((String)SpesaSingola["titolo"]);
            this.SetCategoria((String)SpesaSingola["categoria"]);

            this.SetNomeUtente((String)SpesaSingola["nomeUtente"]);
            this.SetCognomeUtente((String)SpesaSingola["cognomeUtente"]);

            this.SetDataInserimento(Helper.FromMillisToDate((Java.Lang.Long)SpesaSingola["dataInserimento"]));
            this.SetDataPagamento(Helper.FromMillisToDate((Java.Lang.Long)SpesaSingola["dataPagamento"]));
            this.SetDataScadenza(Helper.FromMillisToDate((Java.Lang.Long)SpesaSingola["dataScadenza"]));

            this.SetNome((String)SpesaSingola["nome"]);
            this.SetIdUtente((String)SpesaSingola["idUtente"]);
        }
    }

    
}
