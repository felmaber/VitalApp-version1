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

public class RecyclerListaIncidencias extends
        RecyclerView.Adapter<RecyclerListaIncidencias.IncidenciaViewHolder>{


    private String[] listaincidencias;

    public static class IncidenciaViewHolder extends RecyclerView.ViewHolder {
        TextView NombreUsuarioQR,Fecha,Hora;
        CardView cardViewIncidencias;

        public IncidenciaViewHolder(View itemView) {
            super(itemView);
            NombreUsuarioQR = (TextView) itemView.findViewById(R.id.txtNombreUsuarioQr);
            Fecha=(TextView)itemView.findViewById( R.id.txtFechaIncidencia );
            Hora=(TextView)itemView.findViewById( R.id.txtHoraIncidencia );

            cardViewIncidencias =(CardView)itemView.findViewById( R.id.cardviewIncidencias2 );
        }
    }

    public RecyclerListaIncidencias(String[] listaIncidencia) {

        this.listaincidencias = listaIncidencia;
    }

    @Override
    public IncidenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_incidencias, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);


        return new IncidenciaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(IncidenciaViewHolder holder, int position) {
        holder.NombreUsuarioQR.setText( listaincidencias[position]);
    }

    @Override
    public int getItemCount() {
        return listaincidencias.length;
    }


}
