package es.jduque004.practicagrupal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;

//EL PERFIL DEL USUARIO, TODO SE PUEDE VER Y CAMBIAR

public class Perfil extends AppCompatActivity implements View.OnClickListener {
    private String nombreUsuario;
    private String estilo;
    private String clave;

    private Configuration config = new Configuration();


    Button btnMN;
    Button btnME;
    Button btnMA;
    Button btnMD;
    Button btnML;
    Button btnMI;
    Button btnMC;

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

        setContentView(R.layout.activity_perfil);


        btnMI = findViewById(R.id.btnModificarImagen);
        btnMI.setOnClickListener(this);
        btnMN = findViewById(R.id.btnModificarNombre);
        btnMN.setOnClickListener(this);
        btnMC = findViewById(R.id.btnModificarContraseña);
        btnMC.setOnClickListener(this);
        btnME = findViewById(R.id.btnModificarEstilo);
        btnME.setOnClickListener(this);
        btnMA = findViewById(R.id.btnModificarAlta);
        btnMA.setOnClickListener(this);
        btnMD = findViewById(R.id.btnModificarDireccion);
        btnMD.setOnClickListener(this);
        btnML = findViewById(R.id.btnModificarLocalizacion);
        btnML.setOnClickListener(this);

        TextView textView1 = findViewById(R.id.TVnombreUsuario);
        textView1.setText(nombreUsuario);

        TextView textView2 = findViewById(R.id.TVEstilo);
        switch (estilo) {
            case "001":
                textView2.setText(this.getString(R.string.m_VH));
                break;
            case "004":
                textView2.setText(this.getString(R.string.m_RF));
                break;
            case "007":
                textView2.setText(this.getString(R.string.m_AA));
                break;
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnModificarEstilo:
                showDialog();
                break;

        }
    }
    private void showDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.m_Estilos));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.styles);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(Perfil.this, Menu.class);
                switch(which){
                    case 0:
                        try {
                            PHP3 php3 = new PHP3(nombreUsuario,"001");
                            php3.execute();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        refresh.putExtra("Estilo","001");
                        recreate();
                        break;
                    case 1:
                        try {
                            PHP3 php3 = new PHP3(nombreUsuario,"004");
                            php3.execute();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        refresh.putExtra("Estilo","004");
                        recreate();
                        break;
                    case 2:
                        try {
                            PHP3 php3 = new PHP3(nombreUsuario,"007");
                            php3.execute();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        refresh.putExtra("Estilo","007");
                        recreate();
                        break;
                }
                refresh.putExtra("NombreUsuario",nombreUsuario);
                refresh.putExtra("Clave",clave);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }
}
