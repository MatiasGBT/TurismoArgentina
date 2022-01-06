package datos;

import dominio.*;
import java.sql.*;
import java.util.*;

public class ContactoDao implements IContactoDao {
    
    private static final String SQL_SELECT = "SELECT idcontacto, nombre, email, comentario"
            + " FROM contacto";
    
    private static final String SQL_SELECT_BY_ID = "SELECT idcontacto, nombre, email, comentario"
            + " FROM contacto WHERE idcontacto=?";
    
    private static final String SQL_INSERT = "INSERT INTO contacto(nombre, email, comentario)"
            + " VALUES(?, ?, ?)";
    
    private static final String SQL_DELETE = "DELETE FROM contacto WHERE idcontacto=?";
    
    @Override
    public List<Contacto> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Contacto contacto;
        List<Contacto> contactos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idContacto = rs.getInt("idcontacto");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String comentario = rs.getString("comentario");

                contacto = new Contacto(idContacto, nombre, email, comentario);
                
                contactos.add(contacto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return contactos;
    }
    
    @Override
    public Contacto encontrar(Contacto contacto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, contacto.getIdContacto());
            rs = stmt.executeQuery();
            rs.next();
            String nombre = rs.getString("nombre");
            String email = rs.getString("email");
            String comentario = rs.getString("comentario");

            contacto.setNombre(nombre);
            contacto.setEmail(email);
            contacto.setComentario(comentario);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return contacto;
    }
    
    @Override
    public int insertar(Contacto contacto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int cant = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, contacto.getNombre());
            stmt.setString(2, contacto.getEmail());
            stmt.setString(3, contacto.getComentario());

            cant = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cant;
    }
    
    @Override
    public void eliminar(Contacto contacto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, contacto.getIdContacto());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}