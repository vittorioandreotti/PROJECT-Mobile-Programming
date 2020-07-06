package it.univpm.mobile_programming_project.fragment.spese.recycler.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Locale;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.recycler.InterfaceSpeseAdapter;
import it.univpm.mobile_programming_project.fragment.spese.recycler.view_holder.SpesaViewHolder;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;

public class AffittoSpeseAdapter extends InterfaceSpeseAdapter {

    public AffittoSpeseAdapter(List<Spesa> listaSpese, RecyclerViewClickListener listener, int tipoUtenteAutenticato) {
        super(listaSpese, listener, tipoUtenteAutenticato);
    }

    @NonNull
    @Override
    public SpesaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_spesa_affitto, parent, false);
        return new SpesaViewHolder(view, this.listener, this);
    }

    @Override
    public void onBindViewHolder(SpesaViewHolder holder, int position) {
        Spesa spesa = this.getSpesa(position);

        holder.titoloPlaceholder.setText( spesa.getTitolo() );
        holder.dataInserimentoPlaceholder.setText(Helper.formatDateToString(spesa.getDataInserimento()) );
        holder.dataPagamentoPlaceholder.setText(Helper.formatDateToStringWithHour(spesa.getDataPagamento()) );
        holder.dataScadenzaPlaceholder.setText(Helper.formatDateToString(spesa.getDataScadenza()) );
        holder.prezzoPlaceholder.setText( String.format(Locale.ITALIAN, "%.2f€", spesa.getPrezzo()) );

        if( spesa.getDataPagamento() == null ) {
            // NON PAGATA
            holder.txtPagata.setVisibility(View.GONE);
            holder.txtNonPagata.setVisibility(View.VISIBLE);
            holder.btnPaga.setVisibility(View.VISIBLE);
        }else{
            //PAGATA
            holder.txtPagata.setVisibility(View.VISIBLE);
            holder.txtNonPagata.setVisibility(View.GONE);
            holder.btnPaga.setVisibility(View.GONE);
        }

        if( this.tipoUtenteAutenticato == SommarioSpeseAdapter.PROPRIETARIO ) {
            holder.fullnameUtentePlaceholder.setText( spesa.getNomeUtente() + "\n" + spesa.getCognomeUtente() );
            holder.btnPaga.setVisibility(View.GONE);
            holder.bloccoNomeAffittuario.setVisibility(View.VISIBLE);
        }
    }


}