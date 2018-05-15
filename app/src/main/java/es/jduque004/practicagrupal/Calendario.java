package es.jduque004.practicagrupal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;

//AQUI SE MUESTRA EL CALENDARIO, SI EL USUARIO TOCA UNA FECHA SE LE ENSEÑARA LOS PEDIDOS QUE TIENE PARA ESE DIA Y SE LE DARA LA OPCION DE AÑADIR MAS
//AQUI SE PUEDE LLEGAR DE DOS FORMAS:
//1- ELIGIENDO CALENDARIO DESDE EL MENU PRINCIPAL PARA CONSULTAR LOS PEDIDOS
//2- DESPUES DE HABER ELEGIDO UN PLATO PARA ELEGIR LA FECHA EN LA QUE TE LO MANDAN

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Calendario extends AppCompatActivity implements View.OnClickListener {

    CompactCalendarView calendarioCompacto;
    private SimpleDateFormat formatoMeses = new SimpleDateFormat("MMMM- yyyy");
    private String nombreUsuario;
    private String estilo;
    private String clave;
    private String idPlato;
    private String nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ESTO SIRVE PARA QUE SE MANTENGA EL ESTILO DEL USUARIO

        nombreUsuario = getIntent().getStringExtra("NombreUsuario");
        estilo = getIntent().getStringExtra("Estilo");
        clave = getIntent().getStringExtra("Clave");
        idPlato = getIntent().getStringExtra("idPlato");
        nota = getIntent().getStringExtra("Nota");
        if(estilo.equals("001")){
            setTheme(R.style.AppThemeLG);
        }
        if(estilo.equals("004")){
            setTheme(R.style.AppThemeFR);
        }
        if(estilo.equals("007")){
            setTheme(R.style.AppThemeWB);
        }
        setContentView(R.layout.activity_calendario);





        calendarioCompacto = findViewById(R.id.compactcalendar_view);
        calendarioCompacto.setUseThreeLetterAbbreviation(true);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);





        calendarioCompacto.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Intent i = new Intent(Calendario.this,Dia.class);
                i.putExtra("NombreUsuario",nombreUsuario);
                i.putExtra("Fecha",dateClicked.toString());
                i.putExtra("Estilo",estilo);
                i.putExtra("Clave",clave);
                i.putExtra("idPlato",idPlato);
                i.putExtra("Nota",nota);
                startActivity(i);
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(formatoMeses.format(firstDayOfNewMonth));
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }




}
