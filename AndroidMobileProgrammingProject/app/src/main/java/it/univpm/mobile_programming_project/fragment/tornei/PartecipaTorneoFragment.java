package it.univpm.mobile_programming_project.fragment.tornei;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;


public class PartecipaTorneoFragment extends Fragment {

    private FirebaseFunctionsHelper firebaseFunctionsHelper;

    public PartecipaTorneoFragment() {
        firebaseFunctionsHelper = new FirebaseFunctionsHelper();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_partecipa_a_torneo, container, false);
    }
}
