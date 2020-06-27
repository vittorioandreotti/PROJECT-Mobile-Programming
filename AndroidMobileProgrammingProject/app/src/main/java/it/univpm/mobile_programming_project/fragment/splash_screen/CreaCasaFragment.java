package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.functions.FirebaseFunctionsException;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.fragment.LoadingFragment;
import it.univpm.mobile_programming_project.fragment.autenticazione.GoogleAuthFragment;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreaCasaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaCasaFragment extends Fragment implements View.OnClickListener {

    FragmentContainerView loadingFragment;
    FirebaseFunctionsHelper firebaseFunctionsHelper;
    TextInputEditText txtNomeCasa;
    TextInputEditText txtIndirizzoCasa;

    public CreaCasaFragment() {
        // Required empty public constructor
        this.firebaseFunctionsHelper = new FirebaseFunctionsHelper();
    }

    public static CreaCasaFragment newInstance() {
        return new CreaCasaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        (view.findViewById(R.id.btnCreaCasa)).setOnClickListener(this);
        this.txtNomeCasa = view.findViewById(R.id.txtNomeCasaInput);
        this.txtIndirizzoCasa = view.findViewById(R.id.txtIndirizzoCasaInput);
        this.loadingFragment = view.findViewById(R.id.fragmentLoading);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crea_casa, container, false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCreaCasa) {

            loadingFragment.setVisibility(View.VISIBLE);

            this.firebaseFunctionsHelper.inserisciProprietario().addOnCompleteListener(new OnCompleteListener<Boolean>() {
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
                        loadingFragment.setVisibility(View.GONE);
                        return;
                    }

                    Boolean isProprietarioInserito = task.getResult();
                    if( isProprietarioInserito )
                    {

                        String nome;
                        String indirizzo;
                        nome = CreaCasaFragment.this.txtNomeCasa.getText().toString();
                        indirizzo = CreaCasaFragment.this.txtIndirizzoCasa.getText().toString();

                        CreaCasaFragment.this.firebaseFunctionsHelper.creaCasa(nome, indirizzo).addOnCompleteListener(new OnCompleteListener<Boolean>() {
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
                                    loadingFragment.setVisibility(View.GONE);
                                    return;
                                }

                                Boolean isCasaCreated = task.getResult();
                                if( isCasaCreated )
                                {
                                    // Casa creata -> Redirect to home activity
                                    loadingFragment.setVisibility(View.GONE);
                                    SplashScreenActivity splashScreenActivity = (SplashScreenActivity) CreaCasaFragment.this.getActivity();
                                    splashScreenActivity.navigateToHomeActivity(null);
                                }else{
                                    // Casa non creata
                                    Toast.makeText(CreaCasaFragment.this.getContext(), "Errore nella creazione della casa.", Toast.LENGTH_LONG).show();
                                }

                                loadingFragment.setVisibility(View.GONE);
                            }
                        });
                    }else{
                        // Casa non creata
                        Toast.makeText(CreaCasaFragment.this.getContext(), "Errore nella creazione dell'utente.", Toast.LENGTH_LONG).show();
                    }

                    loadingFragment.setVisibility(View.GONE);
                }
            });
        }
    }
}
