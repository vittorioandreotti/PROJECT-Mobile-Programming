package it.univpm.mobile_programming_project.fragment.spese.proprietario;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InserisciBolletteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserisciBolletteFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText txtDataSpesaBollettaInput;
    private TextInputEditText txtDataScadenzaBollettaInput;
    private TextInputEditText txtNomeCategoriaBollettaInput;
    private TextInputEditText txtImportoBollettaInput;

    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private UtenteSharedPreferences utenteSharedPreferences;

    public InserisciBolletteFragment() {
        firebaseFunctionsHelper = new FirebaseFunctionsHelper(utenteSharedPreferences);
    }

    public static InserisciBolletteFragment newInstance() {
        InserisciBolletteFragment fragment = new InserisciBolletteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utenteSharedPreferences = new UtenteSharedPreferences(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inserisci_bolletta, container, false);

        txtNomeCategoriaBollettaInput = v.findViewById(R.id.txtNomeCategoriaBollettaInput);
        txtImportoBollettaInput = v.findViewById(R.id.txtImportoBollettaInput);

        txtDataSpesaBollettaInput = v.findViewById(R.id.txtDataSpesaBollettaInput);
        txtDataScadenzaBollettaInput = v.findViewById(R.id.txtDataScadenzaBollettaInput);

        txtDataSpesaBollettaInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogInserimento(v);
            }
        });

        txtDataScadenzaBollettaInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogScadenza(v);
            }
        });

        v.findViewById(R.id.btnInsericiBolletta).setOnClickListener(this);

        return v;
    }


    private void showDatePickerDialogScadenza(View view) {
        DialogFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtDataScadenzaBollettaInput.setText(String.format(Helper.getDateFormat(), dayOfMonth, month, year));
            }
        });
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    private void showDatePickerDialogInserimento(View view) {
        DialogFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtDataSpesaBollettaInput.setText(String.format(Helper.getDateFormat(), dayOfMonth, month, year));
            }
        });
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnInsericiBolletta:
                inserisciBolletta();
                break;
        }
    }

    private void inserisciBolletta() {
        String nomeCategoriaBollettaInput = this.txtNomeCategoriaBollettaInput.getText().toString();
        String dataSpesaBollettaInputString = this.txtDataSpesaBollettaInput.getText().toString();
        String dataScadenzaInputString = this.txtDataScadenzaBollettaInput.getText().toString();
        Double importoBollettaInput;
        try {
            importoBollettaInput = Double.parseDouble(txtImportoBollettaInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoBollettaInput = 0.0;
        }

        this.firebaseFunctionsHelper.inserisciSpesaBolletta(importoBollettaInput, nomeCategoriaBollettaInput, dataSpesaBollettaInputString, dataScadenzaInputString);
    }
}