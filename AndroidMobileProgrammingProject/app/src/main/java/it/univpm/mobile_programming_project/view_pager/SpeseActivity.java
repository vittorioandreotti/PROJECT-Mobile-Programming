package it.univpm.mobile_programming_project.view_pager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import it.univpm.mobile_programming_project.R;

public class SpeseActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabItem tabSommario, tabSpeseCondominio, tabAffitto, tabBollette;
    private ViewPager viewPager;
    public PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spese);

        //Collega ogni elemento alla propria View

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabSommario = (TabItem) findViewById(R.id.tabsommario);
        tabSpeseCondominio = (TabItem) findViewById(R.id.tabspesecondomio);
        tabAffitto = (TabItem) findViewById(R.id.tabaffitto);
        tabBollette = (TabItem) findViewById(R.id.tabbollette);

        //
        viewPager = (ViewPager) findViewById(R.id.viewpager);
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
                } else if (tab.getPosition() == 3) {
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
