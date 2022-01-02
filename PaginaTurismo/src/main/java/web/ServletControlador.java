package web;

import datos.*;
import dominio.*;
import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
@MultipartConfig
public class ServletControlador extends HttpServlet {

    private final ILugarDao datosL;
    private final IActividadDao datosA;
    private final IUsuarioDao datosU;

    public ServletControlador() {
        this.datosL = new LugarDao();
        this.datosA = new ActividadDao();
        this.datosU = new UsuarioDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "mostrar":
                    this.mostrarLugar(req, resp);
                    break;
                case "listar":
                    this.mostrarActividades(req, resp);
                    break;
                case "sesion":
                    this.mostrarInicioSesion(req, resp);
                    break;
                case "registrarse":
                    this.mostrarRegistro(req, resp);
                    break;
                case "cerrar":
                    this.cerrarSesion(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
    }

    private void accionDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lugar> lugares = datosL.listar();
        List<Actividad> actividades = datosA.generar();
        HttpSession sesion = req.getSession();
        sesion.setAttribute("lugares", lugares);
        sesion.setAttribute("actividades", actividades);
        resp.sendRedirect("principal.jsp");
    }

    private void mostrarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));
        Lugar lugar = new Lugar(idLugar);
        lugar = datosL.encontrar(lugar);
        req.setAttribute("lugar", lugar);
        String jspBusqueda = "/WEB-INF/paginas/detalles/mostrarLugar.jsp";
        req.getRequestDispatcher(jspBusqueda).forward(req, resp);
    }

    private void mostrarActividades(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Actividad> actividades = datosA.listar();
        req.setAttribute("actividades", actividades);
        String jspBusqueda = "/WEB-INF/paginas/actividades/mostrarActividades.jsp";
        req.getRequestDispatcher(jspBusqueda).forward(req, resp);
    }

    private void mostrarInicioSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspBusqueda = "WEB-INF/paginas/sesion/sesion.jsp";
        req.getRequestDispatcher(jspBusqueda).forward(req, resp);
    }

    private void mostrarRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspBusqueda = "WEB-INF/paginas/sesion/registrarse.jsp";
        req.getRequestDispatcher(jspBusqueda).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "verificar":
                    this.verificar(req, resp);
                    break;
                case "registrarse":
                    this.registrarse(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
    }

    private void verificar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setNombre(req.getParameter("usuario"));
        usuario.setContraseña(req.getParameter("password"));
        usuario = datosU.identificar(usuario);

        if (usuario != null && usuario.getCargo().getNombre().equals("ADMINISTRADOR")) {
            HttpSession sesion = req.getSession();
            sesion.setAttribute("administrador", usuario);
            this.actualizarPanel(req, resp);
        } else if (usuario != null && usuario.getCargo().getNombre().equals("VISITANTE")) {
            HttpSession sesion = req.getSession();
            sesion.setAttribute("visitante", usuario);
            this.accionDefault(req, resp);
        } else {
            req.setAttribute("mensaje", "Credenciales incorrectas.");
            String jspBusqueda = "WEB-INF/paginas/sesion/sesion.jsp";
            req.getRequestDispatcher(jspBusqueda).forward(req, resp);
        }
    }

    private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sesion = req.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        resp.sendRedirect("index.jsp");
    }

    private void registrarse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();
        String contraseña = req.getParameter("password");
        String confirmarContraseña = req.getParameter("confpassword");
        if (contraseña.equals(confirmarContraseña)) {
            String nombre = req.getParameter("usuario");
            String email = req.getParameter("email");

            Usuario usuario = new Usuario(nombre, email, contraseña);

            int registrosModificados = datosU.insertar(usuario);
            System.out.println("Registros modificados totales: " + registrosModificados);

            sesion.setAttribute("visitante", usuario);
            this.accionDefault(req, resp);
        }
    }

    private void actualizarPanel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();
        List<Usuario> usuarios = datosU.listar();
        List<Lugar> lugares = datosL.listar();
        List<Actividad> actividades = datosA.listar();
        sesion.setAttribute("lugares", lugares);
        sesion.setAttribute("actividades", actividades);
        sesion.setAttribute("totalUsuarios", usuarios.size());
        sesion.setAttribute("totalLugares", lugares.size());
        sesion.setAttribute("totalActividades", actividades.size());
        String jspBusqueda = "WEB-INF/paginas/sesion/panel.jsp";
        req.getRequestDispatcher(jspBusqueda).forward(req, resp);
    }
}
