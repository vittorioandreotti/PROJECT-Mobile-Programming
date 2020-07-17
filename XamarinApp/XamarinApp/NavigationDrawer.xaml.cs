using System;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Pages;
using XamarinApp.Utils;
using XamarinApp.Models.Helpers;
using System.Threading.Tasks;
using XamarinApp.LoadingService;
using Rg.Plugins.Popup.Services;

namespace XamarinApp
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class NavigationDrawer : MasterDetailPage
    {
        List<MenuItems> menu;
        UtentePreferences utentePreferences;

        bool IsMenuReady { get; set; }

        public NavigationDrawer()
        {
            IsMenuReady = false;
            InitializeComponent();
            utentePreferences = new UtentePreferences();
            InitUserData();
        }

        private void InitUserData()
        {

            Device.BeginInvokeOnMainThread(() => {
                StartLoading();
            });

            // Controllo se l'utente ha già le shared preferences settate
            if ( utentePreferences.Contains(UtentePreferences.KeyTipo) )
            {
                Task.Factory.StartNew(InitUI);
                return;
            }

            // Chiamata a cloud functions
            HttpsFunctionsCaller functionsCaller = new HttpsFunctionsCaller("getUtenteAndCasa");
            functionsCaller.Call().ContinueWith(HandleNavDrawerResponse).ContinueWith(InitUI);

        }

        // Lettura dati getUtenteAndCasa in utentePreferences
        private void HandleNavDrawerResponse(Task<CloudFunctionResponse> taskCloudResponse)
        {
            taskCloudResponse.Wait();
            CloudFunctionResponse cloudResponse = taskCloudResponse.Result;        

            if (cloudResponse.HasError)
            {
                //
            }
            else
            {
                JObject casa = (JObject)cloudResponse.JsonData["casa"];
                JObject utente = (JObject)cloudResponse.JsonData["utente"];

                utentePreferences.SetIdCasa((string) casa["id"]);
                utentePreferences.SetNomeCasa((string)casa["nome"]);
                utentePreferences.SetIndirizzoCasa((string)casa["indirizzo"]);
                utentePreferences.SetIdUtente((string)utente["id"]);
                utentePreferences.SetNome((string)utente["nome"]);
                utentePreferences.SetCognome((string)utente["cognome"]);
                utentePreferences.SetTipo((string)utente["tipo"]);
            }
        }

        private void InitUI(Task task)
        {
            task.Wait();
            InitUI();
        }

        private void InitUI()
        {
            menu = new List<MenuItems>();

            if (utentePreferences.IsAffittuario())
            {
                menu.Add(new MenuItems { OptionName = "Home", Icon = "Home" });
                menu.Add(new MenuItems { OptionName = "Gestisci spesa comune", Icon = "Add" });
                menu.Add(new MenuItems { OptionName = "Sommario", Icon = "Sommario.png" });
                menu.Add(new MenuItems { OptionName = "Spese in comune", Icon = "SpesaComune" });
                menu.Add(new MenuItems { OptionName = "Affitto", Icon = "Affitto" });
                menu.Add(new MenuItems { OptionName = "Bollette", Icon = "Bollette" });
                menu.Add(new MenuItems { OptionName = "Spese condominio", Icon = "Condominio" });
                menu.Add(new MenuItems { OptionName = "Profilo", Icon = "Profilo" });
                menu.Add(new MenuItems { OptionName = "Logout", Icon = "Logout" });
            }
            else
            {
                menu.Add(new MenuItems { OptionName = "Home", Icon = "Home" });
                menu.Add(new MenuItems { OptionName = "Sommario", Icon = "Sommario" });
                menu.Add(new MenuItems { OptionName = "Spese condominio", Icon = "Condominio" });
                menu.Add(new MenuItems { OptionName = "Affitto", Icon = "Affitto" });
                menu.Add(new MenuItems { OptionName = "Bollette", Icon = "Bollette" });
                menu.Add(new MenuItems { OptionName = "Codice casa", Icon = "CodiceCasa" });
                menu.Add(new MenuItems { OptionName = "Inserisci spese condominio", Icon = "Add" });
                menu.Add(new MenuItems { OptionName = "Inserisci bollette", Icon = "Add" });
                menu.Add(new MenuItems { OptionName = "Inserisci affitto", Icon = "Add" });
                menu.Add(new MenuItems { OptionName = "Profilo", Icon = "Profilo" });
                menu.Add(new MenuItems { OptionName = "Logout", Icon = "Logout" });
            }

            Detail = new NavigationPage(new Home());

            IsMenuReady = true;
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();

            // Wait for menu to be ready. ( Aspetta un tempo molto basso, non blocca l'UI )
            while (!IsMenuReady) ;
            navigationList.ItemsSource = menu;
            SetNameSurnameAndEmail();

        }

        private void SetNameSurnameAndEmail()
        {
            string nome = utentePreferences.GetNome();
            string cognome = utentePreferences.GetCognome();
            string email = utentePreferences.GetEmail();

            Nome.Text = nome;
            Cognome.Text = cognome;
            Email.Text = email;

            Device.BeginInvokeOnMainThread(() => {
                StopLoading();
            });
        }

        private void Item_Tapped(object sender, ItemTappedEventArgs e)
        {
            try
            {
                var item = e.Item as MenuItems;

                switch (item.OptionName)
                {
                    case "Home":
                        {
                            Detail.Navigation.PushAsync(new Home());
                            IsPresented = false;
                        }
                        break;
                    case "Sommario":
                        {
                            //Detail = new NavigationPage(new Sommario());
                            IsPresented = false;
                        }
                        break;
                    case "Spese condominio":
                        {
                            Detail.Navigation.PushAsync(new SpeseCondominio());
                            IsPresented = false;
                        }
                        break;
                    case "Spese in comune":
                        {
                            Detail = new NavigationPage(new SpeseComune());
                            IsPresented = false;
                        }
                        break;
                    case "Affitto":
                        {
                            Detail.Navigation.PushAsync(new Affitto());
                            IsPresented = false;
                        }
                        break;
                    case "Bollette":
                        {
                            Detail.Navigation.PushAsync(new Bollette());
                            IsPresented = false;
                        }
                        break;
                    case "Codice casa":
                        {
                            Detail.Navigation.PushAsync(new MostraCodiceCasa());
                            IsPresented = false;
                        }
                        break;
                    case "Gestisci spesa comune":
                        {
                            Detail.Navigation.PushAsync(new InserimentoSpesaComune());
                            IsPresented = false;
                        }
                        break;
                    case "Inserisci spese condominio":
                        {
                            Detail.Navigation.PushAsync(new InserimentoSpesaCondominio());
                            IsPresented = false;
                        }
                        break;
                    case "Inserisci bollette":
                        {
                            Detail.Navigation.PushAsync(new InserimentoBollette());
                            IsPresented = false;
                        }
                        break;
                    case "Inserisci affitto":
                        {
                            Detail.Navigation.PushAsync(new InserimentoAffitto());
                            IsPresented = false;
                        }
                        break;
                    case "Profilo":
                        {
                            Detail.Navigation.PushAsync(new Profilo());
                            IsPresented = false;
                        }
                        break;
                    case "Logout":
                        {
                            utentePreferences.ClearPreferences();
                            IsPresented = false;
                            App.Current.MainPage = new NavigationPage(new ScegliLoginRegistrazione());
                            return;
                        }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
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


    public class MenuItems
    {
        public string OptionName { get; set; }
        public string Icon { get; set; }
    }
}