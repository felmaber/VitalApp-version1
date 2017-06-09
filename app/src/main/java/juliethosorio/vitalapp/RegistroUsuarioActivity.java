package juliethosorio.vitalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
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
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import static juliethosorio.vitalapp.R.id.campotipoSangre;

/**
 * Created by ljoso on 18/05/2017.
 */

public class RegistroUsuarioActivity  extends AppCompatActivity  {

    private int año,mes,dia;
    private EditText campofecha, campoId,campoNombre,campoCorreo,campoTelefono;
    private EditText campoDireccion,campoNombreContacto,campoTelContacto,campoUsuario,campoContraseña,campoContraseña2;
    private EditText campoCondicion,campoEnfermedad,campoMedicamentos;
    private Button btnFecha,btnAgregarCondicion,btnGuardar,btnsalir;
    private  static final int dialogo=0;
    private static DatePickerDialog.OnDateSetListener selectorFecha;
    private CheckBox opcional;
    private GridLayout condicionMedica;
    private Spinner listaTipoSangre, listaEPS;
    //private RecyclerView recyclerView;



   // ArrayList<ListaCondicion> ArrayCondicion;
    private int campotipoSangre;

    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro_usuario);
            campoId= (EditText) findViewById(R.id.campoId);
            campoNombre= (EditText) findViewById(R.id.campoNombres);
            campoCorreo=(EditText)findViewById(R.id.campoCorreo);
            campoDireccion=(EditText)findViewById(R.id.campoDireccion) ;
            campoTelefono=(EditText)findViewById(R.id.campotelefono);
            campoNombreContacto=(EditText)findViewById(R.id.campoContacto);
            campoTelContacto=(EditText)findViewById(R.id.campotelContacto);
            campoUsuario=(EditText)findViewById(R.id.txtUsuario);
            campoContraseña=(EditText)findViewById(R.id.txtContraseña);
            campoContraseña2=(EditText)findViewById(R.id.txtConfirmarContraseña);

            campoCondicion=(EditText)findViewById(R.id.campoCondicion);
            campoEnfermedad=(EditText)findViewById(R.id.campoEnfermedad);
            campoMedicamentos=(EditText)findViewById(R.id.campoMedicamentos);

            btnGuardar=(Button) findViewById(R.id.btnRegistrar);
            btnAgregarCondicion=(Button)findViewById(R.id.btnAgregar);
            btnsalir=(Button)findViewById(R.id.btnSalir);


            listaTipoSangre=(Spinner)findViewById(R.id.campotipoSangre);
            listaEPS=(Spinner)findViewById(R.id.cbEps);

            campofecha=(EditText)findViewById(R.id.campoFechaNacimiento);
            btnFecha=(Button)findViewById(R.id.btnfecha);
            Calendar calendario=Calendar.getInstance();
            año=calendario.get(Calendar.YEAR);
            mes=calendario.get(Calendar.MONTH)+1;
            dia=calendario.get(Calendar.DAY_OF_MONTH);
            mostrarFecha();

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


            //accion boton guardar registro
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (campoContraseña.getText().toString().equals(campoContraseña2.getText().toString()))
                    {
                        new insertarDatos().execute("http://10.0.3.2/vitalapp/ingresarUsuario.php?identificacion="+campoId.getText().toString()
                                        +"&nombre="+campoNombre.getText().toString()+"&fecha="+campofecha.getText().toString()
                                        +"&tipo="+listaTipoSangre.getSelectedItem().toString()+"&eps="+listaEPS.getSelectedItem().toString()
                                        +"&correo="+campoCorreo.getText().toString()+"&telefono="+campoTelefono.getText().toString()
                                        +"&direccion="+campoDireccion.getText().toString()+"&contacto="+campoNombreContacto.getText().toString()
                                        +"&tel_contacto="+campoTelContacto.getText().toString()+"&user="+campoUsuario.getText().toString()
                                        +"&pass="+campoContraseña.getText().toString()+"&condicion="+campoCondicion.getText().toString()
                                        +"&enfermedad="+campoEnfermedad.getText().toString()+"&medicamentos="+campoMedicamentos.getText().toString());
                    }
                    else{

                        Toast.makeText(getApplicationContext(),"Las contraseñas no conincide vuelva a intentarlo", Toast.LENGTH_LONG).show();
                    }
                }
            });

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

    //metodo para el menu overflow del action bar
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


    //metodo para insertar datos usuario
    private class insertarDatos extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            try{
                return downloadUrl(urls[0]);
            }catch (IOException e){
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        protected void onPostExecute(String resultado){
            Toast.makeText(getApplicationContext(),"El usuario se ha guardado correctamente",Toast.LENGTH_LONG).show();

            Intent irMenu = new Intent(RegistroUsuarioActivity.this,LoginActivity.class);

            startActivity(irMenu);
        }
    }

// metodo para llamar la url de conexion

    private String downloadUrl(String miurl) throws IOException{
        InputStream inputStream=null;

        int len=500;

        try {
            URL url=new URL(miurl);
            HttpURLConnection conexion=(HttpURLConnection)url.openConnection();
            conexion.setReadTimeout(10000);
            conexion.setConnectTimeout(15000);
            conexion.setRequestMethod("GET");
            conexion.setDoInput(true);

            conexion.connect();

            int respuesta= conexion.getResponseCode();
            Log.d("respuesta","La respuesta es: "+respuesta);
            
            inputStream=conexion.getInputStream();

            String contentAsString= readIt(inputStream, len);
            return contentAsString;
        }finally {
            if (inputStream!=null){
                inputStream.close();
            }
        }
    }

    //lee el inputstream y lo convierte en un string

    private String readIt(InputStream inputStream, int len)
            throws IOException, UnsupportedEncodingException{
        Reader leer=null;
        leer=new InputStreamReader(inputStream, "UTF-8");
        char[] buffer=new char[len];
        leer.read(buffer);
        return new String(buffer);
    }


}
