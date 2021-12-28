package dominio;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String contraseña;
    private Cargo cargo;

    public Usuario() {
        
    }
    
    public Usuario(String nombre, String email, String contraseña) {
        this.nombre=nombre;
        this.email=email;
        this.contraseña=contraseña;
    }

    public Usuario(int idUsuario, String nombre, String email, String contraseña, Cargo cargo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", email=" + email + ", contrase\u00f1a=" + contraseña + ", cargo=" + cargo + '}';
    }
}
