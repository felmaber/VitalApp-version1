package juliethosorio.vitalapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfesionalActivity extends AppCompatActivity {
    Button btnGuardarPro;
    EditText registroPro;
    Spinner especialidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesional);

        btnGuardarPro= (Button) findViewById(R.id.btnAgregarProfesional);
        registroPro= (EditText) findViewById(R.id.camporegistroPro);
        especialidad= (Spinner) findViewById(R.id.spinnerEspecialidad);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //activar el btnAtras en el actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // lista tipo de sangre
        ArrayAdapter<CharSequence> adaptadorEspecialidad;

        adaptadorEspecialidad=ArrayAdapter.createFromResource(this,R.array.especialidad,android.R.layout.simple_spinner_item);
        especialidad.setAdapter(adaptadorEspecialidad);


        //accion boton guardar registro
        btnGuardarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new insertarDatosPro().execute("http://10.0.3.2/vitalapp/insertarProfesional.php?registro="+registroPro.getText().toString()
                            +"&especialidad="+especialidad.getSelectedItem());
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
                Intent volverMenu =new Intent(ProfesionalActivity.this,Menu_Activity.class);
                startActivity(volverMenu);
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



    //metodo para insertar datos profesional
    private class insertarDatosPro extends AsyncTask<String, Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            try{
                return downloadUrl(urls[0]);
            }catch (IOException e){
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        protected void onPostExecute(String resultado){
            Toast.makeText(getApplicationContext(),"El profesional de la salud se ha guardado correctamente",Toast.LENGTH_LONG).show();

            Intent irMenu = new Intent(ProfesionalActivity.this,Menu_Activity.class);

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
            throws IOException, UnsupportedEncodingException {
        Reader leer=null;
        leer=new InputStreamReader(inputStream, "UTF-8");
        char[] buffer=new char[len];
        leer.read(buffer);
        return new String(buffer);
    }

}
