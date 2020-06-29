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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.functions.FirebaseFunctionsException;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InserisciCodiceCasaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserisciCodiceCasaFragment extends Fragment implements View.OnClickListener {

    private FragmentContainerView loadingFragment;
    private final FirebaseFunctionsHelper firebaseFunctionsHelper;
    private TextInputEditText txtCodiceCasaInput;

    public InserisciCodiceCasaFragment() {
        this.firebaseFunctionsHelper = new FirebaseFunctionsHelper();
    }


    public static InserisciCodiceCasaFragment newInstance() {
        return new InserisciCodiceCasaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnPartecipaCasa).setOnClickListener(this);
        txtCodiceCasaInput = view.findViewById(R.id.txtCodiceCasaInput);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserisci_codice_casa, container, false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPartecipaCasa)
        {
            ((SplashScreenActivity)this.getActivity()).startLoading();

            this.firebaseFunctionsHelper.inserisciAffittuario().addOnCompleteListener(new OnCompleteListener<Boolean>() {
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
                        ((SplashScreenActivity)InserisciCodiceCasaFragment.this.getActivity()).stopLoading();
                        return;
                    }

                    Boolean isAffittuarioInserito = task.getResult();
                    if (isAffittuarioInserito) {
                        String codiceCasa = txtCodiceCasaInput.getText().toString();


                        if(codiceCasa.equals("")) {
                            Toast.makeText(InserisciCodiceCasaFragment.this.getContext(), "Inserire un codice casa.", Toast.LENGTH_LONG).show();
                            ((SplashScreenActivity)InserisciCodiceCasaFragment.this.getActivity()).stopLoading();
                            return;
                        }


                        InserisciCodiceCasaFragment.this.firebaseFunctionsHelper.partecipaCasa(codiceCasa).addOnCompleteListener(new OnCompleteListener<Boolean>() {
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
                                    ((SplashScreenActivity)InserisciCodiceCasaFragment.this.getActivity()).stopLoading();
                                    return;
                                }

                                Boolean isCasaValid = task.getResult();
                                if (isCasaValid) {
                                    // Casa valida -> Redirect to home activity
                                    ((SplashScreenActivity)InserisciCodiceCasaFragment.this.getActivity()).stopLoading();
                                    SplashScreenActivity splashScreenActivity = (SplashScreenActivity) InserisciCodiceCasaFragment.this.getActivity();
                                    splashScreenActivity.navigateToHomeActivity(null);
                                } else {
                                    // Casa non valida
                                    Toast.makeText(InserisciCodiceCasaFragment.this.getContext(), "Codice casa non valido.", Toast.LENGTH_LONG).show();
                                    ((SplashScreenActivity)InserisciCodiceCasaFragment.this.getActivity()).stopLoading();
                                }
                            }
                        });
                    } else {
                        // Casa non creata
                        Toast.makeText(InserisciCodiceCasaFragment.this.getContext(), "Errore nella creazione dell'utente.", Toast.LENGTH_LONG).show();
                    }

                    ((SplashScreenActivity)InserisciCodiceCasaFragment.this.getActivity()).stopLoading();
                }
            });
        }
    }
}
