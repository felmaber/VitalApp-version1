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

public class RecyclerListaDependientes extends
        RecyclerView.Adapter<RecyclerListaDependientes.DependienteViewHolder>{


    private String[] listaDependientes;

    public static class DependienteViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreDependiente;
        CardView cardViewDependientes;

        public DependienteViewHolder(View itemView) {
            super(itemView);
            txtNombreDependiente = (TextView) itemView.findViewById(R.id.txtNombreDependiente);
            cardViewDependientes=(CardView)itemView.findViewById( R.id.cardviewDependientes );
        }
    }

    public RecyclerListaDependientes(String[] listaDependiente) {

        this.listaDependientes = listaDependiente;
    }


    @Override
    public DependienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_dependientes, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);


        return new DependienteViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(DependienteViewHolder holder, int position) {
        holder.txtNombreDependiente.setText(listaDependientes[position]);
    }

    @Override
    public int getItemCount() {
        return listaDependientes.length;
    }


}
