package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import it.univpm.mobile_programming_project.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InserisciCodiceCasaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserisciCodiceCasaFragment extends Fragment {


    public InserisciCodiceCasaFragment() {
        // Required empty public constructor
    }


    public static InserisciCodiceCasaFragment newInstance() {
        return new InserisciCodiceCasaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserisci_codice_casa, container, false);
    }
}
