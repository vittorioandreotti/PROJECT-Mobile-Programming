using System;
using System.Collections.Generic;
using System.Text;
using XamarinApp.Utils.Helpers;

namespace XamarinApp.Utils
{
    public class UtentePreferences : PreferencesHelper
    {
        private const string KeyInizializzato = "Inizializzato";
        private const string KeyBearer = "BEARER_ACCESS";

        private const string KeyIdUtente = "IdUtente";
        private const string KeyNome = "Nome";
        private const string KeyCognome = "Cognome";
        private const string KeyEmail = "Email";
        private const string KeyTipo = "TipoUtente";

        private const string KeyIdCasa = "IdCasa";


        private const string KeyNomeCasa = "NomeCasa";
        private const string KeyIndirizzoCasa = "IndirizzoCasa";



        public bool IsLoggedIn()
        {
            return this.Contains(KeyBearer) && this.GetAuthToken().Length > 0;
        }

        public bool IsInitialized()
        {
            return Contains(KeyInizializzato) && GetBoolean(KeyInizializzato);
        }
        public void SetInitialized()
        {
            SetBoolean(KeyInizializzato, true);
        }

        public string GetAuthToken()
        {
            return GetString(KeyBearer);
        }
        public void SetAuthToken(string BearerToken)
        {
            SetString(KeyBearer, BearerToken);
        }

        public string GetNome()
        {
            return GetString(KeyNome);
        }
        public void SetNome(string Nome)
        {
            SetString(KeyNome, Nome);
        }

        public string GetCognome()
        {
            return GetString(KeyCognome);
        }
        public void SetCognome(string Cognome)
        {
            SetString(KeyCognome, Cognome);
        }

        public string GetEmail()
        {
            return GetString(KeyEmail);
        }
        public void SetEmail(string Email)
        {
            SetString(KeyEmail, Email);
        }

        public string GetIdCasa()
        {
            return GetString(KeyIdCasa);
        }
        public void SetIdCasa(string IdCasa)
        {
            this.SetString(KeyIdCasa, IdCasa);
        }

        public string GetNomeCasa()
        {
            return GetString(KeyNomeCasa);
        }
        public void SetNomeCasa(string NomeCasa)
        {
            SetString(KeyNomeCasa, NomeCasa);
        }

        public string GetIndirizzoCasa()
        {
            return GetString(KeyIndirizzoCasa);
        }
        public void SetIndirizzoCasa(string IndirizzoCasa)
        {
            SetString(KeyIndirizzoCasa, IndirizzoCasa);
        }

        public string GetIdUtente()
        {
            return GetString(KeyIdUtente);
        }
        public void SetIdUtente(string IdUtente)
        {
            SetString(KeyIdUtente, IdUtente);
        }

        public string GetTipo()
        {
            return GetString(KeyTipo);
        }
        public void SetTipo(string Tipo)
        {
            SetString(KeyTipo, Tipo);
        }

        public bool IsProprietario()
        {
            string Tipo;
            Tipo = GetTipo();
            if (Tipo == null) return false;
            return Tipo.ToLower().Equals("proprietario");

        }

        public bool IsAffittuario()
        {
            string Tipo;
            Tipo = GetTipo();
            if (Tipo == null) return false;
            return Tipo.ToLower().Equals("affittuario");
        }
    }
}
