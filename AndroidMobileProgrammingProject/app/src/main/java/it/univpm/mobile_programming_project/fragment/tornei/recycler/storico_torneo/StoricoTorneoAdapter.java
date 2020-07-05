package it.univpm.mobile_programming_project.fragment.tornei.recycler.storico_torneo;

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
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;

public class StoricoTorneoAdapter extends RecyclerView.Adapter < StoricoTorneoAdapter.TorneoViewHolder > {

    private List<Torneo> mTorneiList;

    public StoricoTorneoAdapter() {
        this.mTorneiList = new ArrayList<Torneo>();
    }

    public StoricoTorneoAdapter(List <Torneo> mTorneiList) {
        this.mTorneiList = mTorneiList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_storico_tornei, parent, false);
        return new TorneoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TorneoViewHolder holder, int position) {
        Torneo torneo = this.mTorneiList.get(position);
        holder.titoloPlaceholder.setText( torneo.getTitolo() );
        holder.categoriaPlaceholder.setText( torneo.getCategoria() );
        holder.regolamentoPlaceholder.setText( torneo.getRegolamento() );
        holder.dataOraPlaceholder.setText( torneo.getDataOra().toString() );
        holder.indirizzoPlaceholder.setText( torneo.getIndirizzo() );
    }

    @Override
    public int getItemCount() {
        return this.mTorneiList.size();
    }

    class TorneoViewHolder extends RecyclerView.ViewHolder{

        TextView titoloPlaceholder;

        TextView categoriaPlaceholder;
        TextView regolamentoPlaceholder;
        TextView dataOraPlaceholder;
        TextView indirizzoPlaceholder;

        TorneoViewHolder(View itemView) {
            super(itemView);

            titoloPlaceholder = itemView.findViewById(R.id.txtPlaceholderTitolo);
            categoriaPlaceholder = itemView.findViewById(R.id.txtPlaceholderCategoria);
            regolamentoPlaceholder = itemView.findViewById(R.id.txtPlaceholderRegolamento);
            dataOraPlaceholder = itemView.findViewById(R.id.txtPlaceholderDataOra);
            indirizzoPlaceholder = itemView.findViewById(R.id.txtPlaceholderIndirizzo);

        }


    }

}
