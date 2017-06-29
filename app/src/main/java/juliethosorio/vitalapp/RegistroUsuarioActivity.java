package juliethosorio.vitalapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.AdapterView;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.Object;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


/**
 * Created by ljoso on 18/05/2017.
 */

public class RegistroUsuarioActivity  extends AppCompatActivity  {
    JSONArray jsonArray;
    private int año,mes,dia;
    private String id;
    private EditText campofecha, campoId,campoNombre,campoCorreo,campoTelefono;
    private EditText campoDireccion,campoNombreContacto,campoTelContacto,campoUsuario,campoContraseña,campoContraseña2;
    private EditText campoCondicion,campoEnfermedad,campoMedicamentos;
    private Button btnFecha;
    public Button btnAgregarCondicion;
    private Button btnGuardar;
    public Button btnsalir;
    private CheckBox opcional;
    private GridLayout condicionMedica;
    private Spinner listaTipoSangre;
    private Spinner listaEPS;
    //private int campotipoSangre;

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
            id="NO DEPENDIENTE";

            campoCondicion=(EditText)findViewById(R.id.campoCondicion);
            campoEnfermedad=(EditText)findViewById(R.id.campoEnfermedad);
            campoMedicamentos=(EditText)findViewById(R.id.campoMedicamentos);

            btnGuardar=(Button) findViewById(R.id.btnRegistrar);

            listaTipoSangre=(Spinner)findViewById(R.id.campotipoSangre);
            listaEPS=(Spinner)findViewById(R.id.cbEps);

            campofecha=(EditText)findViewById(R.id.campoFechaNacimiento);
            btnFecha=(Button)findViewById(R.id.btnfecha);

            opcional=(CheckBox)findViewById(R.id.Condicion);
            condicionMedica=(GridLayout)findViewById(R.id.GridcondicionMedica);

            //activar el btnAtras en el actionbar
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // lista tipo de sangre
            ArrayAdapter<CharSequence> adaptadorTipoSangre;

            adaptadorTipoSangre=ArrayAdapter.createFromResource(this,R.array.tipoSangre,android.R.layout.simple_spinner_item);
            listaTipoSangre.setAdapter(adaptadorTipoSangre);

            //lista Eps
            ArrayAdapter<CharSequence> adaptadorEPS;

            adaptadorEPS=ArrayAdapter.createFromResource(this,R.array.eps,android.R.layout.simple_spinner_item);
            listaEPS.setAdapter(adaptadorEPS);


            //Acción de DatePickerDialog para las fechas
            btnFecha.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (v == btnFecha) {

                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        año = c.get(Calendar.YEAR);
                        mes = c.get(Calendar.MONTH);
                        dia = c.get(Calendar.DAY_OF_MONTH);
                        campofecha.setText(dia + "/" + (mes + 1) + "/" + año);
                        System.out.print("ingresa metodo para cargar fecha: "+campofecha.getText().toString());
                        DatePickerDialog datePickerDialog = new DatePickerDialog(RegistroUsuarioActivity.this, new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                campofecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, año, mes, dia);
                        datePickerDialog.show();
                    }

                }
            });
            //accion boton guardar registro
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (campoContraseña.getText().toString().equals(campoContraseña2.getText().toString()))
                    {
                        System.out.println("inserta datos:");
                        insertarDatos("http://192.168.50.103/vitalapp/ingresarUsuario.php?identificacion="+campoId.getText().toString()
                            +"&nombre="+campoNombre.getText().toString()+"&fecha="+campofecha.getText().toString()
                            +"&tipo="+listaTipoSangre.getSelectedItem().toString()+"&eps="+listaEPS.getSelectedItem().toString()
                            +"&correo="+campoCorreo.getText().toString()+"&telefono="+campoTelefono.getText().toString()
                            +"&direccion="+campoDireccion.getText().toString()+"&contacto="+campoNombreContacto.getText().toString()
                            +"&tel_contacto="+campoTelContacto.getText().toString()+"&user="+campoUsuario.getText().toString()
                            +"&pass="+campoContraseña.getText().toString()+"&condicion="+campoCondicion.getText().toString()
                            +"&enfermedad="+campoEnfermedad.getText().toString()+"&medicamentos="+campoMedicamentos.getText().toString()
                            +"&rolUsuario="+id);

                    }
                    else{

                        Toast.makeText(getApplicationContext(),"Las contraseñas no conincide vuelva a intentarlo", Toast.LENGTH_LONG).show();
                    }
                }
            });

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


    // metodo para llamar la url de conexion

    /*private String downloadUrl(String miurl) throws IOException{
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
    }*/


    //metodo para insertar datos usuario
    /*private class insertarDatos extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            try{
                System.out.println("url ok:"+urls[0]);
                return downloadUrl(urls[0]);


            }catch (IOException e){
                System.out.println("url invalida:");
                return "Unable to retrieve web page. URL may be invalid.";

            }
        }
        /*protected void onPostExecute(String resultado){
            Toast.makeText(getApplicationContext(),"El usuario se ha guardado correctamente",Toast.LENGTH_LONG).show();

            Intent irMenu = new Intent(RegistroUsuarioActivity.this,LoginActivity.class);
            System.out.println("guardo:");

            startActivity(irMenu);
        }
    } */



    //lee el inputstream y lo convierte en un string

    /*private String readIt(InputStream inputStream, int len)
            throws IOException, UnsupportedEncodingException{
        Reader leer=null;
        leer=new InputStreamReader(inputStream, "UTF-8");
        char[] buffer=new char[len];
        leer.read(buffer);
        return new String(buffer);
    }*/



    private void insertarDatos(String URL) {

            Log.i("url",""+URL);

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL,  new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    System.out.println("entra a consulta");
                    try {
                        jsonArray = new JSONArray(response);
                        Toast.makeText(getApplicationContext(),"El usuario no existe en la base de datos",Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"el registro se ha creado en la Base de Datos",Toast.LENGTH_SHORT).show();
                        Intent irMenu = new Intent(RegistroUsuarioActivity.this,LoginActivity.class);
                        startActivity(irMenu);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }});
            queue.add(stringRequest);
        }



}


