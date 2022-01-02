package datos;

import dominio.Cargo;
import dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements IUsuarioDao{
    
    private static final String SQL_SELECT = "SELECT idusuario, nombre, email, contraseña, cod_cargo"
            + " FROM usuario";
    
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, email, contraseña, cod_cargo)"
            + " VALUES(?, ?, ?, ?)";
    
    @Override
    public Usuario identificar(Usuario usuario) {
        
        String SQL="SELECT u.idusuario, c.nombre FROM usuario u "
            + "INNER JOIN cargo c ON u.idusuario=c.idcargo "
            + "WHERE u.nombre='" + usuario.getNombre() + "' "
            + "AND u.contraseña='"+ usuario.getContraseña() + "'";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuarioVerificado=new Usuario();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                usuarioVerificado=new Usuario();
                usuarioVerificado.setIdUsuario(rs.getInt("idusuario"));
                usuarioVerificado.setNombre(usuario.getNombre());
                usuarioVerificado.setCargo(new Cargo());
                usuarioVerificado.getCargo().setNombre(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarioVerificado;
    }
    
    @Override
    public int insertar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int cant = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getContraseña());
            stmt.setInt(4, 2);

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
    public List<Usuario> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idusuario");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contraseña = rs.getString("contraseña");
                int codCargo = rs.getInt("cod_cargo");

                usuario.setIdUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setEmail(email);
                usuario.setContraseña(contraseña);
                
                if(codCargo==1) {
                    usuario.setCargo(new Cargo());
                    usuario.getCargo().setNombre("ADMINISTRADOR");
                } else if(codCargo==2) {
                    usuario.setCargo(new Cargo());
                    usuario.getCargo().setNombre("VISITANTE");
                }
                
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }
}
