using Newtonsoft.Json.Linq;
using Rg.Plugins.Popup.Services;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using XamarinApp.LoadingService;
using XamarinApp.Models.Helpers;
using XamarinApp.Utils;

namespace XamarinApp.ViewModels
{
    public class SpesaComuneViewModel
    {
        public IList<Spesa> CardData { get; set; }
        UtentePreferences utentePreferences;
        public object SelectedItem { get; set; }
        public SpesaComuneViewModel()
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
                taskListaSpese = firebaseFunctionHelper.ElencoSpesaComuneAffittuario();
            }
            else
            {
                taskListaSpese = firebaseFunctionHelper.ElencoSpesaComuneProprietario();
            }
            taskListaSpese.ContinueWith((Task<List<Spesa>> task) =>
             {
                 task.Wait();
                 List<Spesa> listaSpesaComune = task.Result;
                 foreach (Spesa spesa in listaSpesaComune)
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
