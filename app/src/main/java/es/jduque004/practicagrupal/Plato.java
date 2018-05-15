package es.jduque004.practicagrupal;

/**
 * Created by Migue on 12/05/2018.
 */

//LA CLASE DE PLATO

public class Plato {
    int id;
    String nombre;
    String descripcion;
    String ingredientes;
    String receta;
    String imagen;


    public Plato (String nombre, String descripcion, String ingredientes, String receta){
        this.nombre= nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.receta = receta;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return this.id;
    }

    public void setNombre(String nombre){

        this.nombre=nombre;
    }

    public String getNombre(){

        return  this.nombre;
    }

    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public String getDescripcion(){
        return  this.descripcion;
    }

    public void setIngredientes(String ingredientes){
        this.ingredientes=ingredientes;
    }

    public String getIngredientes(){
        return  this.ingredientes;
    }

    public void setReceta(String receta){
        this.receta=receta;
    }

    public String getReceta(){
        return  this.receta;
    }
}
