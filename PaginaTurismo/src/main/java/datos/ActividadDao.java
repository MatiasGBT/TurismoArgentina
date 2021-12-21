package datos;

import dominio.Actividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActividadDao implements IActividadDao {

    private static final String SQL_SELECT = "SELECT idactividad, nombre, imagen, precio"
            + " FROM actividad";

    private static final String SQL_SELECT_BY_ID = "SELECT idactividad, nombre, imagen, precio"
            + " FROM actividad WHERE idactividad IN(?,?,?)";

    private static final String SQL_INSERT = "INSERT INTO actividad(idactividad, nombre, imagen, precio)"
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE actividad"
            + " SET nombre=?, imagen=?, precio=? WHERE idactividad=?";

    private static final String SQL_DELETE = "DELETE FROM actividad WHERE idactividad=?";

    @Override
    public List<Actividad> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Actividad actividad;
        List<Actividad> actividades = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idActividad = rs.getInt("idactividad");
                String nombre = rs.getString("nombre");
                String imagen = rs.getString("imagen");
                double precio = rs.getDouble("precio");

                actividad = new Actividad(idActividad, nombre, imagen, precio);
                actividades.add(actividad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return actividades;
    }
    
    @Override
    public List<Actividad> generar() {
        int n1 = (int) (Math.random() * 3 + 1);
        int n2 = (int) (Math.random() * 3 + 4);
        int n3 = (int) (Math.random() * 3 + 7);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Actividad actividad;
        List<Actividad> actividades = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, n1);
            stmt.setInt(2, n2);
            stmt.setInt(3, n3);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idActividad = rs.getInt("idactividad");
                String nombre = rs.getString("nombre");
                String imagen = rs.getString("imagen");
                double precio = rs.getDouble("precio");
                
                actividad=new Actividad(idActividad, nombre, imagen, precio);
                actividades.add(actividad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return actividades;
    }

}
