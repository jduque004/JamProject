package es.jduque004.practicagrupal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

//AQUI SE MUESTRAN LOS PLATOS QUE HAY, SI EL USUARIO PULSA UNO SE LE DARA MAS INFORMACION SOBRE EL Y LA OPCION DE PEDIRLO
//AUN TIENE TROZOS DEL PROYECTO ANTERIOR

public class Recetas extends AppCompatActivity {
    private ListView lvItems;
    private Adaptador2 adaptador;
    
    private String nombreUsuario;
    private String estilo;
    private String clave;

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

        setContentView(R.layout.activity_recetas);

        lvItems= findViewById(R.id.listView);
        //adaptador = new Adaptador2(this,getPlatos(),Integer.parseInt(clave));
        lvItems.setAdapter(adaptador);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Recetas.this,Calendario.class);
                //db2.borrarNota(notas.get(i).getNombre(), notas.get(i).getTexto());
                intent.putExtra("NombreUsuario",nombreUsuario);
                intent.putExtra("Estilo",estilo);
                intent.putExtra("Clave",clave);
                startActivity(intent);
            }
        });
    }


}
