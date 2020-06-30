package it.univpm.mobile_programming_project.fragment.autenticazione;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.functions.FirebaseFunctionsException;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.auth_helper.EmailAutenticationManager;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrazioneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrazioneFragment extends Fragment implements View.OnClickListener{

    private TextInputEditText txtNome;
    private TextInputEditText txtCognome;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private TextInputEditText txtPasswordConferma;

    private TextInputLayout txtNomeLayout;
    private TextInputLayout txtCognomeLayout;
    private TextInputLayout txtEmailLayout;
    private TextInputLayout txtPasswordLayout;
    private TextInputLayout txtPasswordConfermaLayout;
    private EmailAutenticationManager autenticationManager;

    public RegistrazioneFragment() {
        autenticationManager = new EmailAutenticationManager();
    }

    public static RegistrazioneFragment newInstance() {
        RegistrazioneFragment fragment = new RegistrazioneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registrazione, container, false);

        view.findViewById(R.id.btnRegistrati).setOnClickListener(this);

        txtNome = view.findViewById(R.id.txtNomeInput);
        txtCognome = view.findViewById(R.id.txtCognomeInput);
        txtEmail = view.findViewById(R.id.txtEmailInput);
        txtPassword = view.findViewById(R.id.txtPasswordInput);
        txtPasswordConferma = view.findViewById(R.id.txtPasswordConfirmInput);

        txtNomeLayout = view.findViewById(R.id.txtNome);
        txtCognomeLayout = view.findViewById(R.id.txtCognome);
        txtEmailLayout = view.findViewById(R.id.txtEmail);
        txtPasswordLayout = view.findViewById(R.id.txtPassword);
        txtPasswordConfermaLayout = view.findViewById(R.id.txtPasswordConfirm);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegistrati) {
            registrazione();
        }
    }

    private void registrazione() {

        Activity activity = getActivity();
        assert activity != null;

        boolean anyError = false;

        final String nome = txtNome.getText().toString();
        final String cognome = txtCognome.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String passwordConferma = txtPasswordConferma.getText().toString();

        if( email.isEmpty() || !Helper.isEmailValid(email) ) {
            // Mail vuota o non valida
            txtEmailLayout.setError("Inserire una mail valida.");
            txtEmail.requestFocus();
            anyError = true;
        }else{
            txtEmailLayout.setError(null);
        }

        if( nome.isEmpty() ) {
            // Mail vuota o non valida
            txtNomeLayout.setError("Inserire un nome.");
            txtNome.requestFocus();
            anyError = true;
        }else{
            txtNomeLayout.setError(null);
        }

        if( cognome.isEmpty() ) {
            // Mail vuota o non valida
            txtCognomeLayout.setError("Inserire un cognome.");
            txtCognome.requestFocus();
            anyError = true;
        }else{
            txtCognomeLayout.setError(null);
        }

        if( password.length() < 7 ) {
            // Mail vuota o non valida
            txtPasswordLayout.setError("Inserire una password di almeno 7 caratteri.");
            txtPassword.requestFocus();
            anyError = true;
        }else{
            txtPasswordLayout.setError(null);

            // Controllata solo se la password è di almeno 7 caratteri
            if( !passwordConferma.equals(password) ) {
                txtPasswordConfermaLayout.setError("La password deve combaciare.");
                txtPasswordConferma.requestFocus();
                anyError = true;
            }else{
                txtPasswordConfermaLayout.setError(null);
            }
        }

        // Non c'è almeno un errore
        if( anyError ) return;

        ((SplashScreenActivity)this.getActivity()).startLoading();
        autenticationManager.setCredentials(email, password);

        autenticationManager.registrazione(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Activity activity = getActivity();
                assert activity != null;

                if (!task.isSuccessful()) {
                    // Login fallito
                    Toast.makeText(getActivity().getApplicationContext(), "Registrazione fallita.", Toast.LENGTH_LONG).show();
                    ((SplashScreenActivity)RegistrazioneFragment.this.getActivity()).stopLoading();
                    return;
                }

                // Registrazione OK
                FirebaseFunctionsHelper functionsHelper = new FirebaseFunctionsHelper();
                functionsHelper.registraUtente(nome, cognome).addOnCompleteListener(new OnCompleteListener<Boolean>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {

                        // Handle Error
                        if (!task.isSuccessful()) {

                            Exception e = task.getException();
                            if (e instanceof FirebaseFunctionsException) {
                                FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                                FirebaseFunctionsException.Code code = ffe.getCode();
                                Object details = ffe.getDetails();
                            }
                            ((SplashScreenActivity)RegistrazioneFragment.this.getActivity()).stopLoading();
                            return;
                        }

                        Boolean registrazioneAvvenuta = task.getResult();
                        if( registrazioneAvvenuta )
                        {
                            // Logged in but data is not set -> Redirect to scegli proprietario affittuario
                            ((SplashScreenActivity)RegistrazioneFragment.this.getActivity()).stopLoading();
                            NavController navController = Navigation.findNavController(getView());
                            navController.navigate(R.id.action_registrazioneFragment_to_scegliProprietarioAffittuarioFragment);
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), "Registrazione fallita.", Toast.LENGTH_LONG).show();
                            ((SplashScreenActivity)RegistrazioneFragment.this.getActivity()).stopLoading();
                        }
                    }
                });
            }
        });
    }

}
