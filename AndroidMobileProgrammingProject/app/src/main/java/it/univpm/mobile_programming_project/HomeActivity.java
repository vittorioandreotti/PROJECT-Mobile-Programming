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

import it.univpm.mobile_programming_project.custom_loading_activity.AppCompatActivityWithLoading;
import it.univpm.mobile_programming_project.fragment.HomeFragment;
import it.univpm.mobile_programming_project.fragment.TorneiFragment;
import it.univpm.mobile_programming_project.fragment.profilo.ProfiloFragment;
import it.univpm.mobile_programming_project.fragment.spese.InserimentoSpeseAffittuarioFragment;
import it.univpm.mobile_programming_project.fragment.spese.InserimentoSpeseProprietarioFragment;
import it.univpm.mobile_programming_project.fragment.spese.SpeseAffittuarioFragment;
import it.univpm.mobile_programming_project.fragment.splash_screen.CreaCasaFragment;

public class HomeActivity extends AppCompatActivityWithLoading implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.savedInstanceState = savedInstanceState;

    }

    @Override
    protected void onStart() {
        super.onStart();

        // INIZIA CARICAMENTO

        // Prendi l'utente autenticato e salva i suoi dati in shared preferences
        //

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

//        if(PROPRIETARIO) {
            navigationView.inflateMenu(R.menu.drawer_menu_proprietario);
//        }else{
//            navigationView.inflateMenu(R.menu.drawer_menu_affittuario);
//        }
        //TODO: MODIFICARE

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
        if( this.savedInstanceState == null ){
            Fragment homeFragment;

            /*
            if( AFFITTUARIO ) {
                homeFragment = new HomeAffittuarioFragment();
            }else{
                homeFragment = new HomeProprietarioFragment();
            }*/
            homeFragment = new HomeFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, homeFragment ).commit();
            navigationView.setCheckedItem(R.id.nav_home);
            setToolbarTitle(getString(R.string.homepage));
        }

        // FINE CARICAMENTO

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

//        if( AFFITTUARIO ) {
//            return onNavigationItemSelectedAffittuario(item);
//        }else{
            return onNavigationItemSelectedProprietario(item);
//        }
        //TODO: MODIFICARE

    }

    private boolean onNavigationItemSelectedProprietario(@NonNull MenuItem item) {
        Fragment navigationFragment = null;
        int titleId;

        switch (item.getItemId())
        {
            // Home
            case R.id.nav_home:
                navigationFragment = new HomeFragment();
                titleId = R.string.homepage;
                break;

//            // STATISTICHE
//            case R.id.nav_statistiche:
//                navigationFragment = new StatisticheFragment();
//                titleId = R.string.statistiche;
//                break;


            // SOMMARIO
//            case R.id.nav_sommario_pagate:
//                navigationFragment = new StatisticheFragment();
//                titleId = R.string.pagate;
//                break;
//
//            case R.id.nav_sommario_non_pagate:
//                navigationFragment = new StatisticheFragment();
//                titleId = R.string.non_pagate;
//                break;



            // SPESE
            case R.id.nav_ins_spesa_condominio:
                navigationFragment = new InserimentoSpeseProprietarioFragment(InserimentoSpeseProprietarioFragment.SPESACONDOMINIO);
                titleId = R.string.inserisci_spesa_condominio;
                break;

            case R.id.nav_ins_bollette:
                navigationFragment = new InserimentoSpeseProprietarioFragment(InserimentoSpeseProprietarioFragment.BOLLETTE);
                titleId = R.string.inserisci_bollette;
                break;

            case R.id.nav_ins_affitto:
                navigationFragment = new InserimentoSpeseProprietarioFragment(InserimentoSpeseProprietarioFragment.AFFITTO);
                titleId = R.string.inserisci_affitto;
                break;




            // TORNEI
            //crea
            case R.id.nav_crea_un_torneo:
                navigationFragment = new TorneiFragment(TorneiFragment.CREA);
                titleId = R.string.creatorneo;
                break;

            //partecipa
            case R.id.nav_partecipa_torneo:
                navigationFragment = new TorneiFragment(TorneiFragment.PARTECIPA);
                titleId = R.string.partecipatorneo;
                break;

            //storico
            case R.id.nav_storico_torneo:
                navigationFragment = new TorneiFragment(TorneiFragment.STORICO);
                titleId = R.string.storicotorneo;
                break;

            // Profilo
            case R.id.nav_profilo:
                navigationFragment = new ProfiloFragment();
                titleId = R.string.profilo;
                break;

            default:
                return false;
        }

        // Go to right fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, navigationFragment ).commit();
        setToolbarTitle( getString(titleId) );
        drawerLayout.closeDrawers();

        return true;
    }

    private boolean onNavigationItemSelectedAffittuario(@NonNull MenuItem item) {
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
            // SPESE
            case R.id.nav_gestione_spesa_comune:
                navigationFragment = new InserimentoSpeseAffittuarioFragment(InserimentoSpeseAffittuarioFragment.SPESACOMUNE);
                titleId = R.string.gestisciSpesaComune;
                break;

            case R.id.nav_sommario:
                navigationFragment = new SpeseAffittuarioFragment(SpeseAffittuarioFragment.SOMMARIO);
                titleId = R.string.sommario;
                break;

            case R.id.nav_spesacomune:
                navigationFragment = new SpeseAffittuarioFragment(SpeseAffittuarioFragment.SPESACOMUNE);
                titleId = R.string.spesacomune;
                break;

            case R.id.nav_affitto:
                navigationFragment = new SpeseAffittuarioFragment(SpeseAffittuarioFragment.AFFITTO);
                titleId = R.string.affitto;
                break;

            case R.id.nav_bollette:
                navigationFragment = new SpeseAffittuarioFragment(SpeseAffittuarioFragment.BOLLETTE);
                titleId = R.string.bollette;
                break;


            // TORNEI
            //crea
            case R.id.nav_crea_un_torneo:
                navigationFragment = new TorneiFragment(TorneiFragment.CREA);
                titleId = R.string.creatorneo;
                break;

            //partecipa
            case R.id.nav_partecipa_torneo:
                navigationFragment = new TorneiFragment(TorneiFragment.PARTECIPA);
                titleId = R.string.partecipatorneo;
                break;

            //storico
            case R.id.nav_storico_torneo:
                navigationFragment = new TorneiFragment(TorneiFragment.STORICO);
                titleId = R.string.storicotorneo;
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

    public void setToolbarTitle(String newTitle)
    {
        ((Toolbar)findViewById(R.id.toolbar)).setTitle( newTitle );
    }


}
