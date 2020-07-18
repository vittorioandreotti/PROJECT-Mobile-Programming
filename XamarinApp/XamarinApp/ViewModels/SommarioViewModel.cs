using Newtonsoft.Json.Linq;
using System;
using Rg.Plugins.Popup.Services;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using Xamarin.Forms;
using System.Threading.Tasks;
using XamarinApp.Models.Helpers;
using XamarinApp.LoadingService;
using XamarinApp.Utils;

namespace XamarinApp.ViewModels
{
    public class SommarioViewModel
    {
        public IList<Spesa> CardData { get; set; }
        UtentePreferences utentePreferences;
        public object SelectedItem { get; set; }
        public SommarioViewModel()
        {
            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });

            utentePreferences = new UtentePreferences();
            FirebaseFunctionHelper firebaseFunctionHelper = new FirebaseFunctionHelper();
            Task<List<Spesa>> taskListaSpese;
            CardData = new ObservableCollection<Spesa>();

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
                List<Spesa> listaSommario = task.Result;
               
                foreach (Spesa spesa in listaSommario)
                {
                    CardData.Add(spesa);
                }

                Device.BeginInvokeOnMainThread(() => {
                    StopLoading();
                });
            });
        }
        private async void StartLoading()
        {
            LoadingPage loadingPage = new LoadingPage();

            await PopupNavigation.Instance.PushAsync(loadingPage);
        }

        private async void StopLoading()
        {
            await PopupNavigation.Instance.PopAsync();
        }
    }
}



