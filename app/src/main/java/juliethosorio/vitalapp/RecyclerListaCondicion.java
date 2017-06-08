package juliethosorio.vitalapp;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static juliethosorio.vitalapp.R.id.campoCondicionEspecial;

/**
 * Created by ljoso on 22/05/2017.
 */

public class RecyclerListaCondicion extends
        RecyclerView.Adapter<RecyclerListaCondicion.ViewHolder>{

    ArrayList<ListaCondicion> listaInfo;

    public RecyclerListaCondicion(ArrayList<ListaCondicion> listaInfo) {

        this.listaInfo = listaInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarjetas_lista, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);


        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.campoCondicionEspecial.setText(listaInfo.get(position).getCondicion());
        holder.campoEnfermedadCritica.setText(listaInfo.get(position).getEnfermedad());
        holder.campoMedicamentos.setText(listaInfo.get(position).getMedicamentos());
    }

    @Override
    public int getItemCount() {
        return listaInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText campoCondicionEspecial,campoEnfermedadCritica, campoMedicamentos ;

        public ViewHolder(View itemView) {
            super(itemView);
            campoCondicionEspecial = (EditText) itemView.findViewById(R.id.campoCondicionEspecial);
            campoEnfermedadCritica = (EditText) itemView.findViewById(R.id.campoEnfermedadCritica);
            campoMedicamentos = (EditText) itemView.findViewById(R.id.campoMedicamentos);
        }
    }


}