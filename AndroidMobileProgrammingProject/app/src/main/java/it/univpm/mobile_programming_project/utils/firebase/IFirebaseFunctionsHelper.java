package it.univpm.mobile_programming_project.utils.firebase;

import android.content.Context;

import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.models.Torneo;

interface IFirebaseFunctionsHelper {
    Task< Boolean > isUserInitialized();

    Task<Boolean> creaCasa(String nome, String indirizzo);

    Task<Boolean> inserisciProprietario();

    Task<Boolean> partecipaCasa(String idCasa);

    Task<Boolean> inserisciAffittuario();

    Task<Boolean> registraUtente(String nome, String cognome);

    Task<Boolean> inserisciTorneo(String titolo, String indirizzo, String categoria, String regolamento, String dataEvento, String oraEvento);

    Task<Boolean> inserisciSpesaBolletta(Double importo, String categoria, String stringDataBolletta, String stringDataScadenza);

    Task<Boolean> inserisciSpesaAffitto(Double importo, String titolo, String stringDataAffitto, String stringDataScadenza);

    Task<Boolean> inserisciSpesaCondominio(Double importo, String nome, String stringDataAffitto);

    Task<Boolean> inserisciSpesaComune(Double importo, String nome, String stringDataSpesa, String descrizione);

    Task<HashMap<String, Object>> getUtenteAndCasa();

    Task<Boolean> disiscrizione();

    Task<Boolean> modificaPassword(String newPassword, String newPasswordRepeat);

    Task<List<Torneo>> elencoTornei();

    Task<List<Torneo>> storicoTornei();

    Task<Boolean> partecipaTorneo(String idTorneo);

    Task<Map<String, List<Spesa>>> elencoSpeseAffittuario (Context context);

    Task<Map<String, List<Spesa>>> elencoSpeseProprietario (Context context);

    Task<Boolean> pagaSpesa(String idSpesa, String idCasa);
}
