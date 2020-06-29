package it.univpm.mobile_programming_project.fragment.autenticazione;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private TextInputLayout txtEmailLayout;
    private TextInputLayout txtPasswordLayout;

    private EmailAutenticationManager autenticationManager;

    public LoginFragment() {
        autenticationManager = new EmailAutenticationManager();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLogin = view.findViewById(R.id.btnAccedi);
        txtEmail = view.findViewById(R.id.txtLoginEmailInput);
        txtPassword = view.findViewById(R.id.txtLoginPasswordInput);
        txtEmailLayout = view.findViewById(R.id.txtEmail);
        txtPasswordLayout = view.findViewById(R.id.txtPassword);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAccedi) {
            login();
        }
    }

    private void login() {
        Activity activity = getActivity();
        assert activity != null;

        boolean anyError = false;

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if( email.isEmpty() || !Helper.isEmailValid(email) ) {
            // Mail vuota o non valida
            txtEmailLayout.setError("Inserire una mail valida.");
            txtEmail.requestFocus();
            anyError = true;
        }else{
            txtEmailLayout.setError(null);
        }


        if( password.isEmpty() ) {
            // Mail vuota o non valida
            txtPasswordLayout.setError("Inserire una password.");
            txtPassword.requestFocus();
            anyError = true;
        }else{
            txtPasswordLayout.setError(null);
        }

        // Non c'è almeno un errore
        if( anyError ) return;

        ((SplashScreenActivity)this.getActivity()).startLoading();
        autenticationManager.setCredentials(email, password);
        autenticationManager.login(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Activity activity = getActivity();
                assert activity != null;

                if (!task.isSuccessful()) {
                    // Login fallito
                    Toast.makeText(getActivity().getApplicationContext(), "Credenziali non valide.", Toast.LENGTH_LONG).show();
                    ((SplashScreenActivity)LoginFragment.this.getActivity()).stopLoading();
                    return;
                }

                // Login ok
                ((SplashScreenActivity)LoginFragment.this.getActivity()).stopLoading();
                Toast.makeText(getActivity().getApplicationContext(), "Accesso effettuato", Toast.LENGTH_LONG).show();

                FirebaseFunctionsHelper functionsHelper = new FirebaseFunctionsHelper();
                functionsHelper.isUserInitialized().addOnCompleteListener(new OnCompleteListener<Boolean>()
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
                            ((SplashScreenActivity)LoginFragment.this.getActivity()).stopLoading();
                            return;
                        }

                        ((SplashScreenActivity)LoginFragment.this.getActivity()).stopLoading();

                        Boolean isInitialized = task.getResult();
                        if( isInitialized )
                        {
                            // Logged in and data set -> Redirect to home activity
                            SplashScreenActivity splashScreenActivity = (SplashScreenActivity) LoginFragment.this.getActivity();
                            splashScreenActivity.navigateToHomeActivity(null);
                        }else{
                            // Logged in but data is not set -> Redirect to scegli proprietario affittuario
                            NavController navController = Navigation.findNavController(getView());
                            navController.navigate(R.id.action_loginFragment_to_scegliProprietarioAffittuarioFragment);
                        }
                    }
                });
            }
        });
    }

}
