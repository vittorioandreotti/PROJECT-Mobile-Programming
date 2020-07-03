package it.univpm.mobile_programming_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

import it.univpm.mobile_programming_project.custom_loading_activity.AppCompatActivityWithLoading;
import it.univpm.mobile_programming_project.fragment.HomeFragment;
import it.univpm.mobile_programming_project.fragment.profilo.ProfiloFragment;
import it.univpm.mobile_programming_project.fragment.spese.affittuario.InserimentoSpeseAffittuarioFragment;
import it.univpm.mobile_programming_project.fragment.spese.affittuario.SpeseAffittuarioFragment;
import it.univpm.mobile_programming_project.fragment.spese.proprietario.InserimentoSpeseProprietarioFragment;
import it.univpm.mobile_programming_project.fragment.tornei.TorneiFragment;
import it.univpm.mobile_programming_project.utils.auth_helper.AuthenticationManager;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

public class HomeActivity extends AppCompatActivityWithLoading implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Bundle savedInstanceState;

    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private UtenteSharedPreferences sharedPreferences;
    private TextView navHeaderFullName;
    private TextView navHeaderEmail;
    private AuthenticationManager authenticationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.savedInstanceState = savedInstanceState;
        this.sharedPreferences = new UtenteSharedPreferences(this);
        this.authenticationManager = new AuthenticationManager();

        final HomeActivity context = this;

        navigationView = findViewById(R.id.navigation_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView.setNavigationItemSelectedListener(context);

        final GuiAsyncTask asyncGuiSetup = new GuiAsyncTask(new OnTaskResultListener() {
            @Override
            public void onPreExecute() {
                context.startLoading();
            }

            @Override
            public Object doInBackground(Object obj) {

                context.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        context.navigationView.getMenu().clear();

                        if(context.sharedPreferences.isProprietario()) {
                            context.navigationView.inflateMenu(R.menu.drawer_menu_proprietario);
                        }else{
                            context.navigationView.inflateMenu(R.menu.drawer_menu_affittuario);
                        }

                        // This prevent fragment from being replaced on rotation
                        if( context.savedInstanceState == null ){

                            Fragment homeFragment;

//                            if(preferences.isProprietario()) {
//                                homeFragment = new HomeProprietarioFragment();
//                            }else{
//                                homeFragment = new HomeAffittuarioFragment();
//                            }
                            homeFragment = new HomeFragment();

                            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, homeFragment ).commit();
                            navigationView.setCheckedItem(R.id.nav_home);
                            setToolbarTitle(getString(R.string.homepage));
                        }

                        context.actionBarDrawerToggle = new ActionBarDrawerToggle(
                                context,
                                context.drawerLayout,
                                context.toolbar,
                                R.string.navigation_drawer_open,
                                R.string.navigation_drawer_close
                        );

                        context.drawerLayout.addDrawerListener(context.actionBarDrawerToggle);
                        context.actionBarDrawerToggle.syncState();
                    }

                });

                return null;
            }

            @Override
            public void onPostExecute(Object obj) {
                context.navigationView.invalidate();
                context.navigationView.bringToFront();
                context.actionBarDrawerToggle.syncState();

                View navViewHeader = context.navigationView.getHeaderView(0);

                navHeaderFullName = navViewHeader.findViewById(R.id.txtPlaceholderFullName);
                navHeaderEmail = navViewHeader.findViewById(R.id.txtPlaceholderEmail);

                navHeaderFullName.setText(HomeActivity.this.sharedPreferences.getNome() + " " + HomeActivity.this.sharedPreferences.getCognome());
                navHeaderEmail.setText(context.authenticationManager.getUser().getEmail());

                context.stopLoading();
            }

        });

        if( !this.sharedPreferences.isInitialized() ) {
            context.startLoading();
            FirebaseFunctionsHelper functionsHelper = new FirebaseFunctionsHelper();
            functionsHelper.getUtenteAndCasa().addOnCompleteListener(new OnCompleteListener<HashMap<String, Object>>()
            {
                @Override
                public void onComplete(@NonNull Task<HashMap<String, Object>> task)
                {
                    if(!task.isSuccessful()) return;


                    HashMap<String, Object> result = task.getResult();

                    if((Boolean)result.get("error")) {
                        Toast.makeText(context, "Errore nella lettura delle informazioni sull'utente e la casa.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    HashMap<String, String> resultCasa = (HashMap<String, String>) result.get("casa");
                    HashMap<String, String> resultUtente = (HashMap<String, String>) result.get("utente");

                    context.sharedPreferences.setIdUtente(resultUtente.get("id"));
                    context.sharedPreferences.setNome(resultUtente.get("nome"));
                    context.sharedPreferences.setCognome(resultUtente.get("cognome"));
                    context.sharedPreferences.setTipo(resultUtente.get("tipo"));

                    context.sharedPreferences.setIdCasa(resultCasa.get("id"));
                    context.sharedPreferences.setNomeCasa(resultCasa.get("nome"));
                    context.sharedPreferences.setIndirizzoCasa(resultCasa.get("indirizzo"));

                    context.sharedPreferences.setInitialized();
                    asyncGuiSetup.execute();
                }
            });
        }else{
            asyncGuiSetup.execute();
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

        if( this.sharedPreferences.isAffittuario() ) {
            return onNavigationItemSelectedAffittuario(item);
        }else{
            return onNavigationItemSelectedProprietario(item);
        }

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

            // Log-Out
            case R.id.nav_logout:
                logout();
                return true;

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

            case R.id.nav_spesecondominio:
                navigationFragment = new SpeseAffittuarioFragment(SpeseAffittuarioFragment.SPESACONDOMINIO);
                titleId = R.string.spese_condominio;
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

            // Log-Out
            case R.id.nav_logout:
                logout();
                return true;

            default:
                return false;
        }

        // Go to right fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, navigationFragment ).commit();
        setToolbarTitle( getString(titleId) );
        drawerLayout.closeDrawers();

        return true;
    }

    private void logout() {
        FirebaseUser firebaseUser = authenticationManager.getUser();
        HomeActivity.this.sharedPreferences.clearPreferences();
        authenticationManager.logout();

        Intent intent = new Intent(HomeActivity.this, SplashScreenActivity.class);
        startActivity(intent);
        HomeActivity.this.finish();
    }

    public void setToolbarTitle(String newTitle) {
        ((Toolbar)findViewById(R.id.toolbar)).setTitle( newTitle );
    }


    /**
     * Interfaccia che permette di ascoltare quando la GUI è pronta
     */
    public interface OnTaskResultListener{
        void onPreExecute();
        void onPostExecute(Object obj);
        Object doInBackground(Object obj);
    }

    public class GuiAsyncTask extends AsyncTask<Object,Object,Object> { //change Object to required type

        private OnTaskResultListener listener;

        GuiAsyncTask(OnTaskResultListener listener){
            this.listener=listener;
        }


        @Override
        protected Object doInBackground(Object... objects) {
            return listener.doInBackground(objects);
        }

        @Override
        protected void onPreExecute() {
            listener.onPreExecute();
        }


        @Override
        protected void onPostExecute(Object obj){
            listener.onPostExecute(obj);
        }
    }

}
