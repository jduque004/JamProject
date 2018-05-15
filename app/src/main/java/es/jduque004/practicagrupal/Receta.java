package es.jduque004.practicagrupal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//AQUI SE MUESTRA MAS INFORMACION DE CADA PLATO. PUEDES PEDIRLO Y AÑADIRLE UNA NOTA
//SI PIDES EL PLATO SE TE ENVIARA AL CALENDARIO DONDE PODRAS SELECCIONAR LA FECHA EN LA QUE TE LLEGARA

public class Receta extends AppCompatActivity  implements View.OnClickListener {
    EditText NotaNueva;
    Button btnGuardar;

    private String nombreUsuario;
    private String estilo;
    private String clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.activity_receta);

        btnGuardar = findViewById(R.id.btnGuardar);

    }


    @Override
    public void onClick(View view) {
        NotaNueva = findViewById(R.id.NotaNueva);
        switch(view.getId()){
            case R.id.btnGuardar:
                    Intent i = new Intent(Receta.this,Calendario.class);
                    i.putExtra("NombreUsuario",nombreUsuario);
                    i.putExtra("Estilo",estilo);
                    i.putExtra("Clave",clave);

                    //AÑADIR LA ID DEL PLATO
                    //AÑADIR LA NOTA SI LA HAY

                    startActivity(i);
                break;

        }
    }
}
