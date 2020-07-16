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
    public class SommarioViewModel
    {
        public IList<Spesa> CardData { get; set; }
        UtentePreferences utentePreferences;
        public SommarioViewModel()
        {
            utentePreferences = new UtentePreferences();
            FirebaseFunctionHelper firebaseFunctionHelper = new FirebaseFunctionHelper();
            Task<List<Spesa>> taskListaSpese;

            if (utentePreferences.IsAffittuario())
            {
                taskListaSpese = firebaseFunctionHelper.ElencoSommarioAffittuario();
            }
            else
            {
                taskListaSpese = firebaseFunctionHelper.ElencoSommarioProprietario();
            }
            taskListaSpese.ContinueWith((Task<List<Spesa>> task) =>
            {
                task.Wait();
                List<Spesa> listaSpesaComune = task.Result;

                foreach (Spesa spesa in listaSpesaComune)
                {
                    CardData.Add(spesa);
                }
            });
        }
    }
}



