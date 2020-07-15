using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using XamarinApp.Models.Helpers;
using XamarinApp.Pages;
using XamarinApp.Utils;

namespace XamarinApp
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        private UtentePreferences utentePreferences;
        
        public MainPage()
        {
            InitializeComponent();

            utentePreferences = new UtentePreferences();
        }

        private async void TestNavigation(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Login());
            // await this.Navigation.PopAsync();
        }

        private void Button_Clicked(object sender, EventArgs e)
        {
            string token = utentePreferences.GetAuthToken();

            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("elencoTornei");

            functionsCaller.Call().ContinueWith(HandleLoginResponse);

        }

        private void HandleLoginResponse( Task<Dictionary<string, Object>> taskDataDictionary )
        {
            taskDataDictionary.Wait();

            Dictionary<string, object> dataDictionary = taskDataDictionary.Result;

            CloudFunctionResponse response = new CloudFunctionResponse(dataDictionary);

            LabelRandom.Text = response.Data.ToString();
        }

        private void Button_Clicked_1(object sender, EventArgs e)
        {
            Task.Factory.StartNew(async () =>
            {
                var initTimer = DateTime.Now;
                var time = DateTime.Now;
                do
                {
                    time = DateTime.Now;
                    await Task.Delay(100);
                    Timer.Text = ( time.Millisecond + time.Second * 1000 ).ToString();
                } while ( (initTimer.AddSeconds(60) - time).TotalSeconds >= 0 );
            });
        }
    }
}
