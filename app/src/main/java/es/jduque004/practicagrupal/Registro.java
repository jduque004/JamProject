package es.jduque004.practicagrupal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

//UN REGISTRO NORMAL Y CORRIENTE

public class Registro extends AppCompatActivity implements View.OnClickListener{

    //DB db = new DB(this);

    Button btnRegistro;
    EditText NombreUsuarioRegistro;
    EditText ContraseñaUsuarioRegistro;
    EditText ContraseñaUsuarioRegistro2;

    RequestQueue requestQueue;



    private static final String USER_AGENT = "Mozilla/5.0";

    public Registro() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        NombreUsuarioRegistro = findViewById(R.id.NombreUsuarioRegistro);
        ContraseñaUsuarioRegistro = findViewById(R.id.ContraseñaUsuarioRegistro);
        ContraseñaUsuarioRegistro2 = findViewById(R.id.ContraseñaUsuarioRegistro2);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegistro:
                String nombre = NombreUsuarioRegistro.getText().toString();
                String contraseña = ContraseñaUsuarioRegistro.getText().toString();
                String contraseña2 = ContraseñaUsuarioRegistro2.getText().toString();

                if (!contraseña.equals(contraseña2)) {
                    Toast alerta = Toast.makeText(getApplicationContext(), this.getString(R.string.m_Contraseñas), Toast.LENGTH_SHORT);
                    alerta.show();
                }
                if(nombre.isEmpty())Toast.makeText(getApplicationContext(), this.getString(R.string.m_NombreVacio), Toast.LENGTH_SHORT).show();

                try {
                    PHP php = new PHP(nombre);
                    String json = php.execute().get();
                    System.out.print(json);
                    String prueba= "\"Nombre\":\"" + nombre +'"';

                if (json.indexOf(prueba)>=0) {
                    Toast alerta = Toast.makeText(getApplicationContext(), this.getString(R.string.m_NombreCogido), Toast.LENGTH_SHORT);
                    alerta.show();
                } else {
                    PHP2 php2 = new PHP2(nombre,contraseña);
                    php2.execute();
                    //Usuario usuarioRegistrado = new Usuario(nombre, contraseña);
                    //db.insertar(usuarioRegistrado);
                    startActivity(new Intent(this, Login.class));
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }



}
















