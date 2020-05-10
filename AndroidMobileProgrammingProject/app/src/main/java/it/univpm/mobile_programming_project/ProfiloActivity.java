package it.univpm.mobile_programming_project;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.univpm.mobile_programming_project.fragment.HomeFragment;
import it.univpm.mobile_programming_project.fragment.SpeseFragment;
import it.univpm.mobile_programming_project.fragment.TorneiFragment;

public class ProfiloActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private HomeFragment homeFragment;
    private SpeseFragment speseFragment;
    private TorneiFragment torneiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        homeFragment =new HomeFragment();
        speseFragment = new SpeseFragment();
        torneiFragment = new TorneiFragment();

        bottomNav=findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=null;
                switch(item.getItemId()){
                    case R.id.menu_home:
                        selectedFragment= homeFragment;
                        break;
                    case R.id.menu_spese:
                        selectedFragment= speseFragment;
                        break;
                    case R.id.menu_tornei:
                        selectedFragment= torneiFragment;
                        break;
                }
                if(selectedFragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.scrollviewProfilo, selectedFragment).commit();
                }
                return true;
            }
        });
    }
}