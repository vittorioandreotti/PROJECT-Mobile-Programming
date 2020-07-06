package it.univpm.mobile_programming_project.fragment.spese;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.recycler.sommario.SpeseAdapter;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;


public class BolletteFragment extends Fragment implements RecyclerViewClickListener {

    private RecyclerView recyclerViewBollette;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private LinearLayout linearLayout;

    private List<Spesa> speseBollette;

    public BolletteFragment(List<Spesa> speseBollette) {
        this.speseBollette = speseBollette;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bollette, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        recyclerViewBollette = view.findViewById(R.id.recyclerViewBollette);
        recyclerViewBollette.setHasFixedSize(true);
        recyclerViewBollette.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBollette.setLayoutManager(layoutManager);

        adapter = new SpeseAdapter(this.speseBollette, this);
        recyclerViewBollette.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, Object object) {
        Spesa spesa = (Spesa)object;

        // Cliccata la spesa "spesa"
        // TODO: Setta la spesa pagata da cloud function
    }
}