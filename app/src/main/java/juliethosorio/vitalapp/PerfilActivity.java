package juliethosorio.vitalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    TextView id,nombre,fecha,tipo,eps,correo,tel,dir,contacto,
            tel_contacto,condicion,enfermedad,medicamentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = (TextView) findViewById(R.id.campoIdPerfil);
        nombre = (TextView) findViewById(R.id.campoNombresPerfil);
        fecha = (TextView) findViewById(R.id.campoFechaNacimientoPerfil);
        tipo = (TextView) findViewById(R.id.campotipoSangrePerfil);
        eps = (TextView) findViewById(R.id.campoEPSperfil);
        correo = (TextView) findViewById(R.id.campoCorreoPerfil);
        tel = (TextView) findViewById(R.id.campotelefonoPerfil);
        dir = (TextView) findViewById(R.id.campoDireccionPerfil);
        contacto = (TextView) findViewById(R.id.campoContactoPerfil);
        tel_contacto = (TextView) findViewById(R.id.campotelContactoPerfil);
        condicion = (TextView) findViewById(R.id.campoCondicionPerfil);
        enfermedad = (TextView) findViewById(R.id.campoEnfermedadPerfil);
        medicamentos = (TextView) findViewById(R.id.campoMedicamentosPerfil);

        //activar el btnAtras en el actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mostrarPerfil();

    }

    private void mostrarPerfil() {

        UsuarioVO usuarioVO2= (UsuarioVO)getIntent().getSerializableExtra("usuario");

        id.setText(usuarioVO2.getIdentificacion());
        nombre.setText(usuarioVO2.getNombre());
        fecha.setText(usuarioVO2.getFecha());
        tipo.setText(usuarioVO2.getTipoSangre());
        eps.setText(usuarioVO2.getEps());
        correo.setText(usuarioVO2.getCorreo());
        tel.setText(usuarioVO2.getTelefono());
        dir.setText(usuarioVO2.getDireccion());
        contacto.setText(usuarioVO2.getContacto());
        tel_contacto.setText(usuarioVO2.getTelContacto());

        if(usuarioVO2.getCondicion().equals("")){
            condicion.setVisibility(View.GONE);
        }else {
            condicion.setVisibility(View.VISIBLE);
            condicion.setText(usuarioVO2.getCondicion());
        }

        if(usuarioVO2.getEnfermedad().equals("")){
            enfermedad.setVisibility(View.GONE);
        }else {
            enfermedad.setVisibility(View.VISIBLE);
            enfermedad.setText(usuarioVO2.getEnfermedad());
        }

        if(usuarioVO2.getMedicamentos().equals("")){
            medicamentos.setVisibility(View.GONE);
        }else {
            medicamentos.setVisibility(View.VISIBLE);
            medicamentos.setText(usuarioVO2.getMedicamentos());
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //metodo para el menu overflow del action bar
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent volverMenu =new Intent(PerfilActivity.this,Menu_Activity.class);
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
}
