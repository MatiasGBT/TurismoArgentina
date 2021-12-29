package dominio;

import java.io.InputStream;

public class Lugar {
    private int idLugar;
    private String nombre;
    private String descripcion;
    private InputStream portada;
    private InputStream foto1;
    private InputStream foto2;
    private InputStream foto3;
    private double precio;
    
    public Lugar() {
        
    }
    
    public Lugar(int idLugar) {
        this.idLugar=idLugar;
    }

    public Lugar(String nombre, String descripcion, InputStream portada, InputStream foto1, InputStream foto2, InputStream foto3, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.precio = precio;
    }

    public Lugar(int idLugar, String nombre, String descripcion, InputStream portada, InputStream foto1, InputStream foto2, InputStream foto3, double precio) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.precio = precio;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public InputStream getPortada() {
        return portada;
    }

    public void setPortada(InputStream portada) {
        this.portada = portada;
    }

    public InputStream getFoto1() {
        return foto1;
    }

    public void setFoto1(InputStream foto1) {
        this.foto1 = foto1;
    }

    public InputStream getFoto2() {
        return foto2;
    }

    public void setFoto2(InputStream foto2) {
        this.foto2 = foto2;
    }

    public InputStream getFoto3() {
        return foto3;
    }

    public void setFoto3(InputStream foto3) {
        this.foto3 = foto3;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Lugar{" + "idLugar=" + idLugar + ", nombre=" + nombre + ", descripcion=" + descripcion + ", portada=" + portada + ", foto1=" + foto1 + ", foto2=" + foto2 + ", foto3=" + foto3 + '}';
    }
}
