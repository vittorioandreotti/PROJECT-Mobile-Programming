package it.univpm.mobile_programming_project.fragment.spese.affittuario;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.AffittoFragment;
import it.univpm.mobile_programming_project.fragment.spese.BolletteFragment;
import it.univpm.mobile_programming_project.fragment.spese.SommarioFragment;
import it.univpm.mobile_programming_project.fragment.spese.SpeseCondominioFragment;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Map;

public class SpeseAffittuarioFragment extends Fragment {

    public static final int SOMMARIO = 0;
    public static final int SPESACOMUNE = 1;
    public static final int AFFITTO = 2;
    public static final int BOLLETTE = 3;
    public static final int SPESACONDOMINIO = 4;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public SpesePageAdapterAffittuario pagerAdapter;

    private int paginaDiLancio;
    private List<Spesa> speseSommario;
    private List<Spesa> speseSpesaComune;
    private List<Spesa> speseAffitto;
    private List<Spesa> speseBollette;
    private List<Spesa> speseSpesaCondominio;

    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private UtenteSharedPreferences sharedPreferences;



    public SpeseAffittuarioFragment() {
        this.paginaDiLancio=0;
    }

    public SpeseAffittuarioFragment(int paginaDiLancio) {
        this.paginaDiLancio = paginaDiLancio;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sharedPreferences = new UtenteSharedPreferences(getContext());
        firebaseFunctionsHelper = new FirebaseFunctionsHelper(this.sharedPreferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spese_affittuario, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        pagerAdapter = new SpesePageAdapterAffittuario(getActivity().getSupportFragmentManager());

        ((HomeActivity)getActivity()).startLoading();
        this.firebaseFunctionsHelper.elencoSpeseAffittuario(getContext())
                .addOnCompleteListener(new OnCompleteListener<Map<String, List<Spesa>>>() {
                    @Override
                    public void onComplete(@NonNull Task<Map<String, List<Spesa>>> task) {
                        if(!task.isSuccessful()) {
                            Toast.makeText(getContext(), "Errore nella lettura delle spese dell'affittuario...", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Map<String, List<Spesa>> speseTotali = task.getResult();
                        speseSommario = speseTotali.get("sommario");
                        speseSpesaComune = speseTotali.get("comune");
                        speseAffitto = speseTotali.get("affitto");
                        speseBollette = speseTotali.get("bollette");
                        speseSpesaCondominio = speseTotali.get("condominio");
                        setupUI();
                        ((HomeActivity)getActivity()).stopLoading();
                    }
                });

        return view;
    }

    private void setupUI() {
        pagerAdapter.clearFragmentList();
        pagerAdapter.addFragment(new SommarioFragment(this.speseSommario));
        pagerAdapter.addFragment(new SpesaComuneFragment(this.speseSpesaComune));
        pagerAdapter.addFragment(new AffittoFragment(this.speseAffitto));
        pagerAdapter.addFragment(new BolletteFragment(this.speseBollette));
        pagerAdapter.addFragment(new SpeseCondominioFragment(this.speseSpesaCondominio));

        viewPager.setAdapter(pagerAdapter);

        // Naviga direttamente alla pagina specificata nel costruttore,
        // oppure alla prima pagina se non specificata
        viewPager.setCurrentItem(this.paginaDiLancio);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pagerAdapter.notifyDataSetChanged();

                int titoloId = R.string.spese;
                Activity activity = getActivity();

                switch (tab.getPosition()) {
                    case SpeseAffittuarioFragment.SOMMARIO:
                        titoloId = R.string.sommario;
                        break;

                    case SpeseAffittuarioFragment.SPESACOMUNE:
                        titoloId = R.string.spesacomune;
                        break;

                    case SpeseAffittuarioFragment.AFFITTO:
                        titoloId = R.string.affitto;
                        break;

                    case SpeseAffittuarioFragment.BOLLETTE:
                        titoloId = R.string.bollette;
                        break;

                    case SpeseAffittuarioFragment.SPESACONDOMINIO:
                        titoloId = R.string.spese_condominio;
                }

                ((HomeActivity) activity).setToolbarTitle(activity.getString(titoloId));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    /**
     * Interfaccia che permette di ascoltare quando la GUI è pronta
     */
    public interface OnTaskResultListener{
        void onPreExecute();
        void onPostExecute(Object obj);
        Object doInBackground(Object obj);
    }

    public class GuiSetupAsyncTask extends AsyncTask<Object,Object,Object> { //change Object to required type

        private OnTaskResultListener listener;

        GuiSetupAsyncTask(OnTaskResultListener listener){
            this.listener=listener;
        }


        @Override
        protected Object doInBackground(Object... objects) {
            return listener.doInBackground(objects);
        }

        @Override
        protected void onPreExecute() {
            listener.onPreExecute();
        }


        @Override
        protected void onPostExecute(Object obj){
            listener.onPostExecute(obj);
        }
    }
}