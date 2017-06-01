package juliethosorio.vitalapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ljoso on 22/05/2017.
 */

public class RecyclerListaGrupos extends
        RecyclerView.Adapter<RecyclerListaGrupos.ViewHolder>{


    private String[] listaGrupo;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreGrupo;
        CardView cardViewGrupo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNombreGrupo = (TextView) itemView.findViewById(R.id.txtNombreGrupo);
            cardViewGrupo=(CardView)itemView.findViewById( R.id.cardviewGrupos );
        }
    }

    public RecyclerListaGrupos(String[] listaDependiente) {

        this.listaGrupo = listaDependiente;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_grupos, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);


        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtNombreGrupo.setText(listaGrupo[position]);
    }

    @Override
    public int getItemCount() {

        return listaGrupo.length;
    }


}
