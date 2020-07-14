using System;
using System.Collections.Generic;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Pages;

namespace XamarinApp
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class NavigationDrawer : MasterDetailPage
    {
        List<MenuItems> menu;

        public NavigationDrawer()
        {
            InitializeComponent();
            menu = new List<MenuItems>();

            menu.Add(new MenuItems { OptionName = "Home" });
            menu.Add(new MenuItems { OptionName = "Sommario" });
            menu.Add(new MenuItems { OptionName = "Spese condominio" });
            menu.Add(new MenuItems { OptionName = "Affitto" });
            menu.Add(new MenuItems { OptionName = "Bollette" });
            menu.Add(new MenuItems { OptionName = "Codice casa" });
            menu.Add(new MenuItems { OptionName = "Inserisci spese condominio" });
            menu.Add(new MenuItems { OptionName = "Inserisci bollette" });
            menu.Add(new MenuItems { OptionName = "Inserisci affitto" });
            menu.Add(new MenuItems { OptionName = "Profilo" });
            menu.Add(new MenuItems { OptionName = "Logout" });
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
                            //Detail.Navigation.PushAsync(new LogOut());
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
    }
}