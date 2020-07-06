package it.univpm.mobile_programming_project.fragment.tornei.recycler.partecipa_torneo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.models.Torneo;
import it.univpm.mobile_programming_project.utils.Helper;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;

public class PartecipaTorneoAdapter extends RecyclerView.Adapter < PartecipaTorneoAdapter.TorneoViewHolder > {

    private final RecyclerViewClickListener listener;
    private List<Torneo> mTorneiList;

    public PartecipaTorneoAdapter(RecyclerViewClickListener listener) {
        this.listener = listener;
        this.mTorneiList = new ArrayList<Torneo>();
    }

    public PartecipaTorneoAdapter(List <Torneo> mTorneiList, RecyclerViewClickListener listener) {
        this.mTorneiList = mTorneiList;
        this.listener = listener;
    }

    public void addTorneo(Torneo torneo) {
        this.mTorneiList.add(torneo);
    }

    public Torneo getTorneo(int position) {
        return this.mTorneiList.get(position);
    }

    @NonNull
    @Override
    public TorneoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_partecipa_tornei, parent, false);
        return new TorneoViewHolder(view, this.listener);
    }

    @Override
    public void onBindViewHolder(TorneoViewHolder holder, int position) {
        Torneo torneo = this.mTorneiList.get(position);
        holder.titoloPlaceholder.setText( torneo.getTitolo() );
        holder.categoriaPlaceholder.setText( torneo.getCategoria() );
        holder.regolamentoPlaceholder.setText( torneo.getRegolamento() );
        holder.dataOraPlaceholder.setText(Helper.formatDateToStringWithHour(torneo.getDataOra()) );
        holder.indirizzoPlaceholder.setText( torneo.getIndirizzo() );
    }

    @Override
    public int getItemCount() {
        return this.mTorneiList.size();
    }

    class TorneoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titoloPlaceholder;
        RecyclerViewClickListener listener;

        TextView categoriaPlaceholder;
        TextView regolamentoPlaceholder;
        TextView dataOraPlaceholder;
        TextView indirizzoPlaceholder;
        Button btnPartecipa;

        TorneoViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;

            titoloPlaceholder = itemView.findViewById(R.id.txtPlaceholderTitolo);
            categoriaPlaceholder = itemView.findViewById(R.id.txtPlaceholderCategoria);
            regolamentoPlaceholder = itemView.findViewById(R.id.txtPlaceholderRegolamento);
            dataOraPlaceholder = itemView.findViewById(R.id.txtPlaceholderDataOra);
            indirizzoPlaceholder = itemView.findViewById(R.id.txtPlaceholderIndirizzo);
            btnPartecipa = itemView.findViewById(R.id.btnPartecipa);

            btnPartecipa.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.listener.onClick(view, getTorneo(getAdapterPosition()));
        }
    }

}
