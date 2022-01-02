package datos;

import dominio.Actividad;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface IActividadDao {
    List<Actividad> listar();
    
    List<Actividad> generar();
    
    Actividad encontrar(Actividad actividad);
    
    void insertar(Actividad actividad);
    
    void actualizar(Actividad actividad);
    
    void eliminar(Actividad actividad);
    
    void escribirImagen(int id, HttpServletResponse resp);
}
