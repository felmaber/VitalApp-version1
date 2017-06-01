package juliethosorio.vitalapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

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
        }
    }

}
