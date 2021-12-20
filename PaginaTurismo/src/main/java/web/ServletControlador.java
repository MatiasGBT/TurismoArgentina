
package web;

import datos.*;
import dominio.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    
    private final ILugarDao datosL;
    private final IActividadDao datosA;
    
    public ServletControlador() {
        this.datosL=new LugarDao();
        this.datosA=new ActividadDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion=req.getParameter("accion");
        if(accion!=null) {
            switch (accion){
                case "encontrar":
                    this.mostrarLugar(req, resp);
                    break;
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
    }
    
    private void accionDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lugar> lugares=datosL.listar();
        List<Actividad> actividades=datosA.listar();
        HttpSession sesion1=req.getSession();
        HttpSession sesion2=req.getSession();
        sesion1.setAttribute("lugares", lugares);
        sesion2.setAttribute("actividades", actividades);
        resp.sendRedirect("principal.jsp");
    }
    
    private void mostrarLugar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idLugar=Integer.parseInt(req.getParameter("idLugar"));
        Lugar lugar=new Lugar(idLugar);
        Lugar lugarFinal=datosL.encontrar(lugar);
        req.setAttribute("lugar", lugarFinal);
        String jspBusqueda="/WEB-INF/paginas/detalles/mostrarLugar.jsp";
        req.getRequestDispatcher(jspBusqueda).forward(req, resp);
    }
}
