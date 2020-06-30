package it.univpm.mobile_programming_project.view_pager_inserimento;

import android.annotation.SuppressLint;
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

import java.util.Date;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;


public class InserisciSpesaComuneFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private TextInputEditText txtNomeSpesaComuneInput;
    private TextInputEditText txtDataSpesaComuneInput;
    private TextInputEditText txtImportoSpesaComuneInput;
    private TextInputEditText txtDescrizioneSpesaComuneInput;



    public InserisciSpesaComuneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inserisci_spesa_comune, container, false);

        txtNomeSpesaComuneInput = v.findViewById(R.id.txtNomeSpesaComuneInput);
        txtImportoSpesaComuneInput = v.findViewById(R.id.txtImportoSpesaComuneInput);
        txtDescrizioneSpesaComuneInput = v.findViewById(R.id.txtDescrizioneSpesaComuneInput);

        v.findViewById(R.id.btnInserisciSpesaComune).setOnClickListener(this);

        txtDataSpesaComuneInput = v.findViewById(R.id.txtDataSpesaComune);
        txtDataSpesaComuneInput.setOnClickListener(new View.OnClickListener() {
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
        txtDataSpesaComuneInput.setText(String.format("%d/%d/%d", dayOfMonth, month, year));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnInserisciSpesaComune:
                inserisciSpesaComune();
                break;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void inserisciSpesaComune() {
        String nomeSpesa = txtNomeSpesaComuneInput.getText().toString();
        String descSpesa = txtDescrizioneSpesaComuneInput.getText().toString();
        String dataSpesaStringa = txtDataSpesaComuneInput.getText().toString();
        Date dataSpesa = Helper.fromStringToDate(dataSpesaStringa);
        Integer importoSpesa;

        try {
            importoSpesa = Integer.parseInt(txtImportoSpesaComuneInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoSpesa = 0;
        }

        // TODO: Inserire spesa comune
    }
}
