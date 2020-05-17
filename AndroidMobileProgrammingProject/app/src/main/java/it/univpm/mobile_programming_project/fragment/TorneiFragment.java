package it.univpm.mobile_programming_project.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.tornei.TorneiPageAdapter;
import it.univpm.mobile_programming_project.view_pager.SpesePageAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TorneiFragment extends Fragment {

    private TabLayout tabLayout;
    private TabItem tabPartecipa, tabCrea, tabStorico;
    private ViewPager viewPager;
    public PagerAdapter pagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tornei, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        tabPartecipa = view.findViewById(R.id.tabpartecipa);
        tabStorico = view.findViewById(R.id.tabstorico);
        tabCrea = view.findViewById(R.id.tabcrea);
        viewPager = view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        pagerAdapter = new TorneiPageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }
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
