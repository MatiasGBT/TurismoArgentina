package datos;

import dominio.Lugar;
import java.sql.*;
import java.util.*;

public class LugarDao implements ILugarDao {

    private static final String SQL_SELECT = "SELECT idlugar, nombre, descripcion, portada, foto1, foto2, foto3"
            + " FROM lugar";

    private static final String SQL_SELECT_BY_ID = "SELECT idlugar, nombre, descripcion, portada, foto1, foto2, foto3"
            + " FROM lugar WHERE idlugar = ?";

    private static final String SQL_SELECT_BY_NAME = "SELECT idlugar, nombre, descripcion, portada, foto1, foto2, foto3"
            + " FROM lugar WHERE nombre = ?";

    private static final String SQL_INSERT = "INSERT INTO lugar(idlugar, nombre, descripcion, portada, foto1, foto2, foto3)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE lugar"
            + " SET nombre=?, descripcion=?, portada=?, foto1=?, foto2=?, foto3=? WHERE idlugar=?";

    private static final String SQL_DELETE = "DELETE FROM lugar WHERE idlugar=?";

    @Override
    public List<Lugar> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Lugar lugar;
        List<Lugar> lugares = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idLugar = rs.getInt("idlugar");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String portada = rs.getString("portada");
                String foto1 = rs.getString("foto1");
                String foto2 = rs.getString("foto2");
                String foto3 = rs.getString("foto3");

                lugar = new Lugar(idLugar, nombre, descripcion, portada, foto1, foto2, foto3);
                lugares.add(lugar);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return lugares;
    }

    @Override
    public Lugar encontrar(Lugar lugar) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, lugar.getIdLugar());
            rs = stmt.executeQuery();
            rs.next();
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String portada = rs.getString("portada");
            String foto1 = rs.getString("foto1");
            String foto2 = rs.getString("foto2");
            String foto3 = rs.getString("foto3");

            lugar.setNombre(nombre);
            lugar.setDescripcion(descripcion);
            lugar.setPortada(portada);
            lugar.setFoto1(foto1);
            lugar.setFoto2(foto2);
            lugar.setFoto3(foto3);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return lugar;
    }
}
