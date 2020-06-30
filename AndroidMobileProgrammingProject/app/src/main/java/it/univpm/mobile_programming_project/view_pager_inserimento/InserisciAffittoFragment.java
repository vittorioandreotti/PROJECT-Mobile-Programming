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

import java.util.Date;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.picker.DatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InserisciAffittoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserisciAffittoFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private TextInputEditText txtDataAffittoInput;
    private TextInputEditText txtDataScadenzaInput;
    private TextInputEditText txtImportoAffittoInput;
    private TextInputEditText txtNumeroMesiAffittoInput;

    public InserisciAffittoFragment() {
        // Required empty public constructor
    }

    public static InserisciAffittoFragment newInstance() {
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

        txtImportoAffittoInput = view.findViewById(R.id.txtImportoAffittoInput);
        txtDataAffittoInput = view.findViewById(R.id.txtDataAffittoInput);
        txtDataScadenzaInput = view.findViewById(R.id.txtDataScadenzaInput);
        txtNumeroMesiAffittoInput = view.findViewById(R.id.txtNumeroMesiAffittoInput);

        txtDataAffittoInput.setOnClickListener(this);
        txtDataScadenzaInput.setOnClickListener(this);
        view.findViewById(R.id.btnInsericiAffitto).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.show(fm, "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String formattedDate = String.format("%d/%d/%d", dayOfMonth, month, year);

        switch (view.getId())
        {
            case R.id.txtDataAffittoInput:
                txtDataAffittoInput.setText(formattedDate);
                break;

            case R.id.txtDataScadenzaInput:
                txtDataScadenzaInput.setText(formattedDate);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.txtDataAffittoInput:
            case R.id.txtDataScadenzaInput:
                showDatePickerDialog(v);
                break;

            case R.id.btnInsericiAffitto:
                inserisciSpesaAffitto();
                break;

        }
    }

    private void inserisciSpesaAffitto() {

        Date dataAffitto = Helper.fromStringToDate(txtDataAffittoInput.getText().toString());
        Date dataScadenzaAffitto = Helper.fromStringToDate(txtDataScadenzaInput.getText().toString());
        String nomeSpesa = txtImportoAffittoInput.getText().toString();
        Integer numeroMesiAffitto;
        Integer importoAffitto;

        try {
            numeroMesiAffitto = Integer.parseInt(txtNumeroMesiAffittoInput.getText().toString());
        } catch (NumberFormatException exception) {
            numeroMesiAffitto = 0;
        }

        try {
            importoAffitto = Integer.parseInt(txtImportoAffittoInput.getText().toString());
        } catch (NumberFormatException exception) {
            importoAffitto = 0;
        }

        // TODO: Inserisci affitto
    }


}
