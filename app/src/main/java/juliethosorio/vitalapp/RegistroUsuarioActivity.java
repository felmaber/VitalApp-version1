package juliethosorio.vitalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ljoso on 18/05/2017.
 */

public class RegistroUsuarioActivity  extends AppCompatActivity  {

    private int año,mes,dia;
    private EditText campofecha;
    private Button btnFecha;
    private  static final int dialogo=0;
    private static DatePickerDialog.OnDateSetListener selectorFecha;
    private CheckBox opcional;
    private GridLayout condicionMedica;
    private Spinner listaTipoSangre, listaEPS;
    private RecyclerView recyclerView;


    ArrayList<ListaCondicion> ArrayCondicion;

        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro_usuario);

            listaTipoSangre=(Spinner)findViewById(R.id.campotipoSangre);
            listaEPS=(Spinner)findViewById(R.id.cbEps);

            campofecha=(EditText)findViewById(R.id.campoFechaNacimiento);
            btnFecha=(Button)findViewById(R.id.btnfecha);
            Calendar calendario=Calendar.getInstance();
            año=calendario.get(Calendar.YEAR);
            mes=calendario.get(Calendar.MONTH)+1;
            dia=calendario.get(Calendar.DAY_OF_MONTH);
            mostrarFecha();


            recyclerView= (RecyclerView) findViewById(R.id.RListaCondiciones);
            recyclerView.setLayoutManager(new LinearLayoutManager((getApplicationContext())));
            recyclerView.setHasFixedSize(true);

            llenarListaCondicion();

            RecyclerListaCondicion adaptador=new RecyclerListaCondicion(ArrayCondicion);
            recyclerView.setAdapter(adaptador);

            //fecha seleccionada
            selectorFecha=new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    año= year;
                    mes=month;
                    dia=dayOfMonth;
                    mostrarFecha();
                }
            };

            opcional=(CheckBox)findViewById(R.id.Condicion);
            condicionMedica=(GridLayout)findViewById(R.id.GridcondicionMedica);

            //activar el btnAtras en el actionbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // lista tipo de sangre
            ArrayAdapter<CharSequence> adaptadorTipoSangre;

            adaptadorTipoSangre=ArrayAdapter.createFromResource(this,R.array.tipoSangre,android.R.layout.simple_spinner_item);
            listaTipoSangre.setAdapter(adaptadorTipoSangre);

            //lista Eps
            ArrayAdapter<CharSequence> adaptadorEPS;

            adaptadorEPS=ArrayAdapter.createFromResource(this,R.array.eps,android.R.layout.simple_spinner_item);
            listaEPS.setAdapter(adaptadorEPS);

         }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this,selectorFecha, año, mes,dia);
        }
        return null;
    }

    public void mostrarCalendario(View view){
        showDialog(dialogo);

    }

    public void mostrarFecha(){
        campofecha.setText(año+"/"+(mes+1)+"/"+dia);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.salir:
                finish();
                break;
            case R.id.ayuda:
                break;
            case R.id.acercaDe:
                break;
        }
        return true;
    }

//metodo para habilitar las condiciones medicas

    public void mostrarFormMedico(View view){
        if (opcional.isChecked()){
            condicionMedica.setVisibility(View.VISIBLE);
        }
        else{
            condicionMedica.setVisibility(View.GONE);
        }
    }

    public void onClick(View view){
        Intent irMenu = new Intent(this, Menu_Activity.class);
        startActivity(irMenu);
    }

    public void mostrarLista(View view){
        switch (view .getId()) {
            case R.id.btnAgregar:
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSalir:
                recyclerView.setVisibility(View.GONE);
        }
    }


    private void llenarListaCondicion(){
        ArrayCondicion=new ArrayList<ListaCondicion>();

        ListaCondicion miListaCondicion=new ListaCondicion();

        ArrayCondicion.add(new ListaCondicion("Limitacion Auditiva", "Neuristis Vesticular", "Antineurina"));
        ArrayCondicion.add(new ListaCondicion("Limitacion Auditiva", "Neuristis Vesticular", "Antineurina"));

    }

}
