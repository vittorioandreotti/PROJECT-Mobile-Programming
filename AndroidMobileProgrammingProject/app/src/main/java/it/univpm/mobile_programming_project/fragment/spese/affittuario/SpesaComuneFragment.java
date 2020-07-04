package it.univpm.mobile_programming_project.fragment.spese.affittuario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.SpeseCondominioFragment;
import it.univpm.mobile_programming_project.models.Spesa;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpeseCondominioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpesaComuneFragment extends Fragment {

    private List<Spesa> speseSpesaComune;

    public SpesaComuneFragment(List<Spesa> speseSpesaComune) {
        this.speseSpesaComune = speseSpesaComune;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spesa_comune, container, false);
    }
}
