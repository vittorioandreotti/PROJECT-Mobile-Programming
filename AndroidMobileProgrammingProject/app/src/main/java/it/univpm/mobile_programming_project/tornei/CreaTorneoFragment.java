package it.univpm.mobile_programming_project.tornei;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;
import it.univpm.mobile_programming_project.utils.picker.TimePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreaTorneoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaTorneoFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextInputEditText tietDate;
    private TextInputEditText tietTime;

    public CreaTorneoFragment() {
        // Required empty public constructor
    }

    public static CreaTorneoFragment newInstance(String param1, String param2) {
        CreaTorneoFragment fragment = new CreaTorneoFragment();

        return fragment;
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

        tietDate = v.findViewById(R.id.txtDataTorneoInput);
        tietDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        tietTime = v.findViewById(R.id.txtOraEventoInput);
        tietTime.setOnClickListener(new View.OnClickListener() {
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
        tietDate.setText(String.format("%d/%d/%d", dayOfMonth, month, year));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        tietTime.setText(String.format("%d/%d", hourOfDay, minute));
    }
}
