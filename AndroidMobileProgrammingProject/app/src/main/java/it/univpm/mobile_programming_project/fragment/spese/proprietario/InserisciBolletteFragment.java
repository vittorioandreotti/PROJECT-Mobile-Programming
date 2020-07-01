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
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InserisciBolletteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserisciBolletteFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private TextInputEditText txtDataSpesaBollettaInput;
    private TextInputEditText txtDataScadenzaInput;
    private TextInputEditText txtNomeCategoriaBollettaInput;
    private TextInputEditText txtImportoBollettaInput;

    public InserisciBolletteFragment() {
        // Required empty public constructor
    }

    public static InserisciBolletteFragment newInstance() {
        InserisciBolletteFragment fragment = new InserisciBolletteFragment();
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
        View v = inflater.inflate(R.layout.fragment_inserisci_bolletta, container, false);

        txtNomeCategoriaBollettaInput = v.findViewById(R.id.txtNomeCategoriaBollettaInput);
        txtImportoBollettaInput = v.findViewById(R.id.txtImportoBollettaInput);

        txtDataSpesaBollettaInput = v.findViewById(R.id.txtDataSpesaBollettaInput);
        txtDataScadenzaInput = v.findViewById(R.id.txtDataScadenzaInput);

        txtDataSpesaBollettaInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        txtDataScadenzaInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        v.findViewById(R.id.btnInsericiBolletta).setOnClickListener(this);

        return v;
    }



    private void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String formattedDate = String.format("%d/%d/%d", dayOfMonth, month, year);

        switch(view.getId()){
            case R.id.txtDataSpesaBollettaInput:
                txtDataSpesaBollettaInput.setText(formattedDate);
                break;

            case R.id.txtDataScadenzaInput:
                txtDataScadenzaInput.setText(formattedDate);
                break;
        }
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
        String dataScadenzaInputString = this.txtDataScadenzaInput.getText().toString();
        Integer importoBollettaInput;
        try {
            importoBollettaInput = Integer.parseInt(txtImportoBollettaInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoBollettaInput = 0;
        }

        // TODO: Inserire spesa bollette
    }
}

