package datos;

import dominio.Actividad;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class ActividadDao implements IActividadDao {

    private static final String SQL_SELECT = "SELECT idactividad, nombre, imagen, precio"
            + " FROM actividad";

    private static final String SQL_SELECT_BY_ID = "SELECT idactividad, nombre, imagen, precio"
            + " FROM actividad WHERE idactividad = ?";
    
    private static final String SQL_SELECT_BY_RANDOM_ID = "SELECT idactividad, nombre, imagen, precio"
            + " FROM actividad WHERE idactividad IN(?,?,?)";

    private static final String SQL_INSERT = "INSERT INTO actividad(nombre, imagen, precio)"
            + " VALUES(?, ?, ?)";

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
                InputStream imagen = rs.getBinaryStream("imagen");
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
        int n1;
        int n2;
        int n3;
        do {
            n1 = (int) (Math.random() * listar().size() + 1);
            n2 = (int) (Math.random() * listar().size() + 1);
            n3 = (int) (Math.random() * listar().size() + 1);
        } while (n1 == n2 || n2 == n3 || n1==n3);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Actividad actividad;
        List<Actividad> actividades = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_RANDOM_ID);
            stmt.setInt(1, n1);
            stmt.setInt(2, n2);
            stmt.setInt(3, n3);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idActividad = rs.getInt("idactividad");
                String nombre = rs.getString("nombre");
                InputStream imagen = rs.getBinaryStream("imagen");
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
    public Actividad encontrar(Actividad actividad) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, actividad.getIdActividad());
            rs = stmt.executeQuery();
            rs.next();
            String nombre = rs.getString("nombre");
            InputStream imagen = rs.getBinaryStream("imagen");
            double precio = rs.getDouble("precio");

            actividad.setNombre(nombre);
            actividad.setImagen(imagen);
            actividad.setPrecio(precio);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return actividad;
    }
    
    @Override
    public void insertar(Actividad actividad) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, actividad.getNombre());
            stmt.setBlob(2, actividad.getImagen());
            stmt.setDouble(3, actividad.getPrecio());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    
    @Override
    public void actualizar(Actividad actividad) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, actividad.getNombre());
            stmt.setBlob(2, actividad.getImagen());
            stmt.setDouble(3, actividad.getPrecio());
            stmt.setInt(4, actividad.getIdActividad());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    
    @Override
    public void eliminar(Actividad actividad) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, actividad.getIdActividad());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    
    //Método para convertir los bytes en una imagen
    @Override
    public void escribirImagen(int id, HttpServletResponse resp) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        resp.setContentType("image/*");
        try {
            outputStream = resp.getOutputStream();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("imagen");
            }

            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                bufferedOutputStream.close();
                bufferedInputStream.close();
                outputStream.close();
                inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
