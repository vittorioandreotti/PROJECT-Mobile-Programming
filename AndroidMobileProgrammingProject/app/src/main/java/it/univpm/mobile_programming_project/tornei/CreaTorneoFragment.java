package it.univpm.mobile_programming_project.tornei;

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
import com.google.android.material.textfield.TextInputLayout;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreaTorneoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaTorneoFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText textInputEditText;

    public CreaTorneoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreaTorneoFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        textInputEditText = v.findViewById(R.id.txtDataTorneo);
        textInputEditText.setOnClickListener(new View.OnClickListener() {
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
        textInputEditText.setText(String.format("%d/%d/%d", dayOfMonth, month, year));
    }
}
