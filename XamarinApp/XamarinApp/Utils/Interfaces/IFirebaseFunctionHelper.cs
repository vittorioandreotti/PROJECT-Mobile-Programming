using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace XamarinApp.Utils.Interfaces
{
    public interface IFirebaseFunctionHelper
    {
        Task<Boolean> IsUserInitialized();

        Task<Boolean> CreaCasa(String nome, String indirizzo);

        Task<Boolean> InserisciProprietario();

        Task<Boolean> PartecipaCasa(String idCasa);

        Task<Boolean> InserisciAffittuario();

        Task<Boolean> RegistraUtente(String nome, String cognome);


        Task<Boolean> InserisciSpesaBolletta(Double importo, String categoria, String stringDataBolletta, String stringDataScadenza);

        Task<Boolean> InserisciSpesaAffitto(Double importo, String titolo, String stringDataAffitto, String stringDataScadenza);

        Task<Boolean> InserisciSpesaCondominio(Double importo, String nome, String stringDataAffitto);

        Task<Boolean> InserisciSpesaComune(Double importo, String nome, String stringDataSpesa, String descrizione);

        Task<Dictionary<String, Object>> GetUtenteAndCasa();

        Task<Boolean> Disiscrizione();

        Task<Boolean> ModificaPassword(String newPassword, String newPasswordRepeat);
      

        Task<Dictionary<String, List<Spesa>>> ElencoSpeseAffittuario();

        Task<Dictionary<String, List<Spesa>>> ElencoSpeseProprietario();

        Task<Boolean> PagaSpesa(String idSpesa, String idCasa);
    }
}