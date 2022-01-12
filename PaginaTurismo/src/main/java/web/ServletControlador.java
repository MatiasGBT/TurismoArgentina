package web;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import datos.*;
import dominio.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
@MultipartConfig
public class ServletControlador extends HttpServlet {

    private final ILugarDao datosL;
    private final IActividadDao datosA;
    private final IUsuarioDao datosU;
    private final IContactoDao datosC;

    public ServletControlador() {
        this.datosL = new LugarDao();
        this.datosA = new ActividadDao();
        this.datosU = new UsuarioDao();
        this.datosC = new ContactoDao();
    }

    //  ---  METODO DOGET Y TODOS LOS METODOS DE SUS ACCIONES  ---  
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
                case "formularioLugar":
                    this.mostrarFormularioLugar(req, resp);
                    break;
                case "formularioActividad":
                    this.mostrarFormularioActividad(req, resp);
                    break;
                case "carrito":
                    this.mostrarCarrito(req, resp);
                    break;
                case "quitarLugarCarrito":
                    this.quitarLugarCarrito(req, resp);
                    break;
                case "quitarActividadCarrito":
                    this.quitarActividadCarrito(req, resp);
                    break;
                case "descargarFactura":
                    this.descargarFactura(req, resp);
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
        sesion.setAttribute("mensajeContacto", req.getAttribute("mensajeContacto"));
        resp.sendRedirect("principal.jsp");
    }

    private void mostrarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));
        Lugar lugar = datosL.encontrar(new Lugar(idLugar));
        req.setAttribute("lugar", lugar);
        req.getRequestDispatcher("/WEB-INF/paginas/detalles/mostrarLugar.jsp").forward(req, resp);
    }

    private void mostrarActividades(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Actividad> actividades = datosA.listar();
        req.setAttribute("actividades", actividades);
        req.getRequestDispatcher("WEB-INF/paginas/actividades/mostrarActividades.jsp").forward(req, resp);
    }

    private void mostrarInicioSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/paginas/sesion/sesion.jsp").forward(req, resp);
    }

    private void mostrarRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/paginas/sesion/registrarse.jsp").forward(req, resp);
    }

    private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sesion = req.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        resp.sendRedirect(""); //Para que vuelva a la página index
    }

    private void mostrarFormularioLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));
        Lugar lugar = datosL.encontrar(new Lugar(idLugar));
        HttpSession sesion = req.getSession();
        sesion.setAttribute("lugar", lugar);
        req.getRequestDispatcher("WEB-INF/paginas/formulariosCarrito/formularioLugar.jsp").forward(req, resp);
    }

    private void mostrarFormularioActividad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        Actividad actividad = datosA.encontrar(new Actividad(idActividad));
        HttpSession sesion = req.getSession();
        sesion.setAttribute("actividad", actividad);
        req.getRequestDispatcher("WEB-INF/paginas/formulariosCarrito/formularioActividad.jsp").forward(req, resp);
    }

    private void mostrarCarrito(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession sesion = req.getSession();
        List<Lugar> lugares = (List<Lugar>) sesion.getAttribute("lugaresCarrito");
        List<Actividad> actividades = (List<Actividad>) sesion.getAttribute("actividadesCarrito");

        if (lugares == null) {
            //Se inicializa la lista si no existe.
            lugares = new ArrayList<>();
            sesion.setAttribute("lugaresCarrito", lugares);
        }

        if (actividades == null) {
            //Se inicializa la lista si no existe.
            actividades = new ArrayList<>();
            sesion.setAttribute("actividadesCarrito", actividades);
        }

        //Algoritmo para verificar si hay alguna actividad agregada al carrito la cual no posea su sitio turístico definido.
        boolean existe = false;
        if (!lugares.isEmpty() && !actividades.isEmpty()) {
            for (Actividad a : actividades) {
                for (Lugar l : lugares) {
                    if (!a.getNombre().contains("(" + l.getNombre() + ")")) {
                        existe = true;
                    } else {
                        existe = false;
                        break;
                    }
                }
                if (existe) {
                    break;
                }
            }
            if (existe) {
                req.setAttribute("mensaje", "Advertencia: alguna/s de las actividades seleccionadas no corresponden a los lugares seleccionados en el carrito.");
            }
        }

        req.setAttribute("total", this.calcularTotal(lugares, actividades));

        req.getRequestDispatcher("WEB-INF/paginas/index/carrito.jsp").forward(req, resp);
    }

    private void quitarLugarCarrito(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession sesion = req.getSession();
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));

        Lugar lugar = new Lugar(idLugar);

        List<Lugar> lugares = (List<Lugar>) sesion.getAttribute("lugaresCarrito");
        lugares.removeIf(l -> l.getIdLugar() == lugar.getIdLugar());

        sesion.setAttribute("lugaresCarrito", lugares);
        this.mostrarCarrito(req, resp);
    }

    private void quitarActividadCarrito(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession sesion = req.getSession();
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));

        Actividad actividad = new Actividad(idActividad);

        List<Actividad> actividades = (List<Actividad>) sesion.getAttribute("actividadesCarrito");
        actividades.removeIf(a -> a.getIdActividad() == actividad.getIdActividad());

        sesion.setAttribute("actividadesCarrito", actividades);
        this.mostrarCarrito(req, resp);
    }

    private void descargarFactura(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession sesion = req.getSession();

        List<Lugar> lugares = (List<Lugar>) sesion.getAttribute("lugaresCarrito");
        List<Actividad> actividades = (List<Actividad>) sesion.getAttribute("actividadesCarrito");

        if (!lugares.isEmpty() || !actividades.isEmpty()) {
            resp.setContentType("application/pdf");

            PdfWriter writer = new PdfWriter(resp.getOutputStream());
            PdfDocument pdf = new PdfDocument(writer);
            try (Document documento = new Document(pdf)) {
                documento.setMargins(100, 100, 100, 100);

                documento.add(new Paragraph("Factura Turismo Argentina"));

                documento.add(new Paragraph(""));

                documento.add(new Paragraph("Sitios/lugares turísticos:"));
                lugares.forEach(l -> {
                    documento.add(new Paragraph(l.getNombre() + " ARS$" + l.getPrecio()));
                });

                documento.add(new Paragraph(""));

                documento.add(new Paragraph("Actividades turísticas:"));
                actividades.forEach(a -> {
                    documento.add(new Paragraph(a.getNombre() + " ARS$" + a.getPrecio()));
                });

                documento.add(new Paragraph(""));

                documento.add(new Paragraph("Total: ARS$" + this.calcularTotal(lugares, actividades)));
            }
        } else {
            String mensaje = "Advertencia: el carrito de compras esta vacío.";
            req.setAttribute("mensaje", mensaje);
            this.mostrarCarrito(req, resp);
        }
    }

    //Algoritmo para calcular el precio total de todos los productos en el carrito.
    private double calcularTotal(List<Lugar> lugares, List<Actividad> actividades) {
        double total = 0;
        for (Lugar l : lugares) {
            total = total + l.getPrecio();
        }
        for (Actividad a : actividades) {
            total = total + a.getPrecio();
        }
        return total;
    }

    //  ---  METODO DOPOST Y TODOS LOS METODOS DE SUS ACCIONES  ---  
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
                case "enviarContacto":
                    this.insertarContacto(req, resp);
                    break;
                case "agregarLugarCarrito":
                    this.agregarLugarCarrito(req, resp);
                    break;
                case "agregarActividadCarrito":
                    this.agregarActividadCarrito(req, resp);
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
            req.setAttribute("mensaje", "Error: credenciales incorrectas");
            req.getRequestDispatcher("WEB-INF/paginas/sesion/sesion.jsp").forward(req, resp);
        }
    }

    private void registrarse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contraseña = req.getParameter("password");
        String confirmarContraseña = req.getParameter("confpassword");
        if (contraseña.equals(confirmarContraseña)) {
            String nombre = req.getParameter("usuario");
            String email = req.getParameter("email");

            Usuario usuario = new Usuario(nombre, email, contraseña);

            if (datosU.verificarNombre(usuario) || datosU.verificarEmail(usuario)) {
                req.setAttribute("mensaje", "Error: el nombre de usuario o el email ya estan registrados en la web");
                req.getRequestDispatcher("WEB-INF/paginas/sesion/registrarse.jsp").forward(req, resp);
            } else {
                HttpSession sesion = req.getSession();
                int registrosModificados = datosU.insertar(usuario);
                System.out.println("Registros modificados totales: " + registrosModificados);

                sesion.setAttribute("visitante", usuario);
                this.accionDefault(req, resp);
            }
        } else {
            req.setAttribute("mensaje", "Error: las contraseñas no coinciden");
            req.getRequestDispatcher("WEB-INF/paginas/sesion/registrarse.jsp").forward(req, resp);
        }
    }

    private void insertarContacto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();
        Object mensaje = sesion.getAttribute("mensajeContacto");
            
        if (mensaje == null) {
            //Recuperación valores del formulario:
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            String comentario = req.getParameter("comentario");

            //Creación del objeto contacto
            Contacto contacto = new Contacto(nombre, email, comentario);

            //Insertar el objeto en la base de datos
            datosC.insertar(contacto);

            mensaje = "Se ha enviado el comentario correctamente, ¡muchas gracias!";
            req.setAttribute("mensajeContacto", mensaje);

            //Redirección a la página principal
            this.accionDefault(req, resp);
        } else {
            mensaje = "Ya envío un comentario anteriormente, por favor espere unos minutos para volver a hacerlo.";
            req.setAttribute("mensajeContacto", mensaje);
            this.accionDefault(req, resp);
        }
    }

    private void actualizarPanel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void agregarLugarCarrito(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession sesion = req.getSession();
        int idLugar = Integer.parseInt(req.getParameter("idLugar"));
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));

        Lugar lugar = datosL.encontrar(new Lugar(idLugar));
        lugar.setPrecio(lugar.getPrecio() * cantidad);

        List<Lugar> lugares = (List<Lugar>) sesion.getAttribute("lugaresCarrito");
        if (lugares == null) {
            lugares = new ArrayList<>();
        }

        //Algoritmo para detectar si el lugar seleccionado ya esta en el carrito
        boolean existe = false;
        for (Lugar l : lugares) {
            if (l.getNombre().equals(lugar.getNombre())) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            lugares.add(lugar);
        } else {
            req.setAttribute("mensaje", "Advertencia: el lugar seleccionado ya se encuentra en el carrito, debe eliminarlo si quiere agregarlo de nuevo.");
        }

        sesion.setAttribute("lugaresCarrito", lugares);
        this.mostrarCarrito(req, resp);
    }

    private void agregarActividadCarrito(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession sesion = req.getSession();
        int idActividad = Integer.parseInt(req.getParameter("idActividad"));
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));

        Actividad actividad = datosA.encontrar(new Actividad(idActividad));
        actividad.setPrecio(actividad.getPrecio() * cantidad);

        List<Actividad> actividades = (List<Actividad>) sesion.getAttribute("actividadesCarrito");
        if (actividades == null) {
            actividades = new ArrayList<>();
        }

        //Algoritmo para detectar si la actividad seleccionada ya esta en el carrito
        boolean existe = false;
        for (Actividad a : actividades) {
            if (a.getNombre().equals(actividad.getNombre())) {
                existe = true;
            }
        }

        if (!existe) {
            actividades.add(actividad);
        } else {
            req.setAttribute("mensaje", "Advertencia: la actividad seleccionada ya se encuentra en el carrito, debe eliminarla si quiere agregarla de nuevo.");
        }

        sesion.setAttribute("actividadesCarrito", actividades);
        this.mostrarCarrito(req, resp);
    }
}