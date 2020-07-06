package it.univpm.mobile_programming_project.fragment.spese.proprietario;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import it.univpm.mobile_programming_project.utils.CloseKeyboard;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InserisciAffittoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserisciAffittoFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText txtDataAffittoInput;
    private TextInputEditText txtDataScadenzaInput;
    private TextInputEditText txtImportoAffittoInput;
    private TextInputEditText txtTitoloAffittoInput;

    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private UtenteSharedPreferences utenteSharedPreferences;

    public InserisciAffittoFragment() {
    }

    public static InserisciAffittoFragment newInstance() {
        InserisciAffittoFragment fragment = new InserisciAffittoFragment();
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_inserisci_affitto, container, false);

        txtImportoAffittoInput = view.findViewById(R.id.txtImportoAffittoInput);
        txtDataAffittoInput = view.findViewById(R.id.txtDataAffittoInput);
        txtDataScadenzaInput = view.findViewById(R.id.txtDataScadenzaBollettaInput);
        txtTitoloAffittoInput = view.findViewById(R.id.txtTitoloAffittoInput);

        txtDataAffittoInput.setOnClickListener(this);
        txtDataScadenzaInput.setOnClickListener(this);
        view.findViewById(R.id.btnInsericiAffitto).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void showDatePickerDialogScadenza(View view) {
        DialogFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtDataScadenzaInput.setText(String.format(Helper.getDateFormat(), dayOfMonth, month, year));
            }
        });
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    private void showDatePickerDialogInserimento(View view) {
        DialogFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtDataAffittoInput.setText(String.format(Helper.getDateFormat(), dayOfMonth, month, year));
            }
        });
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }



    @Override
    public void onClick(View v) {
        new CloseKeyboard(getActivity());

        switch (v.getId())
        {
            case R.id.txtDataAffittoInput:
                showDatePickerDialogInserimento(v);
                break;

            case R.id.txtDataScadenzaBollettaInput:
                showDatePickerDialogScadenza(v);
                break;

            case R.id.btnInsericiAffitto:
                inserisciSpesaAffitto();
                break;

        }
    }

    private void inserisciSpesaAffitto() {

        String dataAffitto = this.txtDataAffittoInput.getText().toString();
        String dataScadenzaAffitto = this.txtDataScadenzaInput.getText().toString();
        String titoloAffitto = this.txtTitoloAffittoInput.getText().toString();
        Double importoAffitto;


        try {
            importoAffitto = Double.parseDouble(txtImportoAffittoInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoAffitto = 0.0;
        }

        if (dataAffitto.isEmpty() || dataScadenzaAffitto.isEmpty() || titoloAffitto.isEmpty() || importoAffitto.isNaN()) {
            Toast.makeText(InserisciAffittoFragment.this.getContext(), "Inserisci tutti i campi", Toast.LENGTH_LONG).show();
            return;
        }

        ((HomeActivity) InserisciAffittoFragment.this.getActivity()).startLoading();
        this.firebaseFunctionsHelper
                .inserisciSpesaAffitto(importoAffitto, titoloAffitto, dataAffitto, dataScadenzaAffitto)
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
                            ((HomeActivity) InserisciAffittoFragment.this.getActivity()).stopLoading();
                            return;
                        }

                        Boolean isSpesaInseritaCorrettamente = task.getResult();
                        if(isSpesaInseritaCorrettamente) {
                            // Spesa inserita correttamente

                            txtDataAffittoInput.setText("");
                            txtDataScadenzaInput.setText("");
                            txtTitoloAffittoInput.setText("");
                            txtImportoAffittoInput.setText("");
                            Toast.makeText(InserisciAffittoFragment.this.getContext(), "Spesa inserita con successo.", Toast.LENGTH_LONG).show();
                        } else {
                            // Spesa non inserita
                            Toast.makeText(InserisciAffittoFragment.this.getContext(), "Spesa non inserita.", Toast.LENGTH_LONG).show();
                        }
                        ((HomeActivity)InserisciAffittoFragment.this.getActivity()).stopLoading();
                    }

                });

    }


}
