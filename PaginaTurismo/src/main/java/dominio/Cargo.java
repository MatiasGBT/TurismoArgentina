package dominio;

public class Cargo {
    private int idCargo;
    private String nombre;

    public Cargo() {
        
    }
    
    public Cargo(String nombre) {
        this.nombre=nombre;
    }
    
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
