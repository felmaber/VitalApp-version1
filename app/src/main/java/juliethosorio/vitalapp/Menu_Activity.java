package juliethosorio.vitalapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import com.google.zxing.WriterException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


public class Menu_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        ,MenuFragment.OnFragmentInteractionListener,Historial_IncidenciasFragment.OnFragmentInteractionListener {

    MenuFragment menuFragment;
    Historial_IncidenciasFragment miHistorial;
    JSONArray jsonArray,jsonArrayQR;


    public final static int WHITE = 0xFFFFFFFF;
    public final static int BLACK = 0xFF000000;
    public final static int WIDTH = 400;
    public final static int HEIGHT = 400;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);

        menuFragment= new MenuFragment();
        miHistorial=new Historial_IncidenciasFragment();

        /*---------------------------------------------------------*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "LEER QR VITALAPP", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                read();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorMenuFragments,menuFragment).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
               Intent irLogin= new Intent(this,LoginActivity.class);
                startActivity(irLogin);
                break;
            case R.id.ayuda:
                break;
            case R.id.acercaDe:
                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragmento=null;
        boolean seleccion=false;
        int id = item.getItemId();
        UsuarioVO usuarioVO2= (UsuarioVO)getIntent().getSerializableExtra("usuario");

        if (id == R.id.miPerfil) {
          Intent perfil= new Intent(Menu_Activity.this,PerfilActivity.class);

            Bundle usuarioperfilbundle=new Bundle();
            usuarioperfilbundle.putSerializable("usuario",usuarioVO2);
            perfil.putExtras(usuarioperfilbundle);

            startActivity(perfil);

        } else if (id == R.id.profesionalSalud) {

            Intent profesional=new Intent(Menu_Activity.this,ProfesionalActivity.class);
            startActivity(profesional);

        } else if (id == R.id.historialIncidencias) {
            fragmento= new Historial_IncidenciasFragment();
            seleccion=true;
        }
        if(seleccion){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorMenuFragments,fragmento).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view)
    {
        UsuarioVO usuarioVO2= (UsuarioVO)getIntent().getSerializableExtra("usuario");
        switch (view.getId()){

            case R.id.btnCrearQR:
                //Intent intent= new Intent(this,RegistroUsuarioActivity.class);
                //startActivity(intent);
                write();
                break;

            case R.id.btnDependiente:
                Intent intentdependiente= new Intent(this,ListaDependientesActivity.class);

                Bundle usuarioperfildependientebundle=new Bundle();
                usuarioperfildependientebundle.putSerializable("usuario",usuarioVO2);
                intentdependiente.putExtras(usuarioperfildependientebundle);

                startActivity(intentdependiente);

                break;
            case R.id.btnGrupos:
                Intent intentgrupo= new Intent(this,GruposActivity.class);

                Bundle usuarioperfilgrupobundle=new Bundle();
                usuarioperfilgrupobundle.putSerializable("usuario",usuarioVO2);
                intentgrupo.putExtras(usuarioperfilgrupobundle);

                startActivity(intentgrupo);

                break;
            case R.id.btnCompras:
                Intent intentcompras= new Intent(Menu_Activity.this,TiendaVitalActivity.class);

                Bundle usuarioperfiltiendabundle=new Bundle();
                usuarioperfiltiendabundle.putSerializable("usuario",usuarioVO2);
                intentcompras.putExtras(usuarioperfiltiendabundle);

                startActivity(intentcompras);

                break;
            case R.id.btnMenuQR:
                Intent intentmenuQR=new Intent(this,RegistroUsuarioActivity.class);
                startActivity(intentmenuQR);
                break;
            case R.id.btnMenuGrupos:
                Intent intentmenugrupo=new Intent(this,GruposActivity.class);
                startActivity(intentmenugrupo);
                break;
            case R.id.btnMenuDependiente:
               Intent intentmenudependiente=new Intent(this, ListaDependientesActivity.class);
                startActivity(intentmenudependiente);
                break;
            case R.id.btnMenuCompras:
                Intent intentmenucompras=new Intent(this,TiendaVitalActivity.class);
                startActivity(intentmenucompras);
                break;
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


// metodo para llamar la url de conexion

    /*private String downloadUrl(String miurl) throws IOException {
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

    public void read() {
        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.addExtra("SCAN_WIDTH", 800);
        integrator.addExtra("SCAN_HEIGHT", 800);
        integrator.addExtra("PROMPT_MESSAGE", "Busque un código para escanear");

        //integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String[] contenidoQR;
        String ExpReg = "[:;]";
        if (scanResult != null) {

            System.out.println("Información encontrada");
            contenidoQR = scanResult.getContents().split(ExpReg);
            if("MECARD".equals(contenidoQR[0].trim())){

                String idUL=contenidoQR[6];
                System.out.println("usuario leido: "+idUL);

                UsuarioVO usuarioVO= (UsuarioVO)getIntent().getSerializableExtra("usuario");
                String idUR=(usuarioVO.getIdentificacion());
                System.out.println("usuario reporta: "+idUR);

                ConsultaUL("http://192.168.50.103/vitalapp/incidente.php?user="+idUL+"&userReport="+idUR);

                showDialog(scanResult.getContents());
            }else{
                Toast.makeText(getApplicationContext(),"El codigo no es valido para VitalAPP",Toast.LENGTH_LONG).show();
            }

        }

    }

    public void write() {
        // CONTACT
        Intent intent = new Intent();
        intent.setAction("com.google.zxing.client.android.ENCODE");
        String idUsuarioQR = "";

        UsuarioVO usuarioVO2= (UsuarioVO)getIntent().getSerializableExtra("usuario");

        idUsuarioQR = usuarioVO2.getIdentificacion();

        Bundle bundle = new Bundle();
        bundle.putString(ContactsContract.Intents.Insert.NAME, usuarioVO2.getNombre());
        bundle.putString(ContactsContract.Intents.Insert.COMPANY, "ID_VitalAPP");
        bundle.putString(ContactsContract.Intents.Insert.POSTAL, usuarioVO2.getIdentificacion());
        bundle.putString(ContactsContract.Intents.Insert.EMAIL,usuarioVO2.getContacto());
        bundle.putString(ContactsContract.Intents.Insert.PHONE, usuarioVO2.getTelContacto());

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.addExtra("ENCODE_DATA", bundle);
        integrator.shareText(bundle.toString(), "CONTACT_TYPE");
        //-----------
        intent.putExtra("ENCODE_TYPE", "CONTACT_TYPE");
        intent.putExtra("ENCODE_DATA", bundle.toString());
        try {
            Bitmap bitmap = encodeAsBitmap(bundle.toString());
            System.out.println("Este es el archivo bitmap "+bitmap+" Y este el idUsusarioQR "+idUsuarioQR);
            saveImage(bitmap);

            insertarCodigoQR("http://192.168.50.103/vitalapp/ingresarQR.php?identificacion="+idUsuarioQR+"&imagenQR="+bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

    private Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public String saveImage(Bitmap myBitmap) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        myBitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);

        String IMAGE_DIRECTORY = "/QRVitalApp";

        File wallpaperDirectory = new File( Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            Log.d("Error en directorio", "" + wallpaperDirectory.mkdirs());
            wallpaperDirectory.mkdir();
        } try {
            File f = new File(wallpaperDirectory, Calendar.getInstance() .getTimeInMillis() + ".png");
            f.createNewFile();
            //give read write permission
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this, new String[]{f.getPath()}, new String[]{"image/png"}, null);
            fo.close();
            Log.d("TAG", "Archivo Almacenado::--->" + f.getAbsolutePath());
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.scanning_content);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.show();
    }

    //metodo para consultar datos usuario

    private void insertarCodigoQR(String URLQR) {

            Log.i("url",""+URLQR);

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest =  new StringRequest(Request.Method.GET, URLQR,  new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {

                    try {
                        jsonArrayQR = new JSONArray(response);
                        Toast.makeText(getApplicationContext(),"El QR ya existe en la base de datos",Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"El QR se ha guardado correctamente",Toast.LENGTH_LONG).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }});
            queue.add(stringRequest);
        }
       // protected void onPostExecute(String resultado){
         //   JSONArray jsonArray=null;
           /* TextView id,nom,fecha,tipo,eps,correo,tel,dir,contact,telcon;

            id=(TextView)findViewById(R.id.campoIdPerfil);
            nom=(TextView)findViewById(R.id.campoNombresPerfil);
            fecha=(TextView)findViewById(R.id.campoFechaNacimientoPerfil);
            tipo= (TextView) findViewById(R.id.campotipoSangrePerfil);
            eps= (TextView) findViewById(R.id.campoEPSperfil);
            correo= (TextView) findViewById(R.id.campoCorreoPerfil);
            tel= (TextView) findViewById(R.id.campotelefonoPerfil);
            dir= (TextView) findViewById(R.id.campoDireccionPerfil);
            contact= (TextView) findViewById(R.id.campoContactoPerfil);
            telcon= (TextView) findViewById(R.id.campotelContactoPerfil);*/

           // try {
             //   jsonArray=new JSONArray(resultado);
               /* id.setText(jsonArray.getString(0));
                nom.setText(jsonArray.getString(1));
                fecha.setText(jsonArray.getString(2));
                tipo.setText(jsonArray.getString(3));
                eps.setText(jsonArray.getString(4));
                correo.setText(jsonArray.getString(5));
                tel.setText(jsonArray.getString(6));
                dir.setText(jsonArray.getString(7));
                contact.setText(jsonArray.getString(8));
                telcon.setText(jsonArray.getString(9));*/

            //}catch (JSONException e){
              //  e.printStackTrace();
     //       }
       // }
    //}

    //lee el inputstream y lo convierte en un string

    /*private String readIt(InputStream inputStream, int len)
            throws IOException, UnsupportedEncodingException {
        Reader leer=null;
        leer=new InputStreamReader(inputStream, "UTF-8");
        char[] buffer=new char[len];
        leer.read(buffer);
        return new String(buffer);
    }*/


    private void ConsultaUL(String URL) {

        Log.i("url",""+URL);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL,  new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                System.out.println("entra a consulta");
                try {
                    jsonArray = new JSONArray(response);
                    String idUI = jsonArray.getString(0);
                    System.out.println("usuario Interno: "+idUI);
                    Toast.makeText(getApplicationContext(),"El Incidente se ha guardado correctamente",Toast.LENGTH_LONG).show();
                    //String rolGrupo = jsonArray.getString(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"El Incidente No se ha guardado",Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }});
        queue.add(stringRequest);
    }


}
