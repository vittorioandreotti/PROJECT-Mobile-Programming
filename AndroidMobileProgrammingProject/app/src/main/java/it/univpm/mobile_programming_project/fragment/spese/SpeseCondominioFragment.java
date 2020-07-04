package it.univpm.mobile_programming_project.fragment.spese;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.models.Spesa;

public class SpeseCondominioFragment extends Fragment {

    private List<Spesa> speseSpesaCondominio;

    public SpeseCondominioFragment(List<Spesa> speseSpesaCondominio) {
        this.speseSpesaCondominio = speseSpesaCondominio;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spese_condominio, container, false);
    }
}
