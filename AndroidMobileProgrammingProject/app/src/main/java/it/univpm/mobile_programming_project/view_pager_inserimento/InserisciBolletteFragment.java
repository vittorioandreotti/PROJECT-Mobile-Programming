package it.univpm.mobile_programming_project.view_pager_inserimento;

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
public class InserisciBolletteFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText textInputEditText;
    private TextInputEditText textInputEditText2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InserisciBolletteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BolletteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InserisciBolletteFragment newInstance(String param1, String param2) {
        InserisciBolletteFragment fragment = new InserisciBolletteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inserisci_bolletta, container, false);

        textInputEditText = v.findViewById(R.id.txtDataBolletta);
        textInputEditText2 = v.findViewById(R.id.txtDataScadenzaBolletta);
        textInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        textInputEditText2.setOnClickListener(new View.OnClickListener() {
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
        textInputEditText2.setText(String.format("%d/%d/%d", dayOfMonth, month, year));
    }
}

