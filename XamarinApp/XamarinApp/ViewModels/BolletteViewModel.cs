using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using System.Threading.Tasks;
using XamarinApp.Models.Helpers;
using XamarinApp.Utils;

namespace XamarinApp.ViewModels
{
    public class BolletteViewModel
    {
        public IList<Spesa> CardData { get; set; }
        UtentePreferences utentePreferences;
        public BolletteViewModel ()
        {
            utentePreferences = new UtentePreferences();
            Dictionary<string, object> inputData = new Dictionary<string, object>();
            HttpsFunctionsCaller httpsFunctionsCaller = new HttpsFunctionsCaller("elencoSpese");
            inputData.Add("idCasa", utentePreferences.GetIdCasa());
            httpsFunctionsCaller.Call(inputData).ContinueWith(GenerateBolletteModel);
        }

        private void GenerateBolletteModel(Task<CloudFunctionResponse> taskResponse)
        {
            taskResponse.Wait();
            CloudFunctionResponse elencoSpese = taskResponse.Result;
            CardData = new ObservableCollection<Spesa>();
            foreach (JObject spesa in (JArray)elencoSpese.JsonData["spese"])
            {
                if(((string)spesa["tipo"]).Equals("bolletta"))
                {
                    CardData.Add(new Spesa
                    {
                        Categoria = (string)spesa["categoria"],
                        Prezzo = (double)spesa["prezzo"],
                        DataInserimento = Helper.FromMillisToDate((long)spesa["dataInserimento"]),
                        DataScadenza = Helper.FromMillisToDate((long)spesa["dataScadenza"]),
                        DataPagamento = Helper.FromMillisToDate((long)spesa["dataPagamento"]),
                    });
                }
            }
        }
    }
}
