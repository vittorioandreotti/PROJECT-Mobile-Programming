package it.univpm.mobile_programming_project.fragment.spese.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.univpm.mobile_programming_project.fragment.spese.recycler.view_holder.SpesaViewHolder;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;

public abstract class InterfaceSpeseAdapter extends RecyclerView.Adapter <SpesaViewHolder>{

    protected final int tipoUtenteAutenticato;
    protected List<Spesa> mSpeseList;
    protected final RecyclerViewClickListener listener;

    public static final int AFFITTUARIO = 0;
    public static final int PROPRIETARIO = 1;

    public InterfaceSpeseAdapter(List<Spesa> listaSpese, RecyclerViewClickListener listener, int tipoUtenteAutenticato) {
        super();
        this.listener = listener;
        this.mSpeseList = listaSpese;
        this.tipoUtenteAutenticato = tipoUtenteAutenticato;
    }

    @Override
    public int getItemCount() {
        return this.mSpeseList.size();
    }

    public Spesa getSpesa(int position) {
        return this.mSpeseList.get(position);
    }
}
