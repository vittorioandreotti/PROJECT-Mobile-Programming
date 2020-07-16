using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using XamarinApp.Models.Helpers;
using XamarinApp.Utils.Interfaces;

namespace XamarinApp.Utils
{
    class FirebaseFunctionHelper : IFirebaseFunctionHelper
    {


        /*

        private Preferences Preferences;
        private FirebaseFunctions MFunctions;

        public FirebaseFunctionHelper()
        {
            this.MFunctions = FirebaseFunctions.getInstance();
        }

        public Task<Boolean> IsUserInitialized()
        {

            // Call the function and extract the result
            // exports.getUserInfo

            return this.MFunctions
                    .getHttpsCallable("isUserInitialized")
                    .call()
                    .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
                HttpsCallableResult Result = Task.getResult();
                Boolean ResultData = (Boolean)Result.getData();
                return ResultData;
            }
        });

    }

    public Task<Boolean> CreaCasa(String Nome, String Indirizzo)
    {

        // Call the function and extract the result
        // exports.getUserInfo
        Dictionary<String, Object> Data = new Dictionary<String, Object>();
        Data["nome"]= Nome;
        Data["indirizzo"]= Indirizzo;

        return this.MFunctions
                .getHttpsCallable("creaCasa")
                .call(Data)
                .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
            HttpsCallableResult Result = Task.getResult();
            Boolean ResultData = (Boolean)Result.getData();
            return ResultData;
        }
    });
    }


public Task<Boolean> InserisciProprietario()
{

    return this.MFunctions
            .getHttpsCallable("inserisciProprietario")
            .call()
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    public Task<Boolean> PartecipaCasa(String IdCasa)
{

    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["idCasa"] = IdCasa;

    return this.mFunctions
            .getHttpsCallable("partecipaCasa")
            .call(Data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > task) throws Exception {
        HttpsCallableResult result = Task.getResult();
        Boolean resultData = (Boolean)result.getData();
        return resultData;
    }
});
    }

    public Task<Boolean> InserisciAffittuario()
{

    return this.MFunctions
            .getHttpsCallable("inserisciAffittuario")
            .call()
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    public Task<Boolean> registraUtente(String Nome, String Cognome)
{

    Dictionary<String, Object> DataInput = new Dictionary<String, Object>();
    DataInput["nome"]= Nome;
    DataInput["cognome"]= Cognome;

    return this.MFunctions
            .getHttpsCallable("registraUtente")
            .call(DataInput)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    

    public Task<Boolean> InserisciSpesaBolletta(Double Importo, String Categoria, String StringDataBolletta, String StringDataScadenza)
{

    String IdCasa = Preferences.GetIdCasa();

    // Call the function and extract the result
    // exports.getUserInfo
    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["idCasa"] = IdCasa;
    Data["categoria"] = Categoria;
    Data["prezzo"] = Importo;
    Data["dataInserimento"] = StringDataBolletta;
    Data["dataScadenza"] = StringDataScadenza;
    Data["tipoSpesa"] = "bolletta";
     
    return this.MFunctions
            .getHttpsCallable("inserisciSpesa")
            .call(Data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    public Task<Boolean> InserisciSpesaAffitto(Double Importo, String Titolo, String StringDataAffitto, String StringDataScadenza)
{

    String IdCasa = Preferences.GetIdCasa();

    // Call the function and extract the result
    // exports.getUserInfo
    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["idCasa"] = IdCasa;
    Data["titolo"] = Titolo;
    Data["prezzo"] = Importo;
    Data["dataInserimento"] = StringDataAffitto;
    Data["dataScadenza"] = StringDataScadenza;
    Data["tipoSpesa"] = "affitto";

    return this.MFunctions
            .getHttpsCallable("inserisciSpesa")
            .call(data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    public Task<Boolean> InserisciSpesaCondominio(Double Importo, String Nome, String StringDataAffitto)
{

    String IdCasa = Preferences.GetIdCasa();

    // Call the function and extract the result
    // exports.getUserInfo
    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["idCasa"] = IdCasa;
    Data["nome"] = Nome;
    Data["prezzo"] = Importo;
    Data["dataInserimento"] = StringDataAffitto;
    Data["tipoSpesa"] = "condominio";

    return this.MFunctions
            .getHttpsCallable("inserisciSpesa")
            .call(Data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    public Task<Boolean> InserisciSpesaComune(Double Importo, String Nome, String StringDataSpesa, String Descrizione)
{

    String IdCasa = Preferences.GetIdCasa();

    // Call the function and extract the result
    // exports.getUserInfo
    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["idCasa"] = IdCasa;
    Data["nome"] = Nome;
    Data["prezzo"] = Importo;
    Data["dataInserimento"] = StringDataSpesa;
    Data["descrizione"] = Descrizione;
    Data["tipoSpesa"] = "comune";

    return this.MFunctions
            .getHttpsCallable("inserisciSpesa")
            .call(Data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }


    public Task<Dictionary<String, Object>> GetUtenteAndCasa()
{

    return this.MFunctions
            .getHttpsCallable("getUtenteAndCasa")
            .call()
            .continueWith(new Continuation<HttpsCallableResult, HashMap<String, Object>>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    public Task<Boolean> Disiscrizione()
{

    String IdCasa = Preferences.GetIdCasa();

    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["idCasa"] = IdCasa;

    return this.MFunctions
            .getHttpsCallable("disiscrizione")
            .call(Data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    public Task<Boolean> ModificaPassword(String NewPassword, String NewPasswordRepeat)
{

    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["newPassword"] = NewPassword;
    Data["newPasswordRepeat"] = NewPasswordRepeat;

    return this.MFunctions
            .getHttpsCallable("modificaPassword")
            .call(Data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }

    

    public Task<Dictionary<String, List<Spesa>>> ElencoSpeseAffittuario(sealed Context Context)
{

    Dictionary<String, Object> DataInput = new Dictionary<String, Object>();
    String IdCasa = Preferences.GetIdCasa();

    sealed String IdUtente = Preferences.GetIdUtente();

    return this.MFunctions
            .getHttpsCallable("elencoSpese")
            .withTimeout(30, TimeUnit.SECONDS)
            .call(DataInput)
            .continueWith(new Continuation<HttpsCallableResult, Dictionary<String, List<Spesa>>>() {
                    @Override
                    public Dictionary<String, List<Spesa>> then(@NonNull Task< HttpsCallableResult > Task) throws Exception {

        Dictionary<String, List<Spesa>> SpeseTotali = new Dictionary<>();

        List<Dictionary<String, Object>> TmpSpeseList = new ArrayList<>();

        if (!task.isSuccessful())
        {
            Exception e = task.getException();
            if (e instanceof FirebaseFunctionsException) {
                FirebaseFunctionsException ffe = (FirebaseFunctionsException)e;
                FirebaseFunctionsException.Code code = ffe.getCode();
                Object details = ffe.getDetails();
            }
            return SpeseTotali;
        }

        HttpsCallableResult Result = Task.getResult();
        Dictionary<String, Object> ResultData = (Dictionary<String, Object>)Result.getData();

        if ((Boolean)ResultData.get("error"))
        {
            Toast.makeText(Context, (String)ResultData.get("errorMessage"), Toast.LENGTH_LONG).show();
            return SpeseTotali;
        }

        // Sommario
        List<Spesa> ListaSpeseSommario = new ArrayList<Spesa>();
        TmpSpeseList.addAll((List<Dictionary<String, Object>>)((Dictionary<String, Object>)ResultData.get("sommario")).get("daPagare"));
        TmpSpeseList.addAll((List<Dictionary<String, Object>>)((Dictionary<String, Object>)ResultData.get("sommario")).get("pagate"));
        for (Dictionary<String, Object> SpesaSingola : TmpSpeseList)
        {
            if (SpesaSingola.get("idUtente").equals(IdUtente))
            {
                Spesa Spesa = new Spesa();
                Spesa.createFromHashMap(SpesaSingola);
                ListaSpeseSommario.Add(Spesa);
            }
        }
        TmpSpeseList.clear();

        // Affitto
        List<Spesa> listaSpeseAffitto = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            if (spesaSingola.get("idUtente").equals(idUtente))
            {
                Spesa spesa = new Spesa();
                spesa.createFromHashMap(spesaSingola);
                listaSpeseAffitto.add(spesa);
            }
        }
        tmpSpeseList.clear();

        // Bollette
        List<Spesa> listaSpeseBollette = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            if (spesaSingola.get("idUtente").equals(idUtente))
            {
                Spesa Spesa = new Spesa();
                Spesa.createFromHashMap(spesaSingola);
                listaSpeseBollette.Add(Spesa);
            }
        }
        tmpSpeseList.clear();

        // Comune
        List<Spesa> listaSpeseComune = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            if (SpesaSingola.get("idUtente").equals(IdUtente))
            {
                Spesa Spesa = new Spesa();
                Spesa.createFromHashMap(SpesaSingola);
               ListaSpeseComune.Add(Spesa);
            }
        }
        tmpSpeseList.clear();

        // Condominio
        List<Spesa> listaSpeseCondominio = new ArrayList<Spesa>();
        TmpSpeseList.addAll((List<Dictionary<String, Object>>)((Dictionary<String, Object>)ResultData.get("condominio")).get("daPagare"));
        TmpSpeseList.addAll((List<Dictionary<String, Object>>)((Dictionary<String, Object>)ResultData.get("condominio")).get("pagate"));
        for (Dictionary<String, Object> SpesaSingola : TmpSpeseList)
        {
            if (SpesaSingola.get("idUtente").equals(IdUtente))
            {
                Spesa Spesa = new Spesa();
                Spesa.createFromHashMap(SpesaSingola);
                ListaSpeseCondominio.Add(Spesa);
            }
        }
        tmpSpeseList.clear();

        SpeseTotali["sommario"] = ListaSpeseSommario;
        SpeseTotali["affitto"] = ListaSpeseAffitto;
        SpeseTotali["bollette"] = ListaSpeseBollette;
        SpeseTotali["condominio"] = ListaSpeseCondominio;
        SpeseTotali["comune"] = ListaSpeseComune;

        return SpeseTotali;
    }
});
    }

    public Task<Dictionary<String, List<Spesa>>> ElencoSpeseProprietario(sealed Context Context)
{

    Dictionary<String, Object> DataInput = new Dictionary<String, Object>();
String IdCasa = Preferences.GetIdCasa();


    return this.mFunctions
            .getHttpsCallable("elencoSpese")
            .withTimeout(30, TimeUnit.SECONDS)
            .call(dataInput)
            .continueWith(new Continuation<HttpsCallableResult, Map<String, List<Spesa>>>() {
                    @Override
                    public Map<String, List<Spesa>> then(@NonNull Task< HttpsCallableResult > task) throws Exception {

        Map<String, List<Spesa>> speseTotali = new HashMap<>();

        List<Map<String, Object>> tmpSpeseList = new ArrayList<>();

        if (!task.isSuccessful())
        {
            Exception e = task.getException();
            if (e instanceof FirebaseFunctionsException) {
                FirebaseFunctionsException ffe = (FirebaseFunctionsException)e;
                FirebaseFunctionsException.Code code = ffe.getCode();
                Object details = ffe.getDetails();
            }
            return speseTotali;
        }

        HttpsCallableResult result = task.getResult();
        Map<String, Object> resultData = (Map<String, Object>)result.getData();

        if ((Boolean)resultData.get("error"))
        {
            Toast.makeText(context, (String)resultData.get("errorMessage"), Toast.LENGTH_LONG).show();
            return speseTotali;
        }

        // Sommario
        List<Spesa> listaSpeseSommario = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("sommario")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("sommario")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            Spesa spesa = new Spesa();
            spesa.createFromHashMap(spesaSingola);
            listaSpeseSommario.add(spesa);
        }
        tmpSpeseList.clear();

        // Affitto
        List<Spesa> listaSpeseAffitto = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("affitto")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            Spesa spesa = new Spesa();
            spesa.createFromHashMap(spesaSingola);
            listaSpeseAffitto.add(spesa);
        }
        tmpSpeseList.clear();

        // Bollette
        List<Spesa> listaSpeseBollette = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("bolletta")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            Spesa spesa = new Spesa();
            spesa.createFromHashMap(spesaSingola);
            listaSpeseBollette.add(spesa);
        }
        tmpSpeseList.clear();

        // Comune
        List<Spesa> listaSpeseComune = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("comune")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            Spesa spesa = new Spesa();
            spesa.createFromHashMap(spesaSingola);
            listaSpeseComune.add(spesa);
        }
        tmpSpeseList.clear();

        // Condominio
        List<Spesa> listaSpeseCondominio = new ArrayList<Spesa>();
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("condominio")).get("daPagare"));
        tmpSpeseList.addAll((List<Map<String, Object>>)((Map<String, Object>)resultData.get("condominio")).get("pagate"));
        for (Map<String, Object> spesaSingola : tmpSpeseList)
        {
            Spesa spesa = new Spesa();
            spesa.createFromHashMap(spesaSingola);
            listaSpeseCondominio.add(spesa);
        }
        tmpSpeseList.clear();

        SpeseTotali["sommario"] = ListaSpeseSommario;
        SpeseTotali["affitto"] = ListaSpeseAffitto;
        SpeseTotali["bollette"] = ListaSpeseBollette;
        SpeseTotali["condominio"] = ListaSpeseCondominio;
        SpeseTotali["comune"] = ListaSpeseComune;

        return speseTotali;
    }
});
    }

    public Task<Boolean> PagaSpesa(String IdSpesa, String IdCasa)
{

    Dictionary<String, Object> Data = new Dictionary<String, Object>();
    Data["idSpesa"] = IdSpesa;
    Data["idCasa"] = IdCasa;


    return this.MFunctions
            .getHttpsCallable("pagaSpesa")
            .call(Data)
            .continueWith(new Continuation<HttpsCallableResult, Boolean>() {
                    @Override
                    public Boolean then(@NonNull Task< HttpsCallableResult > Task) throws Exception {
        HttpsCallableResult Result = Task.getResult();
        Boolean ResultData = (Boolean)Result.getData();
        return ResultData;
    }
});
    }
    */
        public Task<bool> CreaCasa(string nome, string indirizzo)
        {
            throw new NotImplementedException();
        }

        public Task<bool> Disiscrizione()
        {
            throw new NotImplementedException();
        }



        //Elenco spese Affittuario
        public Task<List<Spesa>> ElencoSommarioAffittuario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["sommario"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                        if (idUtente.Equals(spesaobj.IdUtente))
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }

        public Task<List<Spesa>> ElencoAffittoAffittuario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["affitto"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                        if (idUtente.Equals(spesaobj.IdUtente))
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }

        public Task<List<Spesa>> ElencoSpesaComuneAffittuario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["comune"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                        if (idUtente.Equals(spesaobj.IdUtente))
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }

        public Task<List<Spesa>> ElencoSpesaCondominioAffittuario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["condominio"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                        if (idUtente.Equals(spesaobj.IdUtente))
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }
   
        public Task< List<Spesa>> ElencoBolletteAffittuario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["bolletta"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                        if( idUtente.Equals(spesaobj.IdUtente) )
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }


        //Elenco spese Proprietario
        public Task<List<Spesa>> ElencoSommarioProprietario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["sommario"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }

        public Task<List<Spesa>> ElencoAffittoProprietario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["affitto"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }

        public Task<List<Spesa>> ElencoSpesaComuneProprietario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["comune"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }

        public Task<List<Spesa>> ElencoSpesaCondominioProprietario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["condominio"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }

        public Task<List<Spesa>> ElencoBolletteProprietario()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoSpese");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call(inputData).ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;
                List<Spesa> listSpesa = new List<Spesa>();
                string idUtente = utentePreferences.GetIdUtente();

                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    JObject tuttleLeSpeseObj = (JObject)cloudResponse.JsonData["bolletta"];

                    JArray tutteLeSpeseArr = new JArray();
                    JArray daPagareArr = (JArray)tuttleLeSpeseObj["daPagare"];
                    JArray pagateArr = (JArray)tuttleLeSpeseObj["pagate"];

                    tutteLeSpeseArr.Add(daPagareArr);
                    tutteLeSpeseArr.Add(pagateArr);

                    foreach (JObject spesa in tutteLeSpeseArr)
                    {
                        Spesa spesaobj = new Spesa();
                        spesaobj.CreateFromHashMap(spesa);
                            listSpesa.Add(spesaobj);
                    }
                }
                return listSpesa;
            });
        }




        public Task<Dictionary<string, object>> GetUtenteAndCasa()
        {
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("getUtenteAndCasa");
            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                Dictionary<string, object> utenteAndCasaData = new Dictionary<string, object>();


                if (cloudResponse.HasError)
                {
                    //
                }
                else
                {
                    //
                }
                return utenteAndCasaData;
            });
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

        public Task<bool> PartecipaCasa(string idCasa)
        {
            throw new NotImplementedException();

        }

       

        public Task<bool> RegistraUtente(string nome, string cognome)
        {
            throw new NotImplementedException();
        }

       
    }
}
