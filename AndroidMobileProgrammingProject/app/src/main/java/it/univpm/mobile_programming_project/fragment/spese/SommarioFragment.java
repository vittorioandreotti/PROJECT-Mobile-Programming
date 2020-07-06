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


public class SommarioFragment extends Fragment implements RecyclerViewClickListener {

    private RecyclerView recyclerViewSommarioSpese;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private LinearLayout linearLayout;

    private List<Spesa> speseSommario;

    public SommarioFragment(List<Spesa> speseSommario) {
        this.speseSommario = speseSommario;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sommario, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null)
            initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        recyclerViewSommarioSpese = view.findViewById(R.id.recyclerViewSommarioSpese);
        recyclerViewSommarioSpese.setHasFixedSize(true);
        recyclerViewSommarioSpese.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewSommarioSpese.setLayoutManager(layoutManager);

        adapter = new SpeseAdapter(this.speseSommario, this);
        recyclerViewSommarioSpese.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, Object object) {
        Spesa spesa = (Spesa)object;

        // Cliccata la spesa "spesa"
        // TODO: Setta la spesa pagata da cloud function
    }
}
