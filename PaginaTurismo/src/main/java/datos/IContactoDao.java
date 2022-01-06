package datos;

import dominio.*;
import java.util.*;

public interface IContactoDao {
    
    List<Contacto> listar();
    
    Contacto encontrar(Contacto contacto);
    
    int insertar(Contacto contacto);
    
    void eliminar(Contacto contacto);
}
