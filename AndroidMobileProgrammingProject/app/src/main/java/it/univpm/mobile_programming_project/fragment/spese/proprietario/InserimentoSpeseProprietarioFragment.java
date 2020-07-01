package it.univpm.mobile_programming_project.fragment.spese.proprietario;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class InserimentoSpeseProprietarioFragment extends Fragment {

    public static final int SPESACONDOMINIO = 0;
    public static final int BOLLETTE = 1;
    public static final int AFFITTO = 2;

    private TabLayout intabLayout;
    private ViewPager inviewPager;
    public InserimentoSpesePageAdapterProprietario inpagerAdapter;

    private int paginaDiLancio;

    public InserimentoSpeseProprietarioFragment() {
        this.paginaDiLancio=0;
    }

    public InserimentoSpeseProprietarioFragment(int paginaDiLancio) {
        this.paginaDiLancio = paginaDiLancio;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inserimento_spese, container, false);

        intabLayout = view.findViewById(R.id.intablayout);
        inviewPager = view.findViewById(R.id.inviewpager);

        inpagerAdapter = new InserimentoSpesePageAdapterProprietario(getActivity().getSupportFragmentManager());
        inviewPager.setAdapter(inpagerAdapter);

        inpagerAdapter.addFragment(new InserisciSpesaCondominioFragment());
        inpagerAdapter.addFragment(new InserisciBolletteFragment());
        inpagerAdapter.addFragment(new InserisciAffittoFragment());


        // Naviga direttamente alla pagina specificata nel costruttore,
        // oppure alla prima pagina se non specificata
        inviewPager.setCurrentItem(this.paginaDiLancio);

        intabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                inviewPager.setCurrentItem(tab.getPosition());


                inpagerAdapter.notifyDataSetChanged();

                int titoloId = R.string.spese;
                Activity activity = getActivity();

                switch(tab.getPosition()){
                    case InserimentoSpeseProprietarioFragment.SPESACONDOMINIO:
                        titoloId = R.string.spese_condominio;
                        break;

                    case InserimentoSpeseProprietarioFragment.BOLLETTE:
                        titoloId = R.string.bollette;
                        break;

                    case InserimentoSpeseProprietarioFragment.AFFITTO:
                        titoloId = R.string.affitto;
                        break;
                }

                ((HomeActivity)activity).setToolbarTitle( activity.getString(titoloId) );


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        inviewPager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(intabLayout));
        return view;
    }
}
