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


        public Task<bool> CreaCasa(string nome, string indirizzo)
        {
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("creaCasa");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("nome", nome);
            inputData.Add("indirizzo", indirizzo);


            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> Disiscrizione()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("disiscrizione");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
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
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("inserisciAffittuario");

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> InserisciProprietario()
        {
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("inserisciProprietario");

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;               

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;                   
                }
            });
        }

        public Task<bool> InserisciSpesaAffitto(double importo, string titolo, string stringDataAffitto, string stringDataScadenza)
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("inserisciSpesa");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());
            inputData.Add("prezzo", importo);
            inputData.Add("titolo", titolo);
            inputData.Add("dataInserimento", stringDataAffitto);
            inputData.Add("dataScadenza", stringDataScadenza); 
            inputData.Add("tipoSpesa", "affitto");

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> InserisciSpesaBolletta(double importo, string categoria, string stringDataBolletta, string stringDataScadenza)
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("inserisciSpesa");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());
            inputData.Add("prezzo", importo);
            inputData.Add("categoria", categoria);
            inputData.Add("dataInserimento", stringDataBolletta);
            inputData.Add("dataScadenza", stringDataScadenza);
            inputData.Add("tipoSpesa", "bolletta");

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> InserisciSpesaComune(double importo, string nome, string stringDataSpesa, string descrizione)
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("inserisciSpesa");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());
            inputData.Add("prezzo", importo);
            inputData.Add("nome", nome);
            inputData.Add("dataInserimento", stringDataSpesa);
            inputData.Add("descrizione", descrizione);
            inputData.Add("tipoSpesa", "comune");

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> InserisciSpesaCondominio(double importo, string nome, string stringDataAffitto)
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("inserisciSpesa");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());
            inputData.Add("prezzo", importo);
            inputData.Add("nome", nome);
            inputData.Add("dataInserimento", stringDataAffitto);
            inputData.Add("tipoSpesa", "condominio");

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        

        public Task<bool> IsUserInitialized()
        {
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("isUserInitialized");

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> ModificaPassword(string newPassword, string newPasswordRepeat)
        {           
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("modificaPassword");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("newPassword", newPassword);
            inputData.Add("newPasswordRepeat", newPasswordRepeat);

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> PagaSpesa(string idSpesa)
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("pagaSpesa");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idSpesa", idSpesa);
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

        public Task<bool> PartecipaCasa()
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("partecipaCasa");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("idCasa", utentePreferences.GetIdCasa());

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });

        }

       

        public Task<bool> RegistraUtente(string nome, string cognome)
        {
            UtentePreferences utentePreferences = new UtentePreferences();
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("registraUtente");

            Dictionary<string, object> inputData = new Dictionary<string, object>();
            inputData.Add("nome", nome);
            inputData.Add("cognome", cognome);

            return functionsCaller.Call().ContinueWith((Task<CloudFunctionResponse> taskCloudResponse) => {

                taskCloudResponse.Wait();
                CloudFunctionResponse cloudResponse = taskCloudResponse.Result;

                if (cloudResponse.HasError)
                {
                    return false;
                }
                else
                {
                    return cloudResponse.BooleanData;
                }
            });
        }

       
    }
}
