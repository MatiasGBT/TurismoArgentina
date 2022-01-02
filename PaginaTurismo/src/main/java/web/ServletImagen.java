package web;

import datos.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletImagen")
public class ServletImagen extends HttpServlet {

    private final ILugarDao datosL;
    private final IActividadDao datosA;

    public ServletImagen() {
        this.datosL = new LugarDao();
        this.datosA = new ActividadDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        int id;
        int num;
        if (accion != null) {
            switch (accion) {
                case "listarPortada":
                    num=1;
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.escribirImagen(id, resp, num);
                    break;
                case "listarFoto1":
                    num=2;
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.escribirImagen(id, resp, num);
                    break;
                case "listarFoto2":
                    num=3;
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.escribirImagen(id, resp, num);
                    break;
                case "listarFoto3":
                    num=4;
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.escribirImagen(id, resp, num);
                    break;
                case "listarActividad":
                    id = Integer.parseInt(req.getParameter("id"));
                    datosA.escribirImagen(id, resp);
                    break;
                default:
                    break;
            }
        }
    }
}
