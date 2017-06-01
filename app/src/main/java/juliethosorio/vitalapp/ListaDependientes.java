package juliethosorio.vitalapp;

/**
 * Created by ljoso on 22/05/2017.
 */

public class ListaDependientes {

    private String Nombre;

    public ListaDependientes(){

    }

    public ListaDependientes(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
