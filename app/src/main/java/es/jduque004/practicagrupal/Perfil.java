package es.jduque004.practicagrupal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
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

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    ImageView imagen;


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
                 /*

        String json = new GestorBDExterna(this).execute("obtenerDatosUsuario","id="+id).get();


        imagen =  findViewById(R.id.imageView);
        imagen.setImageBitmap();

        */
        textView3 = findViewById(R.id.TVcontraseña);
        textView3.setText("");

        textView4 = findViewById(R.id.TVAlta);
        textView4.setText("");

        textView5 = findViewById(R.id.TVDirecction);
        textView5.setText("");

        textView6 = findViewById(R.id.TVLocalizacion);
        textView6.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnModificarEstilo:
                showDialog();
                break;
            case R.id.btnModificarImagen:
                showImageDialog();

                ImageView imagen =  findViewById(R.id.imageView);
                Bitmap bitmap = ((BitmapDrawable)imagen.getDrawable()).getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                String imageStr = Base64.encodeToString(byteArray,Base64.URL_SAFE);

                /*
                String json = new GestorBDExterna(this).execute("modificarImagen","nombre="+nombreUsuario+"&imagen="+imageStr).get();
                 */
                break;
            case R.id.btnModificarNombre:
                String name = textView1.getText().toString();
                /*
                String json = new GestorBDExterna(this).execute("modificarNombre",nombre="+name).get();
                */
                nombreUsuario = name;
                break;
            case R.id.btnModificarContraseña:
                String pass = textView3.getText().toString();
                /*
                String json = new GestorBDExterna(this).execute("modificarContraseña","nombre="+name+"&contraseña="+pass).get();
                */
                break;
            case R.id.btnModificarDireccion:
                String dir = textView5.getText().toString();
                /*
                String json = new GestorBDExterna(this).execute("modificarDireccion","nombre="+name+"&direccion="+dir).get();
                */
                break;
            case R.id.btnModificarLocalizacion:
                break;
            case R.id.btnModificarAlta:
                showAltaDialog();
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

    private void showImageDialog(){
        final CharSequence[] items = new CharSequence[3];
        items[0] = "Sacar Foto";
        items[1] = "Elegir de Galería";
        items[2] = "Cancelar";
        new AlertDialog.Builder(this)
                .setTitle("Subir imagen")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(items[i].equals("Sacar Foto")){
                            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(pictureIntent.resolveActivity(getPackageManager()) != null){
                                startActivityForResult(pictureIntent, 1234);
                            }
                        }else if(items[i].equals("Elegir de Galería")){
                            Intent galeryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(galeryIntent, 4567);
                        }else{
                            dialogInterface.dismiss();
                        }
                    }
                }).show();
    }

    private void showAltaDialog(){
        final CharSequence[] items = new CharSequence[3];
        items[0] = "Plan 1";
        items[1] = "Plan 2";
        items[2] = "Plan 3";
        new AlertDialog.Builder(this)
                .setTitle("Elegir plan")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*
                        String json = new GestorBDExterna(this).execute("modificarAlta","id="+id+"&alta="+items[i]).get();
                        */
                        textView4.setText(items[i]);
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        } else if(requestCode == 4567 && resultCode == RESULT_OK){
            Uri imagenSeleccionada = data.getData();
            imagen.setImageURI(imagenSeleccionada);
        }
    }
}
