package juliethosorio.vitalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import java.util.Calendar;

public class RegistroDependientesActivity extends AppCompatActivity {
    JSONArray jsonArray;
    private int año,mes,dia;
    private EditText campofechaD,campoCondicionD,campoEnfermedadD,campoMedicamentosD;
    private Button btnFechaD,RegistrarD;
    private  static final int dialogo=0;
    private static DatePickerDialog.OnDateSetListener selectorFecha;
    //private CheckBox opcional;
    //private GridLayout condicionMedica;
    private Spinner listaTipoSangreD, listaEPSD;
    private String idUND;
    private String rolUND;
    private String NomGrupo;
    private String idGrupo;
    private String rolGrupoND;
    private String rolGrupoD;
    private String nombreUND, telUND,correUND;

    private EditText campoIdD,campoNombreD,correoD,direccionD,telefonoD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_dependientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campoIdD=(EditText)findViewById(R.id.campoIdD);
        campoNombreD=(EditText)findViewById(R.id.campoNombresD);
        correoD=(EditText)findViewById(R.id.campoCorreoD);
        direccionD=(EditText)findViewById(R.id.campodireccionD);
        telefonoD=(EditText)findViewById(R.id.campotelefonoD);

        campoCondicionD=(EditText)findViewById(R.id.campoCondicionD);
        campoEnfermedadD=(EditText)findViewById(R.id.campoEnfermedadD);
        campoMedicamentosD=(EditText)findViewById(R.id.campoMedicamentosD);

        UsuarioVO usuarioVODep= (UsuarioVO)getIntent().getSerializableExtra("usuario");
        idUND=(usuarioVODep.getIdentificacion());
        nombreUND=(usuarioVODep.getNombre());
        telUND=(usuarioVODep.getTelefono());
        correUND=(usuarioVODep.getCorreo());

        rolUND="DEPENDIENTE";
        NomGrupo="GRUPO FAMILIA";
        idGrupo= idUND;
        rolGrupoD="MIEMBRO";
        rolGrupoND="ADMIN";



        RegistrarD=(Button)findViewById(R.id.btnRegistrar);

        listaTipoSangreD=(Spinner)findViewById(R.id.campotipoSangreD);
        listaEPSD=(Spinner)findViewById(R.id.cbEpsD);

        campofechaD=(EditText)findViewById(R.id.campoFechaNacimientoD);
        btnFechaD=(Button)findViewById(R.id.btnfechaD);
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

        //opcional=(CheckBox)findViewById(R.id.Condicion);
        //condicionMedica=(GridLayout)findViewById(R.id.GridcondicionMedica);

        //activar el btnAtras en el actionbar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // lista tipo de sangre
        ArrayAdapter<CharSequence> adaptadorTipoSangre;

        adaptadorTipoSangre=ArrayAdapter.createFromResource(this,R.array.tipoSangre,android.R.layout.simple_spinner_item);
        listaTipoSangreD.setAdapter(adaptadorTipoSangre);

        //lista Eps
        ArrayAdapter<CharSequence> adaptadorEPS;

        adaptadorEPS=ArrayAdapter.createFromResource(this,R.array.eps,android.R.layout.simple_spinner_item);
        listaEPSD.setAdapter(adaptadorEPS);

        //accion boton guardar registro
        RegistrarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    insertarDatosPedentiente("http://192.168.50.103/vitalapp/insertarDependiente.php?identificacion="+campoIdD.getText().toString()
                                    +"&nombre="+campoNombreD.getText().toString()+"&fecha="+campofechaD.getText().toString()
                                    +"&tipo="+listaTipoSangreD.getSelectedItem().toString()+"&eps="+listaEPSD.getSelectedItem().toString()
                                    +"&correo="+correoD.getText().toString()+"&direccion="+direccionD.getText().toString()
                                    +"&telefono="+telefonoD.getText().toString()
                                    +"&tipo="+listaTipoSangreD.getSelectedItem().toString()
                                    +"&eps="+listaEPSD.getSelectedItem().toString()
                                    +"&contacto="+nombreUND.toString()
                                    +"&tel_contacto="+telUND.toString()
                                    +"&condicion="+campoCondicionD.getText().toString()
                                    +"&enfermedad="+campoEnfermedadD.getText().toString()
                                    +"&medicamentos="+campoMedicamentosD.getText().toString()
                                    +"&rolUsuario="+rolUND.toString()
                                    +"&idUND="+idUND.toString()
                                    +"&NomGrupo="+NomGrupo.toString()
                                    +"&idGrupo="+idGrupo.toString()
                                    +"&rolGrupoD="+rolGrupoD.toString()
                                    +"&rolGrupoND="+rolGrupoND.toString()
                            );
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
        campofechaD.setText(año+"/"+(mes+1)+"/"+dia);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent login=new Intent( this, ListaDependientesActivity.class );
                startActivity( login );
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

    /*public void mostrarFormMedico(View view){
        if (opcional.isChecked()){
            condicionMedica.setVisibility(View.VISIBLE);
        }
        else{
            condicionMedica.setVisibility(View.GONE);
        }
    }*/

    //metodo para insertar datos dependiente
    private void insertarDatosPedentiente(String URL) {

        Log.i("url",""+URL);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL,  new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                try {
                    jsonArray = new JSONArray(response);
                    Toast.makeText(getApplicationContext(),"El usuario ya existe en la base de datos",Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"El dependiente se ha guardado correctamente",Toast.LENGTH_LONG).show();
                    Intent irMenu = new Intent(RegistroDependientesActivity.this,ListaDependientesActivity.class);
                    startActivity(irMenu);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }});
        queue.add(stringRequest);
    }

// metodo para llamar la url de conexion

   /* private String downloadUrl(String miurl) throws IOException{
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

    //lee el inputstream y lo convierte en un string

    /*private String readIt(InputStream inputStream, int len)
            throws IOException, UnsupportedEncodingException {
        Reader leer;
        leer=new InputStreamReader(inputStream, "UTF-8");
        char[] buffer=new char[len];
        leer.read(buffer);
        return new String(buffer);
    }*/


}
