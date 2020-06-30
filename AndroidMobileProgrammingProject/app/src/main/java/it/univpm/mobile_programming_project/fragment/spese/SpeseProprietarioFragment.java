package it.univpm.mobile_programming_project.fragment.spese;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class SpeseProprietarioFragment extends Fragment {

    public static final int SOMMARIO = 0;
    public static final int SPESACONDOMINIO = 1;
    public static final int AFFITTO = 2;
    public static final int BOLLETTE = 3;

    private TabLayout tabLayout;
    private TabItem tabSommario, tabSpeseCondominio, tabAffitto, tabBollette;
    private ViewPager viewPager;
    public PagerAdapter pagerAdapter;

    private int paginaDiLancio;

    public SpeseProprietarioFragment() {
        this.paginaDiLancio=0;
    }

    public SpeseProprietarioFragment(int paginaDiLancio) {
        switch (paginaDiLancio) {
            case SpeseProprietarioFragment.SOMMARIO:
            case SpeseProprietarioFragment.SPESACONDOMINIO:
            case SpeseProprietarioFragment.AFFITTO:
            case SpeseProprietarioFragment.BOLLETTE:

                this.paginaDiLancio = paginaDiLancio;
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_spese_proprietario, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        tabSommario = view.findViewById(R.id.tabsommario);
        tabSpeseCondominio = view.findViewById(R.id.tabspesecondomio);
        tabAffitto = view.findViewById(R.id.tabaffitto);
        tabBollette = view.findViewById(R.id.tabbollette);
        viewPager = view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        pagerAdapter = new SpesePageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        // Naviga direttamente alla pagina specificata nel costruttore,
        // oppure alla prima pagina se non specificata
        viewPager.setCurrentItem(this.paginaDiLancio);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
//                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
//                } else if (tab.getPosition() == 1) {
//                    pagerAdapter.notifyDataSetChanged();
//                } else if (tab.getPosition() == 2) {
//                    pagerAdapter.notifyDataSetChanged();
//                } else if (tab.getPosition() == 3) {
//                    pagerAdapter.notifyDataSetChanged();
//                }
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