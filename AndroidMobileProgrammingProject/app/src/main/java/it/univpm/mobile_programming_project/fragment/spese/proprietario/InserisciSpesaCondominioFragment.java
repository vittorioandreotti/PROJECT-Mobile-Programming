package it.univpm.mobile_programming_project.fragment.spese.proprietario;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.CloseKeyboard;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;


public class InserisciSpesaCondominioFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private TextInputEditText txtDataSpesaCondominioInput;
    private TextInputEditText txtNomeSpesaCondominioInput;
    private TextInputEditText txtImportoSpesaCondominioInput;

    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private UtenteSharedPreferences utenteSharedPreferences;

    public InserisciSpesaCondominioFragment() {
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
        View v = inflater.inflate(R.layout.fragment_inserisci_spesa_condominio, container, false);

        txtNomeSpesaCondominioInput = v.findViewById(R.id.txtNomeSpesaCondominioInput);
        txtImportoSpesaCondominioInput = v.findViewById(R.id.txtImportoSpesaCondominioInput);

        txtDataSpesaCondominioInput = v.findViewById(R.id.txtDataSpesaCondominioInput);

        txtDataSpesaCondominioInput.setOnClickListener(this);
        v.findViewById(R.id.btnInserisciSpesaCondominio).setOnClickListener(this);

        return v;
    }

    private void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (month >= 0) month = month+1;
        String formattedDate = String.format("%d/%d/%d", dayOfMonth, month, year);
        txtDataSpesaCondominioInput.setText(formattedDate);
    }

    @Override
    public void onClick(View v) {
        new CloseKeyboard(getActivity());

        switch (v.getId())
        {
            case R.id.btnInserisciSpesaCondominio:
                inserisciSpesaCondominio();
                break;

            case R.id.txtDataSpesaCondominioInput:
                showDatePickerDialog(v);
                break;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void inserisciSpesaCondominio() {

        String nomeSpesa = txtNomeSpesaCondominioInput.getText().toString();
        String dataSpesaStringa = txtDataSpesaCondominioInput.getText().toString();
        Double importoSpesa;

        try {
            importoSpesa = Double.parseDouble(txtImportoSpesaCondominioInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoSpesa = 0.0;
        }

        if (nomeSpesa.isEmpty() || dataSpesaStringa.isEmpty() || importoSpesa.isNaN()) {
            Toast.makeText(InserisciSpesaCondominioFragment.this.getContext(), "Inserisci tutti i campi", Toast.LENGTH_LONG).show();
            return;
        }

        ((HomeActivity) InserisciSpesaCondominioFragment.this.getActivity()).startLoading();
        this.firebaseFunctionsHelper
                .inserisciSpesaCondominio(importoSpesa, nomeSpesa, dataSpesaStringa)
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
                            ((HomeActivity) InserisciSpesaCondominioFragment.this.getActivity()).stopLoading();
                            return;
                        }

                        Boolean isSpesaInseritaCorrettamente = task.getResult();
                        if(isSpesaInseritaCorrettamente) {
                            // Spesa inserita correttamente

                            txtNomeSpesaCondominioInput.setText("");
                            txtDataSpesaCondominioInput.setText("");
                            txtImportoSpesaCondominioInput.setText("");

                            Toast.makeText(InserisciSpesaCondominioFragment.this.getContext(), "Spesa inserita con successo.", Toast.LENGTH_LONG).show();
                        } else {
                            // Spesa non inserita
                            Toast.makeText(InserisciSpesaCondominioFragment.this.getContext(), "Spesa non inserita.", Toast.LENGTH_LONG).show();
                        }
                        ((HomeActivity)InserisciSpesaCondominioFragment.this.getActivity()).stopLoading();
                    }

                });

    }
}

