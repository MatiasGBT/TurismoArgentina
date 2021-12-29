package web;

import datos.ILugarDao;
import datos.LugarDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletImagen")
public class ServletImagen extends HttpServlet {

    private final ILugarDao datosL;

    public ServletImagen() {
        this.datosL = new LugarDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        int id;
        if (accion != null) {
            switch (accion) {
                case "listarPortada":
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.listarPortada(id, resp);
                    break;
                case "listarFoto1":
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.listarFoto1(id, resp);
                    break;
                case "listarFoto2":
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.listarFoto2(id, resp);
                    break;
                case "listarFoto3":
                    id = Integer.parseInt(req.getParameter("id"));
                    datosL.listarFoto3(id, resp);
                    break;
                default:
                    break;
            }
        }
    }
}
