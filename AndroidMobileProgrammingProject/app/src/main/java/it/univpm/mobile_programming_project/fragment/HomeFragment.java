package it.univpm.mobile_programming_project.fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private List<Spesa> listaSpese;
    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private UtenteSharedPreferences utenteSharedPreferences;

    private PieChart mChartInterno;
    private PieChart mChartEsterno;
    private TextView txtNessunaSpesaPresente;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.utenteSharedPreferences = new UtenteSharedPreferences(getActivity());
        this.firebaseFunctionsHelper = new FirebaseFunctionsHelper(utenteSharedPreferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.mChartInterno = view.findViewById(R.id.chartInterno);
        this.mChartEsterno = view.findViewById(R.id.chartEsterno);
        this.txtNessunaSpesaPresente = view.findViewById(R.id.txtNessunaSpesaPresente);

        mChartInterno.getDescription().setEnabled(false);
        mChartInterno.setCenterTextSize(10f);
        mChartInterno.setHoleRadius(75f);
        mChartInterno.setTransparentCircleRadius(50f);
        mChartInterno.setTouchEnabled(true);
        mChartInterno.setRotationEnabled(false);
        mChartInterno.setDrawEntryLabels(false);
        mChartInterno.setContentDescription("");
        //pieChart.setDrawMarkers(true);
        //pieChart.setMaxHighlightDistance(34);
        mChartInterno.setEntryLabelTextSize(12);

        mChartEsterno.getDescription().setEnabled(false);
        mChartEsterno.setCenterTextSize(10f);
        mChartEsterno.setHoleRadius(75f);
        mChartEsterno.setTransparentCircleRadius(50f);
        mChartEsterno.setTouchEnabled(true);
        mChartEsterno.setRotationEnabled(false);
        mChartEsterno.setDrawEntryLabels(false);
        mChartEsterno.setContentDescription("");
        //pieChart.setDrawMarkers(true);
        //pieChart.setMaxHighlightDistance(34);
        mChartEsterno.setEntryLabelTextSize(12);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if( this.utenteSharedPreferences.isAffittuario() )
        {
            ((HomeActivity)getActivity()).startLoading();
            this.firebaseFunctionsHelper.elencoSpeseAffittuario(getContext())
                    .addOnCompleteListener(new OnCompleteListener<Map<String, List<Spesa>>>() {
                        @Override
                        public void onComplete(@NonNull Task<Map<String, List<Spesa>>> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(getContext(), "Errore nella lettura delle spese dell'affittuario.", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Map<String, List<Spesa>> speseTotali = task.getResult();
                            setupUI(speseTotali);
                            ((HomeActivity)getActivity()).stopLoading();
                        }
                    });
        }else
            {
            ((HomeActivity)getActivity()).startLoading();
            this.firebaseFunctionsHelper.elencoSpeseProprietario(getContext())
                    .addOnCompleteListener(new OnCompleteListener<Map<String, List<Spesa>>>() {
                        @Override
                        public void onComplete(@NonNull Task<Map<String, List<Spesa>>> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(getContext(), "Errore nella lettura delle spese dell'affittuario.", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Map<String, List<Spesa>> speseTotali = task.getResult();
                            setupUI(speseTotali);
                            ((HomeActivity)getActivity()).stopLoading();
                        }
                    });
        }


    }

    private void setupUI(Map<String, List<Spesa>> speseTotali) {

        ArrayList<Integer> colorsInterno = new ArrayList<Integer>();
        ArrayList<Integer> colorsEsterno = new ArrayList<Integer>();

        List<Spesa> speseSommario = speseTotali.get("sommario");
        List<Spesa> speseSpesaComune = speseTotali.get("comune");
        List<Spesa> speseAffitto = speseTotali.get("affitto");
        List<Spesa> speseBollette = speseTotali.get("bollette");
        List<Spesa> speseSpesaCondominio = speseTotali.get("condominio");

        if(speseSommario.size() == 0) {
            this.mChartInterno.setVisibility(View.GONE);
            this.mChartEsterno.setVisibility(View.GONE);
            this.txtNessunaSpesaPresente.setVisibility(View.VISIBLE);
            return;
        }else{
            this.mChartInterno.setVisibility(View.VISIBLE);
            this.mChartEsterno.setVisibility(View.VISIBLE);
            this.txtNessunaSpesaPresente.setVisibility(View.GONE);

        }



        double sommaPagate = 0f, sommaDaPagare = 0f;
        double affittoPagate = 0f, affittoDaPagare = 0f;
        double condominioPagate = 0f, condominioDaPagare = 0f;
        double bollettePagate = 0f, bolletteDaPagare = 0f;
        double comunePagate = 0f, comuneDaPagare = 0f;

        // Calcolo il totale sulle spese pagate o non pagate
        for(Spesa spesa : speseSommario)  {
            if(spesa.isSpesaPagata())
                sommaPagate += spesa.getPrezzo();
            else
                sommaDaPagare += spesa.getPrezzo();
        }

        // Calcolo su affitto
        for(Spesa spesa : speseAffitto) {
            if(spesa.isSpesaPagata())
                affittoPagate += spesa.getPrezzo();
            else
                affittoDaPagare += spesa.getPrezzo();
        }

        // Calcolo su spesa comune
        for(Spesa spesa : speseSpesaComune) {
            if(spesa.isSpesaPagata())
                comunePagate += spesa.getPrezzo();
            else
                comuneDaPagare += spesa.getPrezzo();
        }

        // Calcolo su bollette
        for(Spesa spesa : speseBollette) {
            if(spesa.isSpesaPagata())
                bollettePagate += spesa.getPrezzo();
            else
                bolletteDaPagare += spesa.getPrezzo();
        }

        // Calcolo su condominio
        for(Spesa spesa : speseSpesaCondominio) {
            if(spesa.isSpesaPagata())
                condominioPagate += spesa.getPrezzo();
            else
                condominioDaPagare += spesa.getPrezzo();
        }


        List<PieEntry> entriesEsterno = new ArrayList<>();
        if(sommaDaPagare != 0) {
            colorsEsterno.add( ColorTemplate.rgb("#dc3545") );
            entriesEsterno.add(new PieEntry((float)sommaDaPagare, "DA PAGARE"));
        }
        if(sommaPagate != 0){
            colorsEsterno.add( ColorTemplate.rgb("#28a745") );
            entriesEsterno.add(new PieEntry((float)sommaPagate, "PAGATE"));
        }

        List<PieEntry> entriesInterno = new ArrayList<>();
        if( affittoDaPagare != 0 ) {
            entriesInterno.add(new PieEntry((float)affittoDaPagare, "Affitto da pagare"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[0]);
        }
        if( condominioDaPagare != 0 ) {
            entriesInterno.add(new PieEntry((float)condominioDaPagare, "Spese condominio da pagare"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[1]);
        }
        if( bolletteDaPagare != 0 ) {
            entriesInterno.add(new PieEntry((float)bolletteDaPagare, "Bollette da pagare"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[2]);
        }
        if( comuneDaPagare != 0 ) {
            entriesInterno.add(new PieEntry((float)comuneDaPagare, "Spese Comuni da pagare"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[3]);
        }

        if( affittoPagate != 0 ) {
            entriesInterno.add(new PieEntry((float)affittoPagate, "Affitto pagato"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[0]);
        }
        if( condominioPagate != 0 ) {
            entriesInterno.add(new PieEntry((float)condominioPagate, "Spese condominio pagate"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[1]);
        }
        if( bollettePagate != 0 ) {
            entriesInterno.add(new PieEntry((float)bollettePagate, "Bollette pagate"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[2]);
        }
        if( comunePagate != 0 ) {
            entriesInterno.add(new PieEntry((float)comunePagate, "Spese Comuni pagate"));
            colorsInterno.add(ColorTemplate.VORDIPLOM_COLORS[3]);
        }

        PieDataSet dataSetInterno = new PieDataSet(entriesInterno, "");
        dataSetInterno.setSliceSpace(2f);
        dataSetInterno.setColors(colorsInterno);



        PieDataSet dataSetEsterno = new PieDataSet(entriesEsterno, "");
        dataSetEsterno.setSliceSpace(5f);
        dataSetEsterno.setColors(colorsEsterno);


        PieData dataInterno = new PieData(dataSetInterno);
        dataInterno.setValueFormatter(new PercentFormatter());
        dataInterno.setValueTextSize(10f);
        dataInterno.setValueTextColor(Color.BLACK);

        mChartInterno.setData(dataInterno);
        mChartInterno.invalidate(); // refresh

        PieData dataEsterno = new PieData(dataSetEsterno);
        dataEsterno.setValueFormatter(new PercentFormatter());
        dataEsterno.setValueTextSize(13f);
        dataEsterno.setValueTextColor(Color.BLACK);

        mChartEsterno.setData(dataEsterno);
        mChartEsterno.invalidate(); // refresh

        //legend attributes
        Legend legendInterna = mChartInterno.getLegend();


        LegendEntry[] legendEntryArray = new LegendEntry[]{
            new LegendEntry("Affitto",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[0]),
            new LegendEntry("Spese condominio",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[1]),
            new LegendEntry("Bollette",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[2]),
            new LegendEntry("Spese comuni",Legend.LegendForm.CIRCLE,12f,2f,null,ColorTemplate.VORDIPLOM_COLORS[3])
        };

        legendInterna.setCustom( legendEntryArray );
        legendInterna.setOrientation(Legend.LegendOrientation.VERTICAL);
        legendInterna.setYOffset(75f);
        legendInterna.setXOffset(75f);


        //legend attributes
        Legend legendEsterna = mChartEsterno.getLegend();

        legendEsterna.setForm(Legend.LegendForm.CIRCLE);
        legendEsterna.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legendEsterna.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legendEsterna.setOrientation(Legend.LegendOrientation.VERTICAL);
        legendEsterna.setYOffset(50f);
        legendEsterna.setDrawInside(false);
        legendEsterna.setEnabled(true);

    }
}
