package it.univpm.mobile_programming_project.utils.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.fragment.tornei.PartecipaTorneoFragment;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.models.Torneo;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

public class FirebaseFunctionsHelper implements IFirebaseFunctionsHelper {

    private UtenteSharedPreferences sharedPreferences;
    private FirebaseFunctions mFunctions;

    public FirebaseFunctionsHelper()
    {
        this.mFunctions = FirebaseFunctions.getInstance();
    }


    public FirebaseFunctionsHelper(UtenteSharedPreferences sharedPreferences) {
        this();
        this.sharedPreferences = sharedPreferences;
    }

    @Override
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

    @Override
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


    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Task<Boolean> inserisciTorneo(String titolo, String indirizzo, String categoria, String regolamento, String dataEvento, String oraEvento) {

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

    @Override
    public Task<Boolean> inserisciSpesaBolletta(Double importo, String categoria, String stringDataBolletta, String stringDataScadenza) {

        String idCasa = this.sharedPreferences.getIdCasa();

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("categoria", categoria);
        data.put("prezzo", importo);
        data.put("dataInserimento", stringDataBolletta );
        data.put("dataScadenza", stringDataScadenza );
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

    @Override
    public Task<Boolean> inserisciSpesaAffitto(Double importo, String titolo, String stringDataAffitto, String stringDataScadenza) {

        String idCasa = this.sharedPreferences.getIdCasa();

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("titolo", titolo);
        data.put("prezzo", importo);
        data.put("dataInserimento", stringDataAffitto );
        data.put("dataScadenza", stringDataScadenza );
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

    @Override
    public Task<Boolean> inserisciSpesaCondominio(Double importo, String nome, String stringDataAffitto) {

        String idCasa = this.sharedPreferences.getIdCasa();

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("nome", nome);
        data.put("prezzo", importo);
        data.put("dataInserimento", stringDataAffitto );
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

    @Override
    public Task<Boolean> inserisciSpesaComune(Double importo, String nome, String stringDataSpesa, String descrizione) {

        String idCasa = this.sharedPreferences.getIdCasa();

        // Call the function and extract the result
        // exports.getUserInfo
        Map<String, Object> data = new HashMap<>();
        data.put("idCasa", idCasa);
        data.put("nome", nome);
        data.put("prezzo", importo);
        data.put("dataInserimento", stringDataSpesa );
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


    @Override
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

    @Override
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

    @Override
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

    @Override
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
                            Torneo torneo = new Torneo();
                            torneo.createFromHashMap( torneoSingolo );
                            listaTornei.add( torneo );
                        }

                        return listaTornei;
                    }
                });
    }

    @Override
    public Task<List<Torneo>> storicoTornei() {
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
                            Torneo torneo = new Torneo();
                            torneo.createFromHashMap( torneoSingolo );
                            listaTornei.add( torneo );
                        }

                        return listaTornei;
                    }
                });
    }

    @Override
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

    @Override
    public Task<Map<String, List<Spesa>>> elencoSpeseAffittuario(final Context context) {

        Map<String, Object> dataInput = new HashMap<>();
        dataInput.put("idCasa", this.sharedPreferences.getIdCasa());

        final String idUtente = this.sharedPreferences.getIdUtente();

        return this.mFunctions
                .getHttpsCallable("elencoSpese")
                .withTimeout(30, TimeUnit.SECONDS)
                .call(dataInput)
                .continueWith(new Continuation<HttpsCallableResult, Map<String, List<Spesa>>>() {
                    @Override
                    public Map<String, List<Spesa>> then(@NonNull Task<HttpsCallableResult> task) throws Exception {

                        Map<String, List<Spesa>> speseTotali = new HashMap<>();

                        List<Map<String, Object>> tmpSpeseList = new ArrayList<>();

                        if(!task.isSuccessful()) {
                            Exception e = task.getException();
                            if (e instanceof FirebaseFunctionsException) {
                                FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                                FirebaseFunctionsException.Code code = ffe.getCode();
                                Object details = ffe.getDetails();
                            }
                            return speseTotali;
                        }

                        HttpsCallableResult result = task.getResult();
                        Map<String, Object> resultData = (Map<String, Object>) result.getData();

                        if( (Boolean)resultData.get("error") ) {
                            Toast.makeText(context, (String)resultData.get("errorMessage"), Toast.LENGTH_LONG).show();
                            return speseTotali;
                        }

                        // Sommario
                        List<Spesa> listaSpeseSommario = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("sommario")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("sommario")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            if(spesaSingola.get("idUtente").equals(idUtente)){
                                Spesa spesa = new Spesa();
                                spesa.createFromHashMap( spesaSingola );
                                listaSpeseSommario.add( spesa );
                            }
                        }
                        tmpSpeseList.clear();

                        // Affitto
                        List<Spesa> listaSpeseAffitto = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            if(spesaSingola.get("idUtente").equals(idUtente)){
                                Spesa spesa = new Spesa();
                                spesa.createFromHashMap( spesaSingola );
                                listaSpeseAffitto.add( spesa );
                            }
                        }
                        tmpSpeseList.clear();

                        // Bollette
                        List<Spesa> listaSpeseBollette = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            if(spesaSingola.get("idUtente").equals(idUtente)){
                                Spesa spesa = new Spesa();
                                spesa.createFromHashMap( spesaSingola );
                                listaSpeseBollette.add( spesa );
                            }
                        }
                        tmpSpeseList.clear();

                        // Comune
                        List<Spesa> listaSpeseComune = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            if(spesaSingola.get("idUtente").equals(idUtente)){
                                Spesa spesa = new Spesa();
                                spesa.createFromHashMap( spesaSingola );
                                listaSpeseComune.add( spesa );
                            }
                        }
                        tmpSpeseList.clear();

                        // Condominio
                        List<Spesa> listaSpeseCondominio = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("condominio")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("condominio")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            if(spesaSingola.get("idUtente").equals(idUtente)){
                                Spesa spesa = new Spesa();
                                spesa.createFromHashMap( spesaSingola );
                                listaSpeseCondominio.add( spesa );
                            }
                        }
                        tmpSpeseList.clear();

                        speseTotali.put("sommario", listaSpeseSommario);
                        speseTotali.put("affitto", listaSpeseAffitto);
                        speseTotali.put("bollette", listaSpeseBollette);
                        speseTotali.put("condominio", listaSpeseCondominio);
                        speseTotali.put("comune", listaSpeseComune);

                        return speseTotali;
                    }
                });
    }

    @Override
    public Task<Map<String, List<Spesa>>> elencoSpeseProprietario(final Context context) {

        Map<String, Object> dataInput = new HashMap<>();
        dataInput.put("idCasa", this.sharedPreferences.getIdCasa());


        return this.mFunctions
                .getHttpsCallable("elencoSpese")
                .withTimeout(30, TimeUnit.SECONDS)
                .call(dataInput)
                .continueWith(new Continuation<HttpsCallableResult, Map<String, List<Spesa>>>() {
                    @Override
                    public Map<String, List<Spesa>> then(@NonNull Task<HttpsCallableResult> task) throws Exception {

                        Map<String, List<Spesa>> speseTotali = new HashMap<>();

                        List<Map<String, Object>> tmpSpeseList = new ArrayList<>();

                        if(!task.isSuccessful()) {
                            Exception e = task.getException();
                            if (e instanceof FirebaseFunctionsException) {
                                FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                                FirebaseFunctionsException.Code code = ffe.getCode();
                                Object details = ffe.getDetails();
                            }
                            return speseTotali;
                        }

                        HttpsCallableResult result = task.getResult();
                        Map<String, Object> resultData = (Map<String, Object>) result.getData();

                        if( (Boolean)resultData.get("error") ) {
                            Toast.makeText(context, (String)resultData.get("errorMessage"), Toast.LENGTH_LONG).show();
                            return speseTotali;
                        }

                        // Sommario
                        List<Spesa> listaSpeseSommario = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("sommario")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("sommario")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            Spesa spesa = new Spesa();
                            spesa.createFromHashMap( spesaSingola );
                            listaSpeseSommario.add( spesa );
                        }
                        tmpSpeseList.clear();

                        // Affitto
                        List<Spesa> listaSpeseAffitto = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            Spesa spesa = new Spesa();
                            spesa.createFromHashMap( spesaSingola );
                            listaSpeseAffitto.add( spesa );
                        }
                        tmpSpeseList.clear();

                        // Bollette
                        List<Spesa> listaSpeseBollette = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            Spesa spesa = new Spesa();
                            spesa.createFromHashMap( spesaSingola );
                            listaSpeseBollette.add( spesa );
                        }
                        tmpSpeseList.clear();

                        // Comune
                        List<Spesa> listaSpeseComune = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            Spesa spesa = new Spesa();
                            spesa.createFromHashMap( spesaSingola );
                            listaSpeseComune.add( spesa );
                        }
                        tmpSpeseList.clear();

                        // Condominio
                        List<Spesa> listaSpeseCondominio = new ArrayList<Spesa>();
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("condominio")).get("daPagare") );
                        tmpSpeseList.addAll( (List<Map<String, Object>>)((Map<String, Object>)resultData.get("condominio")).get("pagate") );
                        for( Map<String, Object> spesaSingola : tmpSpeseList ) {
                            Spesa spesa = new Spesa();
                            spesa.createFromHashMap( spesaSingola );
                            listaSpeseCondominio.add( spesa );
                        }
                        tmpSpeseList.clear();

                        speseTotali.put("sommario", listaSpeseSommario);
                        speseTotali.put("affitto", listaSpeseAffitto);
                        speseTotali.put("bollette", listaSpeseBollette);
                        speseTotali.put("condominio", listaSpeseCondominio);
                        speseTotali.put("comune", listaSpeseComune);

                        return speseTotali;
                    }
                });
    }

    @Override
    public Task<Boolean> pagaSpesa(String idSpesa, String idCasa) {

        Map<String, Object> data = new HashMap<>();
        data.put("idSpesa", idSpesa);
        data.put("idCasa", idCasa);


        return this.mFunctions
                .getHttpsCallable("pagaSpesa")
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
