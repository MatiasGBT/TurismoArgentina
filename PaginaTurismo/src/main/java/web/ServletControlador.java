
package web;

import datos.*;
import dominio.Lugar;
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
    
    private final ILugarDao datos;
    
    public ServletControlador() {
        this.datos=new LugarDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion=req.getParameter("accion");
        if(accion!=null) {
            switch (accion){
                default:
                    this.accionDefault(req, resp);
            }
        } else {
            this.accionDefault(req, resp);
        }
    }
    
    private void accionDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lugar> lugares=datos.listar();
        HttpSession sesion=req.getSession();
        sesion.setAttribute("lugares", lugares);
        resp.sendRedirect("principal.jsp");
    }
}
