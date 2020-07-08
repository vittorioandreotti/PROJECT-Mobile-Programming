using Java.Util;
using System;
using System.Collections.Generic;
using System.Text;

namespace XamarinApp
{
    class Torneo
    {
        private String Id;
        private String Titolo;
        private String Categoria;
        private String Indirizzo;
        private DateTime DataOra;
        private String Regolamento;

        public Torneo() { }

        public Torneo(String Id, String Titolo, String Categoria, String Indirizzo, DateTime DataOra, String Regolamento)
        {
            this.Id = Id;
            this.Titolo = Titolo;
            this.Categoria = Categoria;
            this.Indirizzo = Indirizzo;
            this.DataOra = DataOra;
            this.Regolamento = Regolamento;
        }

        public String GetId()
        {
            return Id;
        }

        public void SetId(String Id)
        {
            this.Id = Id;
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

        public String GetIndirizzo()
        {
            return Indirizzo;
        }

        public void SetIndirizzo(String Indirizzo)
        {
            this.Indirizzo = Indirizzo;
        }

        public DateTime GetDataOra()
        {
            return DataOra;
        }

        public void SetDataOra(DateTime DataOra)
        {
            this.DataOra = DataOra;
        }

        public String GetRegolamento()
        {
            return Regolamento;
        }

        public void SetRegolamento(String Regolamento)
        {
            this.Regolamento = Regolamento;
        }

        public void CreateFromHashMap(Dictionary<String, Object> TorneoSingolo)
        {

            this.SetId((String)TorneoSingolo["id"]);
            this.SetTitolo((String)TorneoSingolo["titolo"]);
            this.SetCategoria((String)TorneoSingolo["categoria"]);
            this.SetIndirizzo((String)TorneoSingolo["indirizzo"]);
            this.SetRegolamento((String)TorneoSingolo["regolamento"]);
            this.SetDataOra(Helper.FromMillisToDate((Java.Lang.Long)TorneoSingolo["dataOra"]));
        }
    }
}
