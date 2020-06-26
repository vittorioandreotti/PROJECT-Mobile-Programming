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

    private TabLayout intabLayout;
    private TabItem  intabSpeseCondominio, intabAffitto, intabBollette, intabspesacomune;
    private ViewPager inviewPager;
    public PagerAdapter inpagerAdapter;


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
