package it.univpm.mobile_programming_project.tornei;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;
import it.univpm.mobile_programming_project.utils.picker.TimePickerFragment;


public class CreaTorneoFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextInputEditText txtDataTorneoInput;
    private TextInputEditText txtOraEventoInput;
    private TextInputEditText txtTitoloTorneoInput;
    private TextInputEditText txtIndirizzoEventoInput;
    private TextInputEditText txtCategoriaEventoInput;
    private TextInputEditText txtDescrizioneTorneoInput;


    public CreaTorneoFragment() {
        // Required empty public constructor
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
        txtDataTorneoInput.setText(String.format("%d/%d/%d", dayOfMonth, month, year));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        txtOraEventoInput.setText(String.format("%d/%d", hourOfDay, minute));
    }


    @SuppressLint("SimpleDateFormat")
    private void creaTorneo() {
        String titoloTorneo = txtTitoloTorneoInput.getText().toString();
        String dataEventoStringa = txtDataTorneoInput.getText().toString();
        Date dataEvento = Helper.fromStringToDate(dataEventoStringa);
        String oraEventoStringa = txtOraEventoInput.getText().toString();
        Date oraEvento = Helper.fromStringToDate(oraEventoStringa);
        String indirizzoEvento = txtIndirizzoEventoInput.getText().toString();
        String categoriaTorneo = txtCategoriaEventoInput.getText().toString();
        String regolamentoTorneo = txtDescrizioneTorneoInput.getText().toString();


        // TODO: Inserire torneo
    }


}
