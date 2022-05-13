package uma.taw.ubay.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.MinioFacade;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Altair Bueno
 */

@WebServlet("/image")
public class Image extends HttpServlet {
    @EJB
    MinioFacade facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InputStream object = facade.getObject(req.getParameter("id"));
            ServletOutputStream outputStream = resp.getOutputStream();
            object.transferTo(outputStream);
            object.close();
            outputStream.close();
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}
