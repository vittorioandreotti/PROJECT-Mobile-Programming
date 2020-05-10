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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InserisciCodiceCasaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InserisciCodiceCasaFragment newInstance(String param1, String param2) {
        InserisciCodiceCasaFragment fragment = new InserisciCodiceCasaFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserisci_codice_casa, container, false);
    }
}
