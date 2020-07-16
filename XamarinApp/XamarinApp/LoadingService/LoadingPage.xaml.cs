using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace XamarinApp.LoadingService
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoadingPage : Rg.Plugins.Popup.Pages.PopupPage
    {
        public LoadingPage()
        {
            InitializeComponent();
        }

        protected override Task OnAppearingAnimationBeginAsync()
        {
            return base.OnAppearingAnimationBeginAsync();
        }

        protected override Task OnDisappearingAnimationEndAsync()
        {
            return base.OnDisappearingAnimationEndAsync();
        }

    }
}