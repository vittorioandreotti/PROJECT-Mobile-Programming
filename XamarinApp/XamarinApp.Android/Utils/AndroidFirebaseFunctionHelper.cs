using Android.Content;
using Android.Widget;
using Java.Util;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Essentials;
using XamarinApp;
using XamarinApp.Utils.Interfaces;

namespace XamarinApp.Android.Utils
{
    class AndroidFirebaseFunctionHelper : IFirebaseFunctionHelper
    {
        // FirebaseFunctions firebaseFunctions;

        public AndroidFirebaseFunctionHelper()// (FirebaseApp firebaseApp)
        {
            // this.firebaseFunctions = FirebaseFunctions.GetInstance(firebaseApp);

        }



        public Task<bool> CreaCasa(string Nome, string Indirizzo)
        {
            throw new NotImplementedException();
        }


        public Task<bool> Disiscrizione()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoAffittoAffittuario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoAffittoProprietario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoBolletteAffittuario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoBolletteProprietario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoSommarioAffittuario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoSommarioProprietario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoSpesaComuneAffittuario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoSpesaComuneProprietario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoSpesaCondominioAffittuario()
        {
            throw new NotImplementedException();
        }

        public Task<List<Spesa>> ElencoSpesaCondominioProprietario()
        {
            throw new NotImplementedException();
        }

        public Task<Dictionary<string, List<Spesa>>> ElencoSpeseAffittuario()
        {
            throw new NotImplementedException();
        }

        public Task<Dictionary<string, List<Spesa>>> ElencoSpeseProprietario()
        {
            throw new NotImplementedException();
        }

       

        public Task<Dictionary<string, object>> GetUtenteAndCasa()
        {
            throw new NotImplementedException();
        }

        public Task<bool> InserisciAffittuario()
        {
            throw new NotImplementedException();
        }

        public Task<bool> InserisciProprietario()
        {
            throw new NotImplementedException();
        }

        public Task<bool> InserisciSpesaAffitto(double importo, string titolo, string stringDataAffitto, string stringDataScadenza)
        {
            throw new NotImplementedException();
        }

        public Task<bool> InserisciSpesaBolletta(double importo, string categoria, string stringDataBolletta, string stringDataScadenza)
        {
            throw new NotImplementedException();
        }

        public Task<bool> InserisciSpesaComune(double importo, string nome, string stringDataSpesa, string descrizione)
        {
            throw new NotImplementedException();
        }

        public Task<bool> InserisciSpesaCondominio(double importo, string nome, string stringDataAffitto)
        {
            throw new NotImplementedException();
        }

        

        public Task<bool> IsUserInitialized()
        {
            throw new NotImplementedException();
        }

        public Task<bool> ModificaPassword(string newPassword, string newPasswordRepeat)
        {
            throw new NotImplementedException();
        }

        public Task<bool> PagaSpesa(string idSpesa, string idCasa)
        {
            throw new NotImplementedException();
        }

        public Task<bool> PagaSpesa(string idSpesa)
        {
            throw new NotImplementedException();
        }

        public Task<bool> PartecipaCasa(string idCasa)
        {
            throw new NotImplementedException();
        }

        public Task<bool> PartecipaCasa()
        {
            throw new NotImplementedException();
        }

        public Task<bool> RegistraUtente(string nome, string cognome)
        {
            throw new NotImplementedException();
        }

        
    }
}
