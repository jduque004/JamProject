package es.jduque004.practicagrupal;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

//AQUI SE MUESTRAN LOS PEDIDOS PARA UN DIA CONCRETO Y SE DA LA OPCION DE HACER PEDIDOS
//SI EL STRING idPlato QUE TE HAN PASADO NO ES NULO SE AÑADIRA EL PLATO CORRESPONDIENTE A ESE STRING COMO PEDIDO PARA ESE DIA

public class Dia extends AppCompatActivity implements View.OnClickListener {
    private ListView lvItems;
    private Adaptador adaptador;

    EditText NotaNueva;
    Button btnGuardar;
    String nombreUsuario;
    String fecha;
    String estilo;
    String clave;
    String idPlato;
    String nota;
    Uri imgFoto;

    private final String CarpetaR ="";
    private final String ImagenR =CarpetaR +"misfotos";
    private final int COD_SELEC=10;

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;

    //private ArrayList<Nota>notas;

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
        setContentView(R.layout.activity_dia);

        fecha = getIntent().getStringExtra("Fecha");


        //notas=db2.notasUsuario(nombreUsuario, fecha);
        NotaNueva = findViewById(R.id.NotaNueva);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(this);

        lvItems= findViewById(R.id.listView);
       // adaptador = new Adaptador(this,notas,Integer.parseInt(clave));
        lvItems.setAdapter(adaptador);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Dia.this,Calendario.class);
                //db2.borrarNota(notas.get(i).getNombre(), notas.get(i).getTexto());
                intent.putExtra("NombreUsuario",nombreUsuario);
                intent.putExtra("Estilo",estilo);
                intent.putExtra("Clave",clave);
                startActivity(intent);
         }
        });
    }


    //AL PULSAR EL BOTON DE AÑADIR SE TE ENVIA A RECETAS
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnGuardar:
                if(idPlato!=null){
                    dialogoConfirmacion(nota);
                }
                else{
                    Intent i = new Intent(Dia.this,Recetas.class);
                    i.putExtra("NombreUsuario",nombreUsuario);
                    i.putExtra("Estilo",estilo);
                    i.putExtra("Clave",clave);
                    startActivity(i);
                }
                break;

        }
    }


    //DIALOGO DE CONFIRMACION QUE SE PUEDE REUTILIZAR
    public AlertDialog dialogoConfirmacion(final String nota){
        AlertDialog.Builder constructor= new AlertDialog.Builder(Dia.this);
        constructor.setTitle(this.getString(R.string.m_Atencion)).setMessage(this.getString(R.string.m_Notificacion)).setPositiveButton(this.getString(R.string.m_Si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                if(imgFoto!=null) {
                    System.out.println(imgFoto.getPath().toString());
                    //Nota notaNueva = new Nota(nombreUsuario, fecha, encriptar(nota, clave), imgFoto.getPath().toString());
                    //db2.insertar(notaNueva);
                }else{
                    //Nota notaNueva = new Nota(nombreUsuario, fecha, encriptar(nota, clave), null);
                    //db2.insertar(notaNueva);
                }
                Intent i = new Intent(Dia.this,Menu.class);
                i.putExtra("NombreUsuario",nombreUsuario);
                i.putExtra("Estilo",estilo);
                i.putExtra("Clave",clave);
                //notificacionNota();
                startActivity(i);
            }
        })
                .setNegativeButton(this.getString(R.string.m_No),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
            }
        });
    return constructor.create();

    }


    //PODEMOS ENCRIPTAR Y DESENCRIPTAR LO QUE GUARDE EL USUARIO
    public String encriptar (String nota, String clave){
        String message= nota;
        String encryptedMessage = "";
        int key=Integer.parseInt(clave);
        char ch;


        for(int i = 0; i < message.length(); ++i){
            ch = message.charAt(i);

            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch + key);

                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' - 1);
                }

                encryptedMessage += ch;
            }
            else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch + key);

                if(ch > 'Z'){
                    ch = (char)(ch - 'Z' + 'A' - 1);
                }

                encryptedMessage += ch;
            }
            else {
                encryptedMessage += ch;
            }
        }

        return encryptedMessage;
    }



}







