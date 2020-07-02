package it.univpm.mobile_programming_project.fragment.profilo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.utils.auth_helper.AuthenticationManager;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

public class ProfiloFragment extends Fragment {

    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private TextView nome;
    private TextView cognome;
    private TextView email;
    private Button logout;
    private Button modifica;
    private Button disiscriviti;

    private TextInputEditText nuova_pass;
    private TextInputEditText conferma_pass;
    private AuthenticationManager authenticationManager;
    private Context context;
    private Activity activity;
    private UtenteSharedPreferences utenteSharedPreferences;

    public ProfiloFragment() {
        // Required empty public constructor
        this.authenticationManager = new AuthenticationManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profilo, container, false);

        nome = view.findViewById(R.id.txtNome);
        cognome = view.findViewById(R.id.txtCognome);
        email = view.findViewById(R.id.txtEmail);
        logout = view.findViewById(R.id.btnLogout);
        modifica = view.findViewById(R.id.btnModifica);
        disiscriviti = view.findViewById(R.id.btnDisiscriviti);
        nuova_pass = view.findViewById(R.id.txtNewPasswordInput);
        conferma_pass = view.findViewById(R.id.txtConfermaPasswordInput);

        nome.setText(this.utenteSharedPreferences.getNome());
        cognome.setText(this.utenteSharedPreferences.getCognome());

        FirebaseUser firebaseUser = authenticationManager.getUser();
        email.setText(firebaseUser.getEmail());


        disiscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disiscrizione();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificaPassword();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = getActivity();
        this.utenteSharedPreferences = new UtenteSharedPreferences(this.context);
        this.firebaseFunctionsHelper = new FirebaseFunctionsHelper(this.utenteSharedPreferences);
    }

    private void disiscrizione() {

        ((HomeActivity)this.activity).startLoading();

        this.firebaseFunctionsHelper.disiscrizione()
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if(!task.isSuccessful()) {
                            Toast.makeText(ProfiloFragment.this.getActivity(), "Riprova, si è verificato un problema", Toast.LENGTH_LONG).show();
                            ((HomeActivity)ProfiloFragment.this.activity).stopLoading();
                            return;
                        }

                        Boolean disiscrizioneEffettuata = (Boolean)task.getResult();
                        if(disiscrizioneEffettuata) {
                            Toast.makeText(ProfiloFragment.this.getActivity(), "Cancellazione avvenuta con successo", Toast.LENGTH_LONG).show();

                            ProfiloFragment.this.utenteSharedPreferences.clearPreferences();
                            authenticationManager.logout();

                            Intent intent = new Intent(ProfiloFragment.this.activity, SplashScreenActivity.class);
                            startActivity(intent);
                            ProfiloFragment.this.activity.finish();
                        }else{
                            Toast.makeText(ProfiloFragment.this.getActivity(), "Cancellazione non avvenuta", Toast.LENGTH_LONG).show();
                        }
                        ((HomeActivity)ProfiloFragment.this.activity).stopLoading();
                    }
                });
    }

    private void logout(){

        ProfiloFragment.this.utenteSharedPreferences.clearPreferences();
        authenticationManager.logout();

        Intent intent = new Intent(ProfiloFragment.this.activity, SplashScreenActivity.class);
        startActivity(intent);
        ProfiloFragment.this.activity.finish();

    }

    private void modificaPassword() {

        String nuova_pass = this.nuova_pass.getText().toString();
        String conferma_pass = this.conferma_pass.getText().toString();

        ((HomeActivity)this.activity).startLoading();

        this.firebaseFunctionsHelper.modificaPassword(nuova_pass, conferma_pass)
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if(!task.isSuccessful()) {
                            Toast.makeText(ProfiloFragment.this.getActivity(), "Riprova, si è verificato un problema", Toast.LENGTH_LONG).show();
                            ((HomeActivity)ProfiloFragment.this.activity).stopLoading();
                            return;
                        }

                        Boolean modificaEffettuata = (Boolean)task.getResult();
                        if(modificaEffettuata) {
                            Toast.makeText(ProfiloFragment.this.getActivity(), "Modifica avvenuta con successo", Toast.LENGTH_LONG).show();

                            ProfiloFragment.this.utenteSharedPreferences.clearPreferences();
                            authenticationManager.logout();

                            Intent intent = new Intent(ProfiloFragment.this.activity, SplashScreenActivity.class);
                            startActivity(intent);
                            ProfiloFragment.this.activity.finish();
                        }else{
                            Toast.makeText(ProfiloFragment.this.getActivity(), "Credenziali inserite non corrette", Toast.LENGTH_LONG).show();
                        }
                        ((HomeActivity)ProfiloFragment.this.activity).stopLoading();
                    }
                });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }
}
