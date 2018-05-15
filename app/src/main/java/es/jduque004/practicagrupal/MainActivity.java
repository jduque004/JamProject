package es.jduque004.practicagrupal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.stetho.Stetho;

import java.util.Locale;

//LA PANTALLA DE INICIO

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnComenzar;
    Button btnIdiomas;
    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComenzar = findViewById(R.id.btnComenzar);
        btnIdiomas = findViewById(R.id.btnIdiomas);
        btnComenzar.setOnClickListener(this);
        btnIdiomas.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnComenzar:
                startActivity(new Intent(this, Login.class));

                Stetho.initialize(Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
                break;
                case R.id.btnIdiomas:
                    showDialog();
                break;
        }
    }
    private void showDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.m_Idioma));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which){
                    case 0:
                        locale = new Locale("en");
                        config.locale =Locale.ENGLISH;
                        Locale.setDefault(locale);
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale =new Locale("es","ES");
                        Locale.setDefault(locale);
                        break;

                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }
}
