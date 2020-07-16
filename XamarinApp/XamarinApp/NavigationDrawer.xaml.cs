using System;
using System.Collections.Generic;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Pages;
using XamarinApp.Utils;

namespace XamarinApp
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class NavigationDrawer : MasterDetailPage
    {
        List<MenuItems> menu;
        UtentePreferences utentePreferences;

        public NavigationDrawer()
        {
            InitializeComponent();

            InitUserData();
        }

        private void InitUserData()
        {
            // Chiamata a cloud functions
                // Lettura dati getUtenteAndCasa in utentePreferences
                    // Dopo delle shared preferences, chiamate InitUI()
        }

        private void InitUI()
        {
            menu = new List<MenuItems>();

            utentePreferences = new UtentePreferences();
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
            navigationList.ItemsSource = menu;
            Detail = new NavigationPage(new Home());
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
                            Detail = new NavigationPage(new Home());
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
                            //Detail.Navigation.PushAsync(new SpeseCondominio());
                            IsPresented = false;
                        }
                        break;
                    case "Spese in comune":
                        {
                            //Detail = new NavigationPage(new SpesaComune());
                            IsPresented = false;
                        }
                        break;
                    case "Affitto":
                        {
                            //Detail.Navigation.PushAsync(new Affitto());
                            IsPresented = false;
                        }
                        break;
                    case "Bollette":
                        {
                            //Detail.Navigation.PushAsync(new Bollette());
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
                            Detail = new NavigationPage(new InserimentoSpesaComune());
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
                            this.Navigation.PopAsync();
                            IsPresented = false;
                        }
                        break;
                }
            }
            catch (Exception ex)
            {

            }
        }
    }


    public class MenuItems
    {
        public string OptionName { get; set; }
        public string Icon { get; set; }
    }
}