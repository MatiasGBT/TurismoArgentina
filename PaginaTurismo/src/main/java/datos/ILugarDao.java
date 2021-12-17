package datos;

import dominio.Lugar;
import java.util.List;

public interface ILugarDao {
    List<Lugar> listar();
    
    Lugar encontrar(Lugar lugar);
}
