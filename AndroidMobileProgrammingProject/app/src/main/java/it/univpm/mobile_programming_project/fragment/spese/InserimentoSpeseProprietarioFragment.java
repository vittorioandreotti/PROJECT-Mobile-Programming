package it.univpm.mobile_programming_project.fragment.spese;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.tornei.TorneiPageAdapter;
import it.univpm.mobile_programming_project.view_pager.SpesePageAdapter;
import it.univpm.mobile_programming_project.view_pager.SpesePageAdapterAffittuario;
import it.univpm.mobile_programming_project.view_pager_inserimento.InserimentoSpesePageAdapter;
import it.univpm.mobile_programming_project.view_pager_inserimento.InserimentoSpesePageAdapterAffittuario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class InserimentoSpeseProprietarioFragment extends Fragment {

    public static final int SPESACONDOMINIO = 0;
    public static final int BOLLETTE = 1;
    public static final int AFFITTO = 2;

    private TabLayout intabLayout;
    private TabItem  intabSpeseCondominio, intabAffitto, intabBollette, intabspesacomune;
    private ViewPager inviewPager;
    public PagerAdapter inpagerAdapter;

    private int paginaDiLancio = 0;

    public InserimentoSpeseProprietarioFragment() {
    }

    public InserimentoSpeseProprietarioFragment(int paginaDiLancio) {
        switch (paginaDiLancio) {
            case InserimentoSpeseProprietarioFragment.SPESACONDOMINIO:
            case InserimentoSpeseProprietarioFragment.BOLLETTE:
            case InserimentoSpeseProprietarioFragment.AFFITTO:

                this.paginaDiLancio = paginaDiLancio;
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_inserimento_spese, container, false);

        intabLayout = view.findViewById(R.id.intablayout);
        intabSpeseCondominio = view.findViewById(R.id.tabspesecondomio);
        intabAffitto = view.findViewById(R.id.intabaffitto);
        intabBollette = view.findViewById(R.id.intabbollette);


        inviewPager = view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        inpagerAdapter = new InserimentoSpesePageAdapter(getActivity().getSupportFragmentManager(), intabLayout.getTabCount());
        inviewPager.setAdapter(inpagerAdapter);

        // Naviga direttamente alla pagina specificata nel costruttore,
        // oppure alla prima pagina se non specificata
        inviewPager.setCurrentItem(this.paginaDiLancio);

        intabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                inviewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    inpagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    inpagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    inpagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        inviewPager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(intabLayout));

    }
}
