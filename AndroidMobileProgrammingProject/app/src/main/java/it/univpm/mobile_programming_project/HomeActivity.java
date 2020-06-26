package it.univpm.mobile_programming_project;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import it.univpm.mobile_programming_project.fragment.HomeFragment;
import it.univpm.mobile_programming_project.fragment.TorneiFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Prendi l'utente autenticato e salva i suoi dati in shared preferences
        //

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // This prevent fragment from being replaced on rotation
        if( savedInstanceState == null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new HomeFragment() ).commit();
            navigationView.setCheckedItem(R.id.nav_home);
            setToolbarTitle(getString(R.string.homepage));
        }
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            // Drawer is open
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            // Drawer is NOT open
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment navigationFragment;
        int titleId;

        switch (item.getItemId())
        {
            // Home
            case R.id.nav_home:
                navigationFragment = new HomeFragment();
                titleId = R.string.homepage;
                break;

//            // Statistiche
//            case R.id.nav_statistiche:
//                navigationFragment = new StatisticheFragment();
//                titleId = R.string.statistiche;
//                break;
//
//            // Spese
//            case R.id.nav_spese:
//                navigationFragment = new SpeseFragment();
//                titleId = R.string.spese;
//                break;
//
            // Tornei
            case R.id.nav_tornei:
                navigationFragment = new TorneiFragment();
               titleId = R.string.tornei;
                break;
//
//            // Profilo
//            case R.id.nav_profilo:
//                navigationFragment = new ProfiloFragment();
//                titleId = R.string.profilo;
//                break;

            default:
                return false;
        }

        // Go to right fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, navigationFragment ).commit();
        setToolbarTitle( getString(titleId) );
        drawerLayout.closeDrawers();

        return true;
    }

    private void setToolbarTitle(String newTitle)
    {
        newTitle = getString(R.string.app_name) + " - " + newTitle;

        ((Toolbar)findViewById(R.id.toolbar)).setTitle( newTitle );
    }
}
