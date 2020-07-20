using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using Microcharts;
using Microcharts.Forms;
using Entry = Microcharts.Entry;
using XamarinApp.Utils;
using SkiaSharp;
using SkiaSharp.Views.Forms;
using XamarinApp.LoadingService;
using Rg.Plugins.Popup.Services;


namespace XamarinApp.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Home : ContentPage
    {
        FirebaseFunctionHelper firebaseFunctionHelper = new FirebaseFunctionHelper();
        UtentePreferences utentePreferences = new UtentePreferences();
        List<Entry> chartEntriesInterno = new List<Entry>();
        List<Entry> chartEntriesEsterno = new List<Entry>();

        public Home()
        {
            InitializeComponent();
            Task<List <Spesa>> spese;

            if (utentePreferences.IsAffittuario())
            {
                spese = firebaseFunctionHelper.ElencoSommarioAffittuario();

            }
            else
            {
                spese = firebaseFunctionHelper.ElencoSommarioProprietario();

            }
            spese.ContinueWith((Task<List<Spesa>> t) =>
            {
                t.Wait();
                List<Spesa> listaspese = t.Result;
                InitUI(listaspese);
            });
            
        }

        private void InitUI(List<Spesa> listaspese)
        {
            ChartInterno.IsVisible = false;
            ChartEsterno.IsVisible = false;
            ChartEsternoLabels.IsVisible = false;
            ChartInternoLabels.IsVisible = false;


            Device.BeginInvokeOnMainThread(() => {
                StopLoading();
            });


            double sommaPagate = 0f, sommaDaPagare = 0f;
            double affittoPagate = 0f, affittoDaPagare = 0f;
            double condominioPagate = 0f, condominioDaPagare = 0f;
            double bollettePagate = 0f, bolletteDaPagare = 0f;
            double comunePagate = 0f, comuneDaPagare = 0f;

            foreach(Spesa spesa in listaspese)
            {
                if (spesa.IsSpesaPagata())
                    sommaPagate += spesa.Prezzo;
                else
                    sommaDaPagare += spesa.Prezzo;

                switch(spesa.Tipo)
                {
                    case "bolletta":
                        if (spesa.IsSpesaPagata())
                            bollettePagate += spesa.Prezzo;
                        else
                            bolletteDaPagare += spesa.Prezzo;
                        break;

                    case "affitto":
                        if (spesa.IsSpesaPagata())
                            affittoPagate += spesa.Prezzo;
                        else
                            affittoDaPagare += spesa.Prezzo;
                        break;

                    case "comune":
                        if (spesa.IsSpesaPagata())
                            comunePagate += spesa.Prezzo;
                        else
                            comuneDaPagare += spesa.Prezzo;
                        break;

                    case "condominio":
                        if (spesa.IsSpesaPagata())
                            condominioPagate += spesa.Prezzo;
                        else
                            condominioDaPagare += spesa.Prezzo;
                        break;
                }
            }


            List<Color> chartColors = new List<Color>
            {
                Color.FromRgb(244, 67, 54),
                Color.FromRgb(121, 85, 72),
                Color.FromRgb(251, 140, 0),
                Color.FromRgb(255, 235, 59),
                Color.FromRgb(76, 175, 80),
                Color.FromRgb(0, 188, 212),
                Color.FromRgb(63, 81, 181),
                Color.FromRgb(156, 39, 176)               
            };
            
            if (sommaDaPagare != 0)
            {
                Entry newEntry = new Entry((float)sommaDaPagare)
                {
                    Color = SKColor.Parse("#dc3545"),
                    Label = "DA PAGARE - " + sommaDaPagare.ToString() + "€",
                    ValueLabel = "",
                };
                chartEntriesEsterno.Add(newEntry);
            }
            if (sommaPagate != 0)
            {
                Entry newEntry = new Entry((float)sommaPagate)
                {
                    Color = SKColor.Parse("#28a745"),
                    Label = "PAGATE - " + sommaPagate.ToString() + "€",
                    ValueLabel = "",
                };
                chartEntriesEsterno.Add(newEntry);
            }

            if (affittoDaPagare != 0)
            {
                Entry newEntry = new Entry((float)affittoDaPagare)
                {
                    Color = chartColors[0].ToSKColor(),
                    Label = "Affitto da pagare - " + affittoDaPagare.ToString() + "€",
                    ValueLabel = "",
                };
                chartEntriesInterno.Add(newEntry);
            }
            if (condominioDaPagare != 0)
            {
                Entry newEntry = new Entry((float)condominioDaPagare)
                {
                    Color = chartColors[1].ToSKColor(),
                    Label = "Spese condominio da pagare - " + condominioDaPagare.ToString() + "€",
                    ValueLabel = "",
                };
                chartEntriesInterno.Add(newEntry);
            }
            if (bolletteDaPagare != 0)
            {
                Entry newEntry = new Entry((float)bolletteDaPagare)
                {
                    Color = chartColors[2].ToSKColor(),
                    Label = "Bollette da pagare - " + bolletteDaPagare.ToString() + "€",
                    ValueLabel = "",
                };
                chartEntriesInterno.Add(newEntry);
            }
            if (comuneDaPagare != 0)
            {
                Entry newEntry = new Entry((float)comuneDaPagare)
                {
                    Color = chartColors[3].ToSKColor(),
                    Label = "Spese comuni da pagare - " + comuneDaPagare.ToString() + "€",
                    ValueLabel = "",                    
                };
                chartEntriesInterno.Add(newEntry);
            }

            if (affittoPagate != 0)
            {
                Entry newEntry = new Entry((float)affittoPagate)
                {
                    Color = chartColors[4].ToSKColor(),
                    Label = "Affitto pagato - " + affittoPagate.ToString() + "€",
                    ValueLabel = "",                   
                };
                chartEntriesInterno.Add(newEntry);
            }
            if (condominioPagate != 0)
            {
                Entry newEntry = new Entry((float)condominioPagate)
                {
                    Color = chartColors[5].ToSKColor(),
                    Label = "Spese condominio pagate - " + condominioPagate.ToString() + "€",
                    ValueLabel = "",                  
                };
                chartEntriesInterno.Add(newEntry);
            }
            if (bollettePagate != 0)
            {
                Entry newEntry = new Entry((float)bollettePagate)
                {
                    Color = chartColors[6].ToSKColor(),
                    Label = "Bollette pagate - " + bollettePagate.ToString() + "€",
                    ValueLabel = "",                  
                };
                chartEntriesInterno.Add(newEntry);
            }
            if (comunePagate != 0)
            {
                Entry newEntry = new Entry((float)comunePagate)
                {
                    Color = chartColors[7].ToSKColor(),
                    Label = "Spese comuni pagate - " + comunePagate.ToString() + "€",
                    ValueLabel = "",                    
                };
                chartEntriesInterno.Add(newEntry);
            }

            /*
            //legend attributes
            Legend legendInterna = mChartInterno.getLegend();


            LegendEntry[] legendEntryArray = new LegendEntry[]{
                new LegendEntry("Affitto",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[0]),
                new LegendEntry("Spese condominio",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[1]),
                new LegendEntry("Bollette",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[2]),
                new LegendEntry("Spese comuni",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[3])
            };
            */

            ChartEsterno.Chart = new DonutChart()
            {
                Entries = chartEntriesEsterno,
                HoleRadius = 0.4f,
                LabelTextSize = 0,
                BackgroundColor = Color.Transparent.ToSKColor()
            };


            ChartEsternoLabels.Chart = new DonutChart()
            {
                Entries = chartEntriesEsterno,
                HoleRadius = 1,
                LabelTextSize = 40,
                BackgroundColor = Color.Transparent.ToSKColor()
            };

            ChartInterno.Chart = new DonutChart() {
                Entries = chartEntriesInterno,
                HoleRadius = 0.4f,
                LabelTextSize = 0,
                BackgroundColor = Color.Transparent.ToSKColor(),
                
            };

            ChartInternoLabels.Chart = new DonutChart()
            {
                Entries = chartEntriesInterno,
                HoleRadius = 1,
                LabelTextSize = 30,
                BackgroundColor = Color.Transparent.ToSKColor()
            };


            ChartEsterno.IsVisible = true;
            ChartInterno.IsVisible = true;
            ChartEsternoLabels.IsVisible = true;
            ChartInternoLabels.IsVisible = true;

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