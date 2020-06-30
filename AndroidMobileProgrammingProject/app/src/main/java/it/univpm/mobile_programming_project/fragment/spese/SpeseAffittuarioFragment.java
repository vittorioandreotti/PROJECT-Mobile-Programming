package it.univpm.mobile_programming_project.fragment.spese;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.tornei.TorneiPageAdapter;
import it.univpm.mobile_programming_project.view_pager.SpesePageAdapter;
import it.univpm.mobile_programming_project.view_pager.SpesePageAdapterAffittuario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SpeseAffittuarioFragment extends Fragment {

    public static final int SOMMARIO = 0;
    public static final int SPESACOMUNE = 1;
    public static final int AFFITTO = 2;
    public static final int BOLLETTE = 3;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PagerAdapter pagerAdapter;

    private int paginaDiLancio;

    public SpeseAffittuarioFragment() {
        this.paginaDiLancio=0;
    }

    public SpeseAffittuarioFragment(int paginaDiLancio) {
        switch (paginaDiLancio) {
            case SpeseAffittuarioFragment.SOMMARIO:
            case SpeseAffittuarioFragment.SPESACOMUNE:
            case SpeseAffittuarioFragment.AFFITTO:
            case SpeseAffittuarioFragment.BOLLETTE:

                this.paginaDiLancio = paginaDiLancio;
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_spese_affittuario, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        pagerAdapter = new SpesePageAdapterAffittuario(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
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

                switch(tab.getPosition()){
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

    }
}