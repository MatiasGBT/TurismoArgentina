package datos;

import dominio.Usuario;
import java.util.List;

public interface IUsuarioDao {
    Usuario identificar(Usuario usuario);
    
    int insertar(Usuario usuario);
    
    List<Usuario> listar();
}
