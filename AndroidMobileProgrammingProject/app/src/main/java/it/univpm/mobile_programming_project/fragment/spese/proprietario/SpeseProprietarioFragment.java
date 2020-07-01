package it.univpm.mobile_programming_project.fragment.spese.proprietario;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.AffittoFragment;
import it.univpm.mobile_programming_project.fragment.spese.BolletteFragment;
import it.univpm.mobile_programming_project.fragment.spese.SommarioFragment;
import it.univpm.mobile_programming_project.fragment.spese.SpeseCondominioFragment;
import it.univpm.mobile_programming_project.fragment.spese.affittuario.SpesaComuneFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class SpeseProprietarioFragment extends Fragment {

    public static final int SOMMARIO = 0;
    public static final int SPESACONDOMINIO = 1;
    public static final int AFFITTO = 2;
    public static final int BOLLETTE = 3;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public SpesePageAdapterProprietario pagerAdapter;

    private int paginaDiLancio;

    public SpeseProprietarioFragment() {
        this.paginaDiLancio=0;
    }

    public SpeseProprietarioFragment(int paginaDiLancio) {
        this.paginaDiLancio = paginaDiLancio;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spese_proprietario, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        pagerAdapter = new SpesePageAdapterProprietario(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        pagerAdapter.addFragment(new SommarioFragment());
        pagerAdapter.addFragment(new SpeseCondominioFragment());
        pagerAdapter.addFragment(new AffittoFragment());
        pagerAdapter.addFragment(new BolletteFragment());

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

                switch(tab.getPosition()){
                    case SpeseProprietarioFragment.SOMMARIO:
                        titoloId = R.string.sommario;
                        break;

                    case SpeseProprietarioFragment.SPESACONDOMINIO:
                        titoloId = R.string.spese_condominio;
                        break;

                    case SpeseProprietarioFragment.AFFITTO:
                        titoloId = R.string.affitto;
                        break;

                    case SpeseProprietarioFragment.BOLLETTE:
                        titoloId = R.string.bollette;
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

        viewPager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }
}