package datos;

import dominio.Lugar;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface ILugarDao {
    List<Lugar> listar();
    
    Lugar encontrar(Lugar lugar);
    
    void insertar(Lugar lugar);
    
    void actualizar(Lugar lugar);
    
    void eliminar(Lugar lugar);
    
    void listarPortada(int id, HttpServletResponse resp);
    
    void listarFoto1(int id, HttpServletResponse resp);
    
    void listarFoto2(int id, HttpServletResponse resp);
    
    void listarFoto3(int id, HttpServletResponse resp);
}
