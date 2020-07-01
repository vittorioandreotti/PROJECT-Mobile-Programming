package it.univpm.mobile_programming_project.fragment.spese;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AffittoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AffittoFragment extends Fragment {

    public AffittoFragment() {
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
        return inflater.inflate(R.layout.fragment_affitto, container, false);
    }
}
