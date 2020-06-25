package it.univpm.mobile_programming_project.view_pager_inserimento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import it.univpm.mobile_programming_project.R;

public class InserimentoSpeseActivity extends AppCompatActivity {

    private TabLayout intabLayout;
    private TabItem  intabSpeseCondominio, intabAffitto, intabBollette, intabspesacomune;
    private ViewPager inviewPager;
    public PagerAdapter inpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_spese);

        //Collega ogni elemento alla propria View

        intabLayout = (TabLayout) findViewById(R.id.intablayout);
        intabSpeseCondominio = (TabItem) findViewById(R.id.tabspesecondomio);
        intabAffitto = (TabItem) findViewById(R.id.intabaffitto);
        intabBollette = (TabItem) findViewById(R.id.intabbollette);
        intabspesacomune = (TabItem) findViewById(R.id.intabspesacomune);


        //
        inviewPager = (ViewPager) findViewById(R.id.viewpager);
        inpagerAdapter = new InserimentoSpesePageAdapter(getSupportFragmentManager(), intabLayout.getTabCount());
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
                } else if (tab.getPosition() == 3) {
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
