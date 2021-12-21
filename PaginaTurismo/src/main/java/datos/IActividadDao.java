package datos;

import dominio.Actividad;
import java.util.List;

public interface IActividadDao {
    List<Actividad> listar();
    List<Actividad> generar();
}
