package it.univpm.mobile_programming_project.fragment.spese.proprietario;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;


public class InserisciSpesaCondominioFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private TextInputEditText txtDataSpesaCondominioInput;
    private TextInputEditText txtNomeSpesaCondominioInput;
    private TextInputEditText txtImportoSpesaCondominioInput;

    public InserisciSpesaCondominioFragment() {
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
        View v = inflater.inflate(R.layout.fragment_inserisci_spesa_condominio, container, false);

        txtNomeSpesaCondominioInput = v.findViewById(R.id.txtNomeSpesaCondominioInput);
        txtImportoSpesaCondominioInput = v.findViewById(R.id.txtImportoSpesaCondominioInput);

        txtDataSpesaCondominioInput = v.findViewById(R.id.txtDataSpesaCondominioInput);

        txtDataSpesaCondominioInput.setOnClickListener(this);
        v.findViewById(R.id.btnInserisciSpesaCondominio).setOnClickListener(this);

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
        txtDataSpesaCondominioInput.setText(formattedDate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnInserisciSpesaCondominio:
                inserisciSpesaCondominio();
                break;

            case R.id.txtDataSpesaCondominioInput:
                showDatePickerDialog(v);
                break;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void inserisciSpesaCondominio() {

        String nomeSpesa = txtNomeSpesaCondominioInput.getText().toString();
        String dataSpesaStringa = txtDataSpesaCondominioInput.getText().toString();
        Date dataSpesa = Helper.fromStringToDate(dataSpesaStringa);
        Integer importoSpesa;

        try {
            importoSpesa = Integer.parseInt(txtImportoSpesaCondominioInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoSpesa = 0;
        }

        // TODO: Inserire spesa condominio
    }
}

