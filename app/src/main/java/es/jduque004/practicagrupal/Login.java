package es.jduque004.practicagrupal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

//UN LOGIN NORMAL Y CORRIENTE

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText NombreUsuarioLogin;
    EditText ContraseñaUsuarioLogin;
    TextView lkRegistrateAqui;

    //DB db = new DB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NombreUsuarioLogin = findViewById(R.id.NombreUsuarioLogin);
        ContraseñaUsuarioLogin = findViewById(R.id.ContraseñaUsuarioLogin);
        btnLogin = findViewById(R.id.btnLogin);
        lkRegistrateAqui = findViewById(R.id.lkRegistrateAqui);

        btnLogin.setOnClickListener(this);
        lkRegistrateAqui.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:


                String nombre = NombreUsuarioLogin.getText().toString();
                String contraseña = ContraseñaUsuarioLogin.getText().toString();


                PHP php = null;
                try {
                    php = new PHP(nombre);

                //String json = php.execute().get();
                    String json = new GestorBDExterna(this).execute("comprobarLogin","email="+nombre+"&pass="+contraseña).get();
                //System.out.print(json);
                Log.i("DebugMio","JSON resultado: "+json);
                String prueba= "\"Pass\":\"" + contraseña +'"';

                if (json.indexOf(prueba)>=0){
                    Intent i = new Intent(Login.this,Menu.class);
                    i.putExtra("NombreUsuario",nombre);
                    i.putExtra("Clave", json.substring(json.indexOf("\"Cesar\":\"")+9,json.indexOf("\"Cesar\":\"")+11));
                    System.out.print(json.substring(json.indexOf("\"Cesar\":\"")+9,json.indexOf("\"Cesar\":\"")+11));
                    System.out.print(json.substring(json.indexOf("\"Estilo\":\"")+10,json.indexOf("\"Estilo\":\"")+13));
                    i.putExtra("Estilo",json.substring(json.indexOf("\"Estilo\":\"")+10,json.indexOf("\"Estilo\":\"")+13));

                    startActivity(i);
                }else{
                    Toast alerta = Toast.makeText(getApplicationContext(), this.getString(R.string.m_Datos), Toast.LENGTH_SHORT);
                    alerta.show();
                }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.lkRegistrateAqui:
                startActivity(new Intent(this, Registro.class));
                break;

        }
    }
}
