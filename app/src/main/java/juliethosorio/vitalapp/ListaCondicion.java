package juliethosorio.vitalapp;

/**
 * Created by ljoso on 22/05/2017.
 */

public class ListaCondicion {

    private String Condicion,enfermedad,medicamentos;

    public ListaCondicion(){

    }

    public ListaCondicion(String condicion, String enfermedad, String medicamentos)
    {
        Condicion = condicion;
        this.enfermedad = enfermedad;
        this.medicamentos = medicamentos;
    }

    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String condicion) {
        Condicion = condicion;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }
}
