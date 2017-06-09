package juliethosorio.vitalapp;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ljoso on 8/06/2017.
 */

public class UsuarioVO  implements Serializable{
    String identificacion, nombre,tipoSangre,eps,correo,telefono,fecha,
            direccion, contacto,telContacto,login,pass,condicion,enfermedad,medicamentos;



    public UsuarioVO(){

    }

    public UsuarioVO(String identificacion, String nombre, String tipoSangre, String eps,
                     String correo, String telefono, String direccion, String contacto,
                     String telContacto, String login, String pass, String condicion,
                     String enfermedad, String medicamentos, String fecha) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tipoSangre = tipoSangre;
        this.eps = eps;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contacto = contacto;
        this.telContacto = telContacto;
        this.login = login;
        this.pass = pass;
        this.fecha = fecha;
        this.condicion = condicion;
        this.enfermedad = enfermedad;
        this.medicamentos = medicamentos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getEps() {
        return eps;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelContacto() {
        return telContacto;
    }

    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
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
