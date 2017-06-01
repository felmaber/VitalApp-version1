package juliethosorio.vitalapp;

/**
 * Created by ljoso on 22/05/2017.
 */

public class ListaIncidencias {

    private String NombreQR,Fecha, Hora;


    public ListaIncidencias(){

    }

    public ListaIncidencias(String fecha, String hora, String nombre) {
        Fecha = fecha;
        Hora = hora;
        NombreQR = nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getNombre() {
        return NombreQR;
    }

    public void setNombre(String nombre) {
        NombreQR = nombre;
    }
}
