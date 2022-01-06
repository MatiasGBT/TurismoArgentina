package dominio;

public class Contacto {
    private int idContacto;
    private String nombre;
    private String email;
    private String comentario;
    
    public Contacto() {
        
    }
    
    public Contacto(int idContacto) {
        this.idContacto=idContacto;
    }
    
    public Contacto(String nombre, String email, String comentario) {
        this.nombre=nombre;
        this.email=email;
        this.comentario=comentario;
    }
    
    public Contacto(int idContacto, String nombre, String email, String comentario) {
        this.idContacto=idContacto;
        this.nombre=nombre;
        this.email=email;
        this.comentario=comentario;
    }
    
    public int getIdContacto() {
        return this.idContacto;
    }
    
    public void setIdContacto(int idContacto) {
        this.idContacto=idContacto;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" + "idContacto=" + idContacto + ", nombre=" + nombre + ", email=" + email + ", comentario=" + comentario + '}';
    }
}
