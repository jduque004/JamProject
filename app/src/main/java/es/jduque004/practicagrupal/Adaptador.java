package es.jduque004.practicagrupal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Migue on 09/03/2018.
 * AQUI GESTIONAMOS EL LISTVIEW QUE HAY EN LOS PEDIDOS DEL USUARIO
 */

public class Adaptador extends BaseAdapter {
    private ArrayList <Perfil> listaNotas;
    private Context contexto;
    private int clave;

    public Adaptador (Context contexto, ArrayList<Perfil> listaNotas, int clave){
        this.listaNotas=listaNotas;
        this.contexto=contexto;
        this.clave=clave;
    }

    @Override
    public int getCount() {
        return listaNotas.size();
    }

    @Override
    public Object getItem(int i) {
        return listaNotas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //AQUI SE PONEN LOS VALORES QUE SE MOSTRARAN EN EL LISTVIEW
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        //Nota item = (Nota) getItem(i);

        convertView = LayoutInflater.from(contexto).inflate(R.layout.item, null);
        TextView texto= convertView.findViewById(R.id.texto);

        //Quitar el desencriptar aqui para ver los efectos
        //texto.setText(item.getTexto());
        //texto.setText(desencriptar(item.getTexto(),clave));
        //System.out.println(item.getfoto());
        //if(item.getfoto()!=null) {
            ImageView view= convertView.findViewById(R.id.imageView);
            view.setImageResource(R.drawable.defaultprofile);

        //}
        return convertView;
    }
    //PODEMOS ENCRIPTAR Y DESENCRIPTAR DATOS DEL USUARIO SI QUEREMOS
    public String desencriptar(String texto, int clave){
        String message= texto;
        String decryptedMessage = "";
        int key = clave;
        char ch;

        for(int i = 0; i < message.length(); ++i){
            ch = message.charAt(i);

            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch - key);

                if(ch < 'a'){
                    ch = (char)(ch + 'z' - 'a' + 1);
                }

                decryptedMessage += ch;
            }
            else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch - key);

                if(ch < 'A'){
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }

                decryptedMessage += ch;
            }
            else {
                decryptedMessage += ch;
            }
        }

        return decryptedMessage;
    }
}
