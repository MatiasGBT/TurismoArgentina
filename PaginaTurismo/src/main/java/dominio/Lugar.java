package dominio;

public class Lugar {
    private int idLugar;
    private String nombre;
    private String descripcion;
    private String portada;
    private String foto1;
    private String foto2;
    private String foto3;
    
    public Lugar() {
        
    }
    
    public Lugar(int idLugar) {
        this.idLugar=idLugar;
    }
    
    public Lugar(String nombre) {
        this.nombre=nombre;
    }

    public Lugar(String nombre, String descripcion, String portada, String foto1, String foto2, String foto3) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
    }

    public Lugar(int idLugar, String nombre, String descripcion, String portada, String foto1, String foto2, String foto3) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
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

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    @Override
    public String toString() {
        return "Lugar{" + "idLugar=" + idLugar + ", nombre=" + nombre + ", descripcion=" + descripcion + ", portada=" + portada + ", foto1=" + foto1 + ", foto2=" + foto2 + ", foto3=" + foto3 + '}';
    }
}
