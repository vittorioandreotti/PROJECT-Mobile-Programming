package it.univpm.mobile_programming_project.fragment.splash_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import it.univpm.mobile_programming_project.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreaCasaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaCasaFragment extends Fragment {

    public CreaCasaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CreaCasaFragment newInstance(String param1, String param2) {
        CreaCasaFragment fragment = new CreaCasaFragment();
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
        return inflater.inflate(R.layout.fragment_crea_casa, container, false);
    }
}
