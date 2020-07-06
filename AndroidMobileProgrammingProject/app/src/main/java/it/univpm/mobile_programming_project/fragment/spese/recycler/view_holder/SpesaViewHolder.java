package it.univpm.mobile_programming_project.fragment.spese.recycler.view_holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.recycler.InterfaceSpeseAdapter;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;

public class SpesaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final RecyclerViewClickListener listener;
    public final InterfaceSpeseAdapter adapter;
    public TextView nomePlaceholder;
    public TextView descrizionePlaceholder;
    public TextView tipoPlaceholder;
    public TextView dataInserimentoPlaceholder;
    public TextView dataPagamentoPlaceholder;
    public TextView prezzoPlaceholder;
    public TextView titoloPlaceholder;
    public TextView categoriaPlaceholder;
    public TextView dataScadenzaPlaceholder;

    public TextView txtPagata;
    public TextView txtNonPagata;
    public Button btnPaga;

    public SpesaViewHolder(View itemView, RecyclerViewClickListener listener, InterfaceSpeseAdapter adapter) {
        super(itemView);
        this.listener = listener;
        this.adapter = adapter;

        nomePlaceholder = itemView.findViewById(R.id.txtNomeSpesa);
        titoloPlaceholder = itemView.findViewById(R.id.txtTitoloSpesa);
        descrizionePlaceholder = itemView.findViewById(R.id.txtDescrizioneSpesa);
        tipoPlaceholder = itemView.findViewById(R.id.txtTipoSpesa);
        prezzoPlaceholder = itemView.findViewById(R.id.txtPrezzoSpesa);
        dataInserimentoPlaceholder = itemView.findViewById(R.id.txtDataInserimentoSpesa);
        dataPagamentoPlaceholder = itemView.findViewById(R.id.txtDataPagamentoSpesa);
        dataScadenzaPlaceholder = itemView.findViewById(R.id.txtDataScadenzaSpesa);
        categoriaPlaceholder = itemView.findViewById(R.id.txtCategoriaSpesa);

        txtPagata = itemView.findViewById(R.id.txtPagata);
        txtNonPagata = itemView.findViewById(R.id.txtNonPagata);
        btnPaga = itemView.findViewById(R.id.btnPaga);

        if(btnPaga != null)
            btnPaga.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.listener.onClick(view, this);
    }

}
