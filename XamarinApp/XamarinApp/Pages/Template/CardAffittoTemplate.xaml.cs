using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace XamarinApp.Pages.Template
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class CardAffittoTemplate : ContentView
    {
        Spesa spesa = new Spesa();
        public CardAffittoTemplate()
        {
            InitializeComponent();
        }

        public void Paga()
        {
            String id = spesa.IdSpesa;
        }
    }
}