package juliethosorio.vitalapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    JSONArray jsonArray;

    EditText txtUsuario,txtContraseña;

    UsuarioVO usuarioVO= new UsuarioVO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtContraseña=(EditText)findViewById(R.id.txtContraseña);

        TextView createAccountText = (TextView) findViewById(R.id.txtRegistrarse);
        Resources res = getResources();
        CharSequence styledText = res.getText(R.string.Registrarse);
        createAccountText.setText(styledText, TextView.BufferType.SPANNABLE);

        TextView createAccountText2 = (TextView) findViewById(R.id.txtOlvidoContraseña);
        Resources res2 = getResources();
        CharSequence styledText2 = res2.getText(R.string.OlvidarContraseña);
        createAccountText2.setText(styledText2, TextView.BufferType.SPANNABLE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabQR);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtRegistrarse:
                Intent regreso = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);
                startActivity(regreso);
                break;
            case R.id.txtOlvidoContraseña:
                Intent regreso2 = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);
                startActivity(regreso2);
                break;
            case R.id.btnIngresar:
                if(txtUsuario.getText().toString().equals("")&&txtContraseña.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Ingrese Usuario y Contraseña",Toast.LENGTH_SHORT).show();
                }
                else{
                ConsultaPass("http://10.0.3.2/vitalapp/login.php?user="+txtUsuario.getText().toString());
                }
                break;
        }
    }

    private void ConsultaPass(String URL) {

        Log.i("url",""+URL);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    jsonArray = new JSONArray(response);
                    usuarioVO.setIdentificacion(jsonArray.getString(0));
                    usuarioVO.setNombre(jsonArray.getString(1));
                    usuarioVO.setFecha(jsonArray.getString(2));
                    usuarioVO.setTipoSangre(jsonArray.getString(3));
                    usuarioVO.setEps(jsonArray.getString(4));
                    usuarioVO.setCorreo(jsonArray.getString(5));
                    usuarioVO.setTelefono(jsonArray.getString(6));
                    usuarioVO.setDireccion(jsonArray.getString(7));
                    usuarioVO.setContacto(jsonArray.getString(8));
                    usuarioVO.setTelContacto(jsonArray.getString(9));
                    usuarioVO.setCondicion(jsonArray.getString(12));
                    usuarioVO.setEnfermedad(jsonArray.getString(13));
                    usuarioVO.setMedicamentos(jsonArray.getString(14));


                    String pass = jsonArray.getString(11);
                    if(pass.equals(txtContraseña.getText().toString())){

                        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, Menu_Activity.class);

                        Bundle bundleUsuario=new Bundle();
                        bundleUsuario.putSerializable("usuario",usuarioVO);
                        intent.putExtras(bundleUsuario);

                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(),"verifique su contraseña",Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(),"El usuario no existe en la base de datos",Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }



}
