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
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;

public class SommarioSpeseAdapter extends InterfaceSpeseAdapter {

    private static final int AFFITTO = 0;
    private static final int COMUNE = 1;
    private static final int CONDOMINIO = 2;
    private static final int BOLLETTE = 3;

    public SommarioSpeseAdapter(List<Spesa> listaSpese, RecyclerViewClickListener listener, int tipoUtenteAutenticato) {
        super(listaSpese, listener, tipoUtenteAutenticato);
    }

    @NonNull
    @Override
    public SpesaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch ( viewType ) {
            case SommarioSpeseAdapter.AFFITTO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_spesa_affitto, parent, false);
                break;

            case SommarioSpeseAdapter.COMUNE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_spesa_comune, parent, false);
                break;

            case SommarioSpeseAdapter.CONDOMINIO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_spesa_condominio, parent, false);
                break;

            case SommarioSpeseAdapter.BOLLETTE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_spesa_bollette, parent, false);
                break;

            default: return null;
        }

        return new SpesaViewHolder(view, this.listener, this);

    }

    public int getItemViewType(int position) {
        switch (this.getSpesa(position).getTipo()) {
            case "affitto": return SommarioSpeseAdapter.AFFITTO;
            case "comune": return SommarioSpeseAdapter.COMUNE;
            case "condominio": return SommarioSpeseAdapter.CONDOMINIO;
            case "bolletta": return SommarioSpeseAdapter.BOLLETTE;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(SpesaViewHolder holder, int position) {
        Spesa spesa = this.getSpesa(position);

        switch ( getItemViewType(position) ) {
            case SommarioSpeseAdapter.AFFITTO:
                holder.titoloPlaceholder.setText( spesa.getTitolo() );
                holder.dataInserimentoPlaceholder.setText(Helper.formatDateToString(spesa.getDataInserimento()) );
                holder.dataPagamentoPlaceholder.setText(Helper.formatDateToStringWithHour(spesa.getDataPagamento()) );
                holder.dataScadenzaPlaceholder.setText(Helper.formatDateToString(spesa.getDataScadenza()) );
                holder.prezzoPlaceholder.setText( String.format(Locale.ITALIAN, "%.2f€", spesa.getPrezzo()) );
                break;

            case SommarioSpeseAdapter.COMUNE:
                holder.nomePlaceholder.setText(spesa.getNome());
                holder.descrizionePlaceholder.setText( spesa.getDescrizione() );
                holder.dataInserimentoPlaceholder.setText(Helper.formatDateToString(spesa.getDataInserimento()) );
                holder.dataPagamentoPlaceholder.setText(Helper.formatDateToStringWithHour(spesa.getDataPagamento()) );
                holder.prezzoPlaceholder.setText( String.format(Locale.ITALIAN, "%.2f€", spesa.getPrezzo()) );
                break;

            case SommarioSpeseAdapter.CONDOMINIO:
                holder.nomePlaceholder.setText(spesa.getNome());
                holder.dataInserimentoPlaceholder.setText(Helper.formatDateToString(spesa.getDataInserimento()) );
                holder.dataPagamentoPlaceholder.setText(Helper.formatDateToStringWithHour(spesa.getDataPagamento()) );
                holder.prezzoPlaceholder.setText( String.format(Locale.ITALIAN, "%.2f€", spesa.getPrezzo()) );
                break;

            case SommarioSpeseAdapter.BOLLETTE:
                holder.dataInserimentoPlaceholder.setText(Helper.formatDateToString(spesa.getDataInserimento()) );
                holder.dataPagamentoPlaceholder.setText(Helper.formatDateToStringWithHour(spesa.getDataPagamento()) );
                holder.dataScadenzaPlaceholder.setText(Helper.formatDateToString(spesa.getDataScadenza()) );
                holder.categoriaPlaceholder.setText( spesa.getCategoria());
                holder.prezzoPlaceholder.setText( String.format(Locale.ITALIAN, "%.2f€", spesa.getPrezzo()) );
                break;
        }



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
