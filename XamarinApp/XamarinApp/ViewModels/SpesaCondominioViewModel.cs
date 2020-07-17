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
    public class SpesaCondominioViewModel
    {
        public IList<Spesa> CardData { get; set; }
        UtentePreferences utentePreferences;
        public object SelectedItem { get; set; }
        public SpesaCondominioViewModel()
        {
            utentePreferences = new UtentePreferences();
            FirebaseFunctionHelper firebaseFunctionHelper = new FirebaseFunctionHelper();
            Task<List<Spesa>> taskListaSpese;

            if (utentePreferences.IsAffittuario())
            {
                taskListaSpese = firebaseFunctionHelper.ElencoSpesaCondominioAffittuario();
            }
            else
            {
                taskListaSpese = firebaseFunctionHelper.ElencoSpesaCondominioProprietario();
            }
            taskListaSpese.ContinueWith((Task<List<Spesa>> task) =>
            {
                task.Wait();
                List<Spesa> listaSpesaComune = task.Result;
                CardData = new ObservableCollection<Spesa>();

                foreach (Spesa spesa in listaSpesaComune)
                {
                    CardData.Add(spesa);
                }
            });
        }
    }
}



