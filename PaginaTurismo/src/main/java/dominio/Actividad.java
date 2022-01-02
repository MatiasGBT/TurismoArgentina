package dominio;

import java.io.InputStream;

public class Actividad {
    private int idActividad;
    private String nombre;
    private InputStream imagen;
    private double precio;
    
    public Actividad() {
        
    }
    
    public Actividad(int idActividad) {
        this.idActividad=idActividad;
    }

    public Actividad(String nombre, InputStream imagen, double precio) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
    }

    public Actividad(int idActividad, String nombre, InputStream imagen, double precio) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Actividad{" + "idActividad=" + idActividad + ", nombre=" + nombre + ", imagen=" + imagen + ", precio=" + precio + '}';
    }
}
