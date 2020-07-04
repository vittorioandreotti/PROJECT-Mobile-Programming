package it.univpm.mobile_programming_project.utils.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univpm.mobile_programming_project.models.Torneo;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

public class FirebaseFunctionsHelper {

    private UtenteSharedPreferences sharedPreferences;
    private FirebaseFunctions mFunctions;

    public FirebaseFunctionsHelper()
    {
        this.mFunctions = FirebaseFunctions.getInstance();
    }


    public FirebaseFunctionsHelper(UtenteSharedPreferences sharedPreferences)
    {
        this();
        this.sharedPreferences = sharedPreferences;
    }


    public Task< Boolean > isUserInitialized() {

        // Call the function and extract the result
        // exports.getUserInfo

        return this.mFunctions
            .getHttpsCallable("isUserInitialized")
            .call()
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                @Override
                public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                    HttpsCallableResult result = task.getResult();
                    Boolean resultData = (Boolean) result.getData();
                    return resultData;
                }
            });

    }

    public Task<Boolean> creaCasa(String nome, String indirizzo) {

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("nome", nome);
        data.put("indirizzo", indirizzo);

        return this.mFunctions
            .getHttpsCallable("creaCasa")
            .call( data )
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                @Override
                public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                    HttpsCallableResult result = task.getResult();
                    Boolean resultData = (Boolean) result.getData();
                    return resultData;
                }
            });
    }


    public Task<Boolean> inserisciProprietario() {

        return this.mFunctions
            .getHttpsCallable("inserisciProprietario")
            .call(  )
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                @Override
                public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                    HttpsCallableResult result = task.getResult();
                    Boolean resultData = (Boolean) result.getData();
                    return resultData;
                }
            });
    }

    public Task<Boolean> partecipaCasa(String idCasa) {

        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);

        return this.mFunctions
            .getHttpsCallable("partecipaCasa")
            .call( data )
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                @Override
                public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                    HttpsCallableResult result = task.getResult();
                    Boolean resultData = (Boolean) result.getData();
                    return resultData;
                }
            });
    }

    public Task<Boolean> inserisciAffittuario() {

        return this.mFunctions
                .getHttpsCallable("inserisciAffittuario")
                .call(  )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

    public Task<Boolean> registraUtente(String nome, String cognome) {

        Map<String, Object> dataInput = new HashMap<>();
        dataInput.put("nome", nome);
        dataInput.put("cognome", cognome);

        return this.mFunctions
                .getHttpsCallable("registraUtente")
                .call( dataInput )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

    public Task<Boolean> inserisciTorneo(String titolo, String indirizzo, String categoria, String regolamento, String dataEvento, String oraEvento ) {

        Date dataOraEvento = Helper.fromStringToDateTime(dataEvento, oraEvento);

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("titolo", titolo);
        data.put("indirizzo", indirizzo);
        data.put("categoria", categoria);
        data.put("regolamento", regolamento);
        data.put("dataOraEvento", dataOraEvento.toString() );

        return this.mFunctions
                .getHttpsCallable("inserisciTorneo")
                .call( data )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

    public Task<Boolean> inserisciSpesaBolletta(Double importo, String categoria, String stringDataBolletta, String stringDataScadenza) {

        String idCasa = this.sharedPreferences.getIdCasa();

        Date dataBolletta = Helper.fromStringToDate(stringDataBolletta);
        Date dataScadenza = Helper.fromStringToDate(stringDataScadenza);

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("categoria", categoria);
        data.put("prezzo", importo);
        data.put("dataInserimento", dataBolletta.toString() );
        data.put("dataScadenza", dataScadenza.toString() );
        data.put("tipoSpesa", "bolletta");

        return this.mFunctions
                .getHttpsCallable("inserisciSpesa")
                .call( data )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

    public Task<Boolean> inserisciSpesaAffitto(Double importo, String titolo, String stringDataAffitto, String stringDataScadenza) {

        String idCasa = this.sharedPreferences.getIdCasa();

        Date dataAffitto = Helper.fromStringToDate(stringDataAffitto);
        Date dataScadenza = Helper.fromStringToDate(stringDataScadenza);

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("titolo", titolo);
        data.put("prezzo", importo);
        data.put("dataInserimento", dataAffitto.toString() );
        data.put("dataScadenza", dataScadenza.toString() );
        data.put("tipoSpesa", "affitto");

        return this.mFunctions
                .getHttpsCallable("inserisciSpesa")
                .call( data )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

    public Task<Boolean> inserisciSpesaCondominio(Double importo, String nome, String stringDataAffitto) {

        String idCasa = this.sharedPreferences.getIdCasa();

        Date dataAffitto = Helper.fromStringToDate(stringDataAffitto);

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("nome", nome);
        data.put("prezzo", importo);
        data.put("dataInserimento", dataAffitto.toString() );
        data.put("tipoSpesa", "condominio");

        return this.mFunctions
                .getHttpsCallable("inserisciSpesa")
                .call( data )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

    public Task<Boolean> inserisciSpesaComune(Double importo, String nome, String stringDataSpesa, String descrizione) {

        String idCasa = this.sharedPreferences.getIdCasa();

        Date dataSpesa = Helper.fromStringToDate(stringDataSpesa);

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("nome", nome);
        data.put("prezzo", importo);
        data.put("dataInserimento", dataSpesa.toString() );
        data.put("descrizione", descrizione);
        data.put("tipoSpesa", "comune");

        return this.mFunctions
                .getHttpsCallable("inserisciSpesa")
                .call( data )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }


    public Task<HashMap<String, Object>> getUtenteAndCasa() {

        return this.mFunctions
            .getHttpsCallable("getUtenteAndCasa")
            .call( )
            .continueWith(new Continuation<HttpsCallableResult, HashMap<String, Object>>() {
                @Override
                public HashMap<String, Object> then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                    HttpsCallableResult result = task.getResult();
                    HashMap<String, Object> resultData = (HashMap<String, Object>) result.getData();
                    return resultData;
                }
            });
    }

    public Task<Boolean> disiscrizione() {

        String idCasa = this.sharedPreferences.getIdCasa();

        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);

        return this.mFunctions
                .getHttpsCallable("disiscrizione")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

    public Task<Boolean> modificaPassword(String newPassword, String newPasswordRepeat) {

        Map<String, Object> data = new HashMap<>();
        data.put("newPassword", newPassword);
        data.put("newPasswordRepeat", newPasswordRepeat);

        return this.mFunctions
                .getHttpsCallable("modificaPassword")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }


    public Task<List<Torneo>> elencoTornei() {
        return this.mFunctions
                .getHttpsCallable("elencoTornei")
                .call()
                .continueWith(new Continuation<HttpsCallableResult, List<Torneo>>() {
                    @Override
                    public List<Torneo> then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Map<String, Object> resultData = (Map<String, Object>) result.getData();

                        List<Torneo> listaTornei = new ArrayList<Torneo>();

                        if( (Boolean)resultData.get("error") ) {
                            return listaTornei;
                        }

                        for( Map<String, Object> torneoSingolo : (List<Map<String, Object>>) resultData.get("tornei") ) {
                            //
                            String id = (String)torneoSingolo.get("id");
                            String titolo = (String)torneoSingolo.get("titolo");
                            String categoria = (String)torneoSingolo.get("categoria");
                            String indirizzo = (String)torneoSingolo.get("indirizzo");
                            String regolamento = (String)torneoSingolo.get("regolamento");
                            Object dataOra = torneoSingolo.get("dataOra");

                            // TODO: Modificare la creazione della data.
                            listaTornei.add( new Torneo(id, titolo, categoria, indirizzo, new Date(), regolamento) );
                        }

                        return listaTornei;
                    }
                });
    }

    public Task<List<Torneo>> storicoTornei () {
        return this.mFunctions
                .getHttpsCallable("storicoTornei")
                .call()
                .continueWith(new Continuation<HttpsCallableResult, List<Torneo>>() {
                    @Override
                    public List<Torneo> then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Map<String, Object> resultData = (Map<String, Object>) result.getData();

                        List<Torneo> listaTornei = new ArrayList<Torneo>();

                        if( (Boolean)resultData.get("error") ) {
                            return listaTornei;
                        }

                        for( Map<String, Object> torneoSingolo : (List<Map<String, Object>>) resultData.get("tornei") ) {
                            //
                            String id = (String)torneoSingolo.get("id");
                            String titolo = (String)torneoSingolo.get("titolo");
                            String categoria = (String)torneoSingolo.get("categoria");
                            String indirizzo = (String)torneoSingolo.get("indirizzo");
                            String regolamento = (String)torneoSingolo.get("regolamento");
                            Object dataOra = torneoSingolo.get("dataOra");

                            // TODO: Modificare la creazione della data.
                            listaTornei.add( new Torneo(id, titolo, categoria, indirizzo, new Date(), regolamento) );
                        }

                        return listaTornei;
                    }
                });
    }

    public Task<Boolean> partecipaTorneo(String idTorneo) {

        Map<String, Object> data = new HashMap<>();
        data.put("idTorneo", idTorneo);

        return this.mFunctions
                .getHttpsCallable("partecipaTorneo")
                .call( data )
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        HttpsCallableResult result = task.getResult();
                        Boolean resultData = (Boolean) result.getData();
                        return resultData;
                    }
                });
    }

}
