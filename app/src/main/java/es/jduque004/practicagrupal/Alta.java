package es.jduque004.practicagrupal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//AQUI ES DONDE EL USUARIO SE PODRA DAR DE ALTA
public class Alta extends AppCompatActivity  implements View.OnClickListener{
    private String nombreUsuario;
    private String estilo;
    private String clave;

    Button btnAlta;


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

        setContentView(R.layout.activity_alta);

        btnAlta = findViewById(R.id.btnAlta);
        btnAlta.setOnClickListener(this);


    }

    //EL BOTON QUE ACTIVARA EL TRAMITE PARA DAR DE ALTA
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAlta:
                Intent i = new Intent(Alta.this,Menu.class);
                i.putExtra("NombreUsuario",nombreUsuario);
                i.putExtra("Clave", clave);
                i.putExtra("Estilo",estilo);
                startActivity(i);
                break;
        }
    }
}
