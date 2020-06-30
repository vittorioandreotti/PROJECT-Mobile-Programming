package it.univpm.mobile_programming_project.view_pager_inserimento;

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

import com.google.android.material.textfield.TextInputEditText;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InserisciAffittoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserisciAffittoFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText textInputDataAffitto;
    private TextInputEditText textInputDataScadenzaAffitto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InserisciAffittoFragment() {
        // Required empty public constructor
    }

    public static InserisciAffittoFragment newInstance(String param1, String param2) {
        InserisciAffittoFragment fragment = new InserisciAffittoFragment();
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
        View view = inflater.inflate(R.layout.fragment_inserisci_affitto, container, false);

        textInputDataAffitto = view.findViewById(R.id.txtDataAffitto);
        textInputDataScadenzaAffitto = view.findViewById(R.id.txtDataScadenzaAffitto);
        textInputDataAffitto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        textInputDataScadenzaAffitto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String formattedDate = String.format("%d/%d/%d", dayOfMonth, month, year);

        switch (view.getId())
        {
            case R.id.txtDataAffitto:
                textInputDataAffitto.setText(formattedDate);
                break;

            case R.id.txtDataScadenzaAffitto:
                textInputDataScadenzaAffitto.setText(formattedDate);
                break;
        }
    }
}
