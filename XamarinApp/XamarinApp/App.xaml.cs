using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using XamarinApp.Pages;

namespace XamarinApp
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();

            // Visualizza splash screen

            MainPage = new SplashScreen();
            //MainPage = new NavigationPage( new MainPage() );
            // MainPage = new NavigationDrawer();
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
