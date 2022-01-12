package datos;

import dominio.Lugar;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class LugarDao implements ILugarDao {

    private static final String SQL_SELECT = "SELECT idlugar, nombre, descripcion, portada, foto1, foto2, foto3, precio"
            + " FROM lugar";

    private static final String SQL_SELECT_BY_ID = "SELECT idlugar, nombre, descripcion, portada, foto1, foto2, foto3, precio"
            + " FROM lugar WHERE idlugar = ?";

    private static final String SQL_INSERT = "INSERT INTO lugar(nombre, descripcion, portada, foto1, foto2, foto3, precio)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE lugar"
            + " SET nombre=?, descripcion=?, portada=?, foto1=?, foto2=?, foto3=?, precio=? WHERE idlugar=?";

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
                InputStream portada = rs.getBinaryStream("portada");
                InputStream foto1 = rs.getBinaryStream("foto1");
                InputStream foto2 = rs.getBinaryStream("foto2");
                InputStream foto3 = rs.getBinaryStream("foto3");
                double precio = rs.getDouble("precio");

                lugar = new Lugar(idLugar, nombre, descripcion, portada, foto1, foto2, foto3, precio);
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
            InputStream portada = rs.getBinaryStream("portada");
            InputStream foto1 = rs.getBinaryStream("foto1");
            InputStream foto2 = rs.getBinaryStream("foto2");
            InputStream foto3 = rs.getBinaryStream("foto3");
            double precio = rs.getDouble("precio");

            lugar.setNombre(nombre);
            lugar.setDescripcion(descripcion);
            lugar.setPortada(portada);
            lugar.setFoto1(foto1);
            lugar.setFoto2(foto2);
            lugar.setFoto3(foto3);
            lugar.setPrecio(precio);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return lugar;
    }
    
    @Override
    public void insertar(Lugar lugar) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, lugar.getNombre());
            stmt.setString(2, lugar.getDescripcion());
            stmt.setBlob(3, lugar.getPortada());
            stmt.setBlob(4, lugar.getFoto1());
            stmt.setBlob(5, lugar.getFoto2());
            stmt.setBlob(6, lugar.getFoto3());
            stmt.setDouble(7, lugar.getPrecio());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    
    @Override
    public void actualizar(Lugar lugar) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, lugar.getNombre());
            stmt.setString(2, lugar.getDescripcion());
            stmt.setBlob(3, lugar.getPortada());
            stmt.setBlob(4, lugar.getFoto1());
            stmt.setBlob(5, lugar.getFoto2());
            stmt.setBlob(6, lugar.getFoto3());
            stmt.setDouble(7, lugar.getPrecio());
            stmt.setInt(8, lugar.getIdLugar());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
    
    @Override
    public void eliminar(Lugar lugar) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, lugar.getIdLugar());
            
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
    public void escribirImagen(int id, HttpServletResponse resp, int num) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        resp.setContentType("image/*");
        try {
            outputStream=resp.getOutputStream();
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                switch (num) {
                    case 1:
                        inputStream=rs.getBinaryStream("portada");
                        break;
                    case 2:
                        inputStream=rs.getBinaryStream("foto1");
                        break;
                    case 3:
                        inputStream=rs.getBinaryStream("foto2");
                        break;
                    case 4:
                        inputStream=rs.getBinaryStream("foto3");
                        break;
                }
            }
            
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream= new BufferedOutputStream(outputStream);
            int i;
            while((i=bufferedInputStream.read())!=-1) {
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
