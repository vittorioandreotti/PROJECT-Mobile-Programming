package it.univpm.mobile_programming_project.tornei;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.functions.FirebaseFunctionsException;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.SplashScreenActivity;
import it.univpm.mobile_programming_project.fragment.spese.affittuario.InserisciSpesaComuneFragment;
import it.univpm.mobile_programming_project.fragment.splash_screen.InserisciCodiceCasaFragment;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;
import it.univpm.mobile_programming_project.utils.picker.TimePickerFragment;


public class CreaTorneoFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextInputEditText txtDataTorneoInput;
    private TextInputEditText txtOraEventoInput;
    private TextInputEditText txtTitoloTorneoInput;
    private TextInputEditText txtIndirizzoEventoInput;
    private TextInputEditText txtCategoriaEventoInput;
    private TextInputEditText txtDescrizioneTorneoInput;

    private FirebaseFunctionsHelper firebaseFunctionsHelper;

    public CreaTorneoFragment() {
        firebaseFunctionsHelper = new FirebaseFunctionsHelper();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crea_torneo, container, false);

        txtTitoloTorneoInput = v.findViewById(R.id.txtTitoloTorneoInput);
        txtIndirizzoEventoInput = v.findViewById(R.id.txtIndirizzoEventoInput);
        txtCategoriaEventoInput = v.findViewById(R.id.txtCategoriaEventoInput);
        txtDescrizioneTorneoInput = v.findViewById(R.id.txtDescrizioneTorneoInput);

        v.findViewById(R.id.btnCreaTorneo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creaTorneo();
            }
        });

        txtDataTorneoInput = v.findViewById(R.id.txtDataTorneoInput);
        txtDataTorneoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        txtOraEventoInput = v.findViewById(R.id.txtOraEventoInput);
        txtOraEventoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        return v;
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    public void showTimePickerDialog (View view) {
        DialogFragment dialogFragment = new TimePickerFragment(this);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        dialogFragment.show(fragmentManager, "timePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txtDataTorneoInput.setText(String.format(Helper.getDateFormat(), dayOfMonth, month, year));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        txtOraEventoInput.setText(String.format( Helper.getTimeFormat(), hourOfDay, minute));
    }


    @SuppressLint("SimpleDateFormat")
    private void creaTorneo() {
        String titoloTorneo = txtTitoloTorneoInput.getText().toString();
        String dataEventoStringa = txtDataTorneoInput.getText().toString();
        String oraEventoStringa = txtOraEventoInput.getText().toString();

        String indirizzoEvento = txtIndirizzoEventoInput.getText().toString();
        String categoriaTorneo = txtCategoriaEventoInput.getText().toString();
        String regolamentoTorneo = txtDescrizioneTorneoInput.getText().toString();

        if (titoloTorneo.isEmpty() || dataEventoStringa.isEmpty() || oraEventoStringa.isEmpty() || indirizzoEvento.isEmpty() || categoriaTorneo.isEmpty()) {
            Toast.makeText(CreaTorneoFragment.this.getContext(), "Inserisci tutti i campi", Toast.LENGTH_LONG).show();
            return;
        }

        ((HomeActivity) CreaTorneoFragment.this.getActivity()).startLoading();
        firebaseFunctionsHelper
                .inserisciTorneo(titoloTorneo, indirizzoEvento, categoriaTorneo, regolamentoTorneo, dataEventoStringa, oraEventoStringa)
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
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
                            ((HomeActivity) CreaTorneoFragment.this.getActivity()).stopLoading();
                            return;
                        }

                        Boolean isTorneoValid = task.getResult();
                        if (isTorneoValid) {
                            // Torneo valido

                            txtTitoloTorneoInput.setText("");
                            txtDataTorneoInput.setText("");
                            txtOraEventoInput.setText("");
                            txtIndirizzoEventoInput.setText("");
                            txtCategoriaEventoInput.setText("");
                            txtDescrizioneTorneoInput.setText("");

                            Toast.makeText(CreaTorneoFragment.this.getContext(), "Torneo creato con successo.", Toast.LENGTH_LONG).show();
                        } else {
                            // Torneo non valido
                            Toast.makeText(CreaTorneoFragment.this.getContext(), "Torneo non creato.", Toast.LENGTH_LONG).show();
                        }
                        ((HomeActivity)CreaTorneoFragment.this.getActivity()).stopLoading();
                    }
                });

    }


}
