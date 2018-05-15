package es.jduque004.practicagrupal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//EL MENU PRINCIPAL CON TODAS SUS OPCIONES

public class Menu extends AppCompatActivity implements View.OnClickListener {

    private String nombreUsuario;
    private String estilo;
    private String clave;

    Button btnRecetas;
    Button btnPerfil;
    Button btnCalendario;
    Button btnAlta;
    Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ESTO SIRVE PARA QUE SE MANTENGA EL ESTILO DEL USUARIO

        nombreUsuario = getIntent().getStringExtra("NombreUsuario");
        estilo = getIntent().getStringExtra("Estilo");
        clave = getIntent().getStringExtra("Clave");
        if(estilo.equals("001")){
            setTheme(R.style.AppThemeLG);
        }
        if(estilo.equals("004")){
            setTheme(R.style.AppThemeFR);
        }
        if(estilo.equals("007")){
            setTheme(R.style.AppThemeWB);
        }

        setContentView(R.layout.activity_menu);

        btnRecetas = findViewById(R.id.btnRecetas);
        btnRecetas.setOnClickListener(this);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(this);
        btnCalendario = findViewById(R.id.btnCalendario);
        btnCalendario.setOnClickListener(this);
        btnAlta = findViewById(R.id.btnAlta);
        btnAlta.setOnClickListener(this);
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecetas:
                Intent i = new Intent(Menu.this,Recetas.class);
                i.putExtra("NombreUsuario",nombreUsuario);
                i.putExtra("Estilo",estilo);
                i.putExtra("Clave",clave);
                startActivity(i);
                break;
            case R.id.btnPerfil:
                Intent ii = new Intent(Menu.this,Perfil.class);
                ii.putExtra("NombreUsuario",nombreUsuario);
                ii.putExtra("Estilo",estilo);
                ii.putExtra("Clave",clave);
                startActivity(ii);
                break;
            case R.id.btnCalendario:
                Intent iii = new Intent(Menu.this,Calendario.class);
                iii.putExtra("NombreUsuario",nombreUsuario);
                iii.putExtra("Estilo",estilo);
                iii.putExtra("Clave",clave);
                startActivity(iii);
                break;
            case R.id.btnAlta:
                Intent iv = new Intent(Menu.this,Alta.class);
                iv.putExtra("NombreUsuario",nombreUsuario);
                iv.putExtra("Estilo",estilo);
                iv.putExtra("Clave",clave);
                startActivity(iv);
                break;
            case R.id.btnSalir:
                Intent v = new Intent(Menu.this,Login.class);
                startActivity(v);
                break;
        }
    }
}