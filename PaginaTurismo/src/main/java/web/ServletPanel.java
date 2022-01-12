package web;

import datos.*;
import dominio.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/ServletPanel")
@MultipartConfig
public class ServletPanel extends HttpServlet {

    private final ILugarDao datosL;
    private final IActividadDao datosA;
    private final IUsuarioDao datosU;
    private final IContactoDao datosC;

    public ServletPanel() {
        this.datosL = new LugarDao();
        this.datosA = new ActividadDao();
        this.datosU = new UsuarioDao();
        this.datosC = new ContactoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                //Opciones de sitios turísticos:
                case "editarLugar":
                    this.editarLugar(req, resp);
                    break;
                case "agregarLugar":
                    this.agregarLugar(req, resp);
                    break;
                case "eliminarLugar":
                    this.eliminarLugar(req, resp);
                    break;
                //Opciones de actividades turísticas
                case "mostrarActividad":
                    this.mostrarActividad(req, resp);
                    break;
                case "editarActividad":
                    this.editarActividad(req, resp);
                    break;
                case "agregarActividad":
                    this.agregarActividad(req, resp);
                    break;
                case "eliminarActividad":
                    this.eliminarActividad(req, resp);
                    break;
                //Opciones de contacto
                case "mostrarContacto":
                    this.mostrarContacto(req, resp);
                    break;
                case "eliminarContacto":
                    this.eliminarContacto(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
    }

    private void accionDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();

        List<Usuario> usuarios = datosU.listar();
        List<Lugar> lugares = datosL.listar();
        List<Actividad> actividades = datosA.listar();
        List<Contacto> contactos = datosC.listar();

        sesion.setAttribute("lugares", lugares);
        sesion.setAttribute("actividades", actividades);
        sesion.setAttribute("contactos", contactos);
        sesion.setAttribute("totalUsuarios", usuarios.size());
        sesion.setAttribute("totalLugares", lugares.size());
        sesion.setAttribute("totalActividades", actividades.size());
        req.getRequestDispatcher("WEB-INF/paginas/sesion/panel.jsp").forward(req, resp);
    }

    //  ---  METODOS DOGET DE SITIOS ---  
    private void editarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));
        Lugar lugar = datosL.encontrar(new Lugar(idLugar));
        req.setAttribute("lugar", lugar);
        req.getRequestDispatcher("WEB-INF/paginas/sesion/lugar/editarLugar.jsp").forward(req, resp);
    }

    private void agregarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/paginas/sesion/lugar/agregarLugar.jsp").forward(req, resp);
    }

    private void eliminarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));
        Lugar lugar = new Lugar(idLugar);
        datosL.eliminar(lugar);
        this.accionDefault(req, resp);
    }

    //  ---  METODOS DOGET DE ACTIVIDADES ---  
    private void mostrarActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        Actividad actividad = datosA.encontrar(new Actividad(idActividad));
        req.setAttribute("actividad", actividad);
        req.getRequestDispatcher("WEB-INF/paginas/sesion/actividad/mostrarActividad.jsp").forward(req, resp);
    }

    private void editarActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        Actividad actividad = datosA.encontrar(new Actividad(idActividad));
        req.setAttribute("actividad", actividad);
        req.getRequestDispatcher("WEB-INF/paginas/sesion/actividad/editarActividad.jsp").forward(req, resp);
    }

    private void agregarActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/paginas/sesion/actividad/agregarActividad.jsp").forward(req, resp);
    }

    private void eliminarActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        Actividad actividad = new Actividad(idActividad);
        datosA.eliminar(actividad);
        this.accionDefault(req, resp);
    }

    //  ---  METODOS DOGET DE CONTACTO ---  
    private void mostrarContacto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idContacto = Integer.parseInt(req.getParameter("idContacto"));
        Contacto contacto = datosC.encontrar(new Contacto(idContacto));
        req.setAttribute("contacto", contacto);
        req.getRequestDispatcher("WEB-INF/paginas/sesion/contacto/mostrarContacto.jsp").forward(req, resp);
    }
    
    private void eliminarContacto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idContacto = Integer.parseInt(req.getParameter("idContacto"));
        Contacto contacto = new Contacto(idContacto);
        datosC.eliminar(contacto);
        this.accionDefault(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                //Opciones de sitios turísticos:
                case "modificarLugar":
                    this.actualizarLugar(req, resp);
                    break;
                case "insertarLugar":
                    this.insertarLugar(req, resp);
                    break;
                //Opciones de actividades turísticas
                case "modificarActividad":
                    this.actualizarActividad(req, resp);
                    break;
                case "insertarActividad":
                    this.insertarActividad(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
    }

    //  ---  METODOS DOPOST DE SITIOS ---  
    private void actualizarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperación valores del formulario:
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        Part portada = req.getPart("portada");
        InputStream inputPortada = portada.getInputStream();

        Part foto1 = req.getPart("foto1");
        InputStream inputFoto1 = foto1.getInputStream();

        Part foto2 = req.getPart("foto2");
        InputStream inputFoto2 = foto2.getInputStream();

        Part foto3 = req.getPart("foto3");
        InputStream inputFoto3 = foto3.getInputStream();

        double precio = 0;
        String precioString = req.getParameter("precio");
        if (precioString != null && !precioString.equals("")) {
            precio = Double.parseDouble(precioString);
        }

        //Creación de objeto lugar
        Lugar lugar = new Lugar(idLugar, nombre, descripcion, inputPortada, inputFoto1, inputFoto2, inputFoto3, precio);

        //Modificar el lugar en la base de datos
        datosL.actualizar(lugar);

        //Redirección hacia el panel de nuevo (para que se refresque la info).
        this.accionDefault(req, resp);
    }

    private void insertarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperación valores del formulario:
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        Part portada = req.getPart("portada");
        InputStream inputPortada = portada.getInputStream();

        Part foto1 = req.getPart("foto1");
        InputStream inputFoto1 = foto1.getInputStream();

        Part foto2 = req.getPart("foto2");
        InputStream inputFoto2 = foto2.getInputStream();

        Part foto3 = req.getPart("foto3");
        InputStream inputFoto3 = foto3.getInputStream();

        double precio = 0;
        String precioString = req.getParameter("precio");
        if (precioString != null && !precioString.equals("")) {
            precio = Double.parseDouble(precioString);
        }

        //Creación de objeto lugar
        Lugar lugar = new Lugar(nombre, descripcion, inputPortada, inputFoto1, inputFoto2, inputFoto3, precio);

        //Modificar el lugar en la base de datos
        datosL.insertar(lugar);

        //Redirección hacia el panel de nuevo (para que se refresque la info).
        this.accionDefault(req, resp);
    }

    //  ---  METODOS DOPOST DE ACTIVIDADES ---  
    private void actualizarActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperación valores del formulario:
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        String nombre = req.getParameter("nombre");

        Part imagen = req.getPart("imagen");
        InputStream inputImagen = imagen.getInputStream();

        double precio = 0;
        String precioString = req.getParameter("precio");
        if (precioString != null && !precioString.equals("")) {
            precio = Double.parseDouble(precioString);
        }

        //Creación de objeto actividad
        Actividad actividad = new Actividad(idActividad, nombre, inputImagen, precio);

        //Modificar la actividad en la base de datos
        datosA.actualizar(actividad);

        //Redirección hacia el panel de nuevo (para que se refresque la info).
        this.accionDefault(req, resp);
    }

    private void insertarActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperación valores del formulario:
        String nombre = req.getParameter("nombre");

        Part imagen = req.getPart("imagen");
        InputStream inputImagen = imagen.getInputStream();

        double precio = 0;
        String precioString = req.getParameter("precio");
        if (precioString != null && !precioString.equals("")) {
            precio = Double.parseDouble(precioString);
        }

        //Creación de objeto actividad
        Actividad actividad = new Actividad(nombre, inputImagen, precio);

        //Modificar la actividad en la base de datos
        datosA.insertar(actividad);

        //Redirección hacia el panel de nuevo (para que se refresque la info).
        this.accionDefault(req, resp);
    }
}
