package juliethosorio.vitalapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class TiendaVitalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TiendaFragment.OnFragmentInteractionListener,
        Historial_IncidenciasFragment.OnFragmentInteractionListener{

    TiendaFragment tiendaFragment;
    Historial_IncidenciasFragment miHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_vital);

        tiendaFragment=new TiendaFragment();
        miHistorial=new Historial_IncidenciasFragment();

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.content_tienda_vital,tiendaFragment).commit();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
                Intent irLogin= new Intent(this,Menu_Activity.class);
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
            Intent perfil= new Intent(TiendaVitalActivity.this,PerfilActivity.class);

            Bundle usuarioperfiltiendabundle=new Bundle();
            usuarioperfiltiendabundle.putSerializable("usuario",usuarioVO2);
            perfil.putExtras(usuarioperfiltiendabundle);

            startActivity(perfil);
        } else if (id == R.id.profesionalSalud) {

            Intent profesional=new Intent(TiendaVitalActivity.this,ProfesionalActivity.class);
            startActivity(profesional);

        } else if (id == R.id.historialIncidencias) {
            fragmento= new Historial_IncidenciasFragment();
            seleccion=true;
        }
        if(seleccion){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_tienda_vital,fragmento).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onClick(View view)
    {
        Intent intent=null;
        switch (view.getId()){
            case R.id.btnMenuQR:
                intent=new Intent(this,RegistroUsuarioActivity.class);
                break;
            case R.id.btnMenuGrupos:
                intent=new Intent(this,GruposActivity.class);
                break;
            case R.id.btnMenuDependiente:
                intent=new Intent(this, ListaDependientesActivity.class);
                break;
            case R.id.btnMenuCompras:
                intent=new Intent(this,TiendaVitalActivity.class);
                break;
        }
        startActivity(intent);
    }

    public void onFragmentInteraction(Uri uri) {

    }
}
