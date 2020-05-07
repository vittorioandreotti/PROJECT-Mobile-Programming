package it.univpm.mobile_programming_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.univpm.mobile_programming_project.fragment.FragmentHome;
import it.univpm.mobile_programming_project.fragment.FragmentSpese;
import it.univpm.mobile_programming_project.fragment.FragmentTornei;

public class AccediActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private FragmentHome fragmentHome;
    private FragmentSpese fragmentSpese;
    private FragmentTornei fragmentTornei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accedi);
        fragmentHome=new FragmentHome();
        fragmentSpese = new FragmentSpese();
        fragmentTornei = new FragmentTornei();

        bottomNav=findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=null;
                switch(item.getItemId()){
                    case R.id.menu_home:
                        selectedFragment=fragmentHome;
                        break;
                    case R.id.menu_spese:
                        selectedFragment=fragmentSpese;
                        break;
                    case R.id.menu_tornei:
                        selectedFragment=fragmentTornei;
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
