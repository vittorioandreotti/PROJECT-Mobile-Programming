package it.univpm.mobile_programming_project.fragment.spese.affittuario;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.functions.FirebaseFunctionsException;

import java.util.Date;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.proprietario.InserisciSpesaCondominioFragment;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;


public class InserisciSpesaComuneFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private TextInputEditText txtNomeSpesaComuneInput;
    private TextInputEditText txtDataSpesaComuneInput;
    private TextInputEditText txtImportoSpesaComuneInput;
    private TextInputEditText txtDescrizioneSpesaComuneInput;

    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private UtenteSharedPreferences utenteSharedPreferences;

    public InserisciSpesaComuneFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utenteSharedPreferences = new UtenteSharedPreferences(getContext());
        firebaseFunctionsHelper = new FirebaseFunctionsHelper(utenteSharedPreferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inserisci_spesa_comune, container, false);

        txtNomeSpesaComuneInput = v.findViewById(R.id.txtNomeSpesaComuneInput);
        txtImportoSpesaComuneInput = v.findViewById(R.id.txtImportoSpesaComuneInput);
        txtDescrizioneSpesaComuneInput = v.findViewById(R.id.txtDescrizioneSpesaComuneInput);

        v.findViewById(R.id.btnInserisciSpesaComune).setOnClickListener(this);

        txtDataSpesaComuneInput = v.findViewById(R.id.txtDataSpesaComune);
        txtDataSpesaComuneInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        return v;
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txtDataSpesaComuneInput.setText(String.format("%d/%d/%d", dayOfMonth, month, year));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnInserisciSpesaComune:
                inserisciSpesaComune();
                break;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void inserisciSpesaComune() {
        String nomeSpesa = txtNomeSpesaComuneInput.getText().toString();
        String descSpesa = txtDescrizioneSpesaComuneInput.getText().toString();
        String dataSpesaStringa = txtDataSpesaComuneInput.getText().toString();
        Double importoSpesa;

        try {
            importoSpesa = Double.parseDouble(txtImportoSpesaComuneInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoSpesa = 0.0;
        }

        ((HomeActivity) InserisciSpesaComuneFragment.this.getActivity()).startLoading();
        this.firebaseFunctionsHelper
                .inserisciSpesaComune(importoSpesa, nomeSpesa, dataSpesaStringa, descSpesa)
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (!task.isSuccessful()) {
                            Exception e = task.getException();
                            if (e instanceof FirebaseFunctionsException) {
                                FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                                FirebaseFunctionsException.Code code = ffe.getCode();
                                Object details = ffe.getDetails();
                            }
                            ((HomeActivity) InserisciSpesaComuneFragment.this.getActivity()).stopLoading();
                            return;
                        }

                        Boolean isSpesaInseritaCorrettamente = task.getResult();
                        if(isSpesaInseritaCorrettamente) {
                            // Spesa inserita correttamente
                            Toast.makeText(InserisciSpesaComuneFragment.this.getContext(), "Spesa inserita con successo.", Toast.LENGTH_LONG).show();
                        } else {
                            // Spesa non inserita
                            Toast.makeText(InserisciSpesaComuneFragment.this.getContext(), "Spesa non inserita.", Toast.LENGTH_LONG).show();
                        }
                        ((HomeActivity)InserisciSpesaComuneFragment.this.getActivity()).stopLoading();
                    }

                });

    }
}
