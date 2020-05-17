package it.univpm.mobile_programming_project.tornei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.view_pager.SpesePageAdapter;

public class TorneiActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabItem tabPartecipa, tabCrea, tabStorico;
    private ViewPager viewPager;
    public PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tornei);

        tabLayout = findViewById(R.id.tablayout);
        tabPartecipa = findViewById(R.id.tabpartecipa);
        tabStorico = findViewById(R.id.tabstorico);
        tabCrea = findViewById(R.id.tabcrea);

        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new SpesePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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
