package es.jduque004.practicagrupal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Migue on 12/05/2018.
 * AQUI GESTIONAMOS EL LISTVIEW QUE CONTIENE LOS PLATOS DIPONIBLES
 */

public class Adaptador2  extends BaseAdapter {
    private ArrayList<Plato> listaNotas;
    private Context contexto;
    private int clave;

    public Adaptador2 (Context contexto, ArrayList<Plato> listaNotas, int clave){
        //this.listaNotas=listaNotas;
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


    //AQUI PONEMOS LOS DATOS QUE APARECEN EN CADA ELEMENTO DEL LISTVIEW
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        //Nota item = (Nota) getItem(i);

        //convertView = LayoutInflater.from(contexto).inflate(R.layout.item, null);
        //TextView texto= convertView.findViewById(R.id.texto);

        //texto.setText(item.getTexto());
        //System.out.println(item.getfoto());
        //if(item.getfoto()!=null) {
            ImageView view= convertView.findViewById(R.id.imageView);
            view.setImageResource(R.drawable.defaultprofile);

        //}
        return convertView;
    }

}
