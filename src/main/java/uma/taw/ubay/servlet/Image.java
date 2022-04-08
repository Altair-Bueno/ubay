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

@WebServlet("/image")
/*
// Required for sample code
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
*/
public class Image extends HttpServlet {
    @EJB
    MinioFacade facade;
    /*
    // Sample jsp form
        <form method="post" action="${pageContext.request.contextPath}/image" enctype="multipart/form-data">
            <input type="file" name="file" />
            <input type="submit" value="Upload" />
        </form>
    // Sample code for file upload
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part file = req.getPart("file");
        InputStream inputStream = file.getInputStream();
        try {
            String s = facade.uploadObject(inputStream);
            resp.sendRedirect(req.getContextPath() + "/test.jsp?id=" + s);
        } catch (Exception e) {
            throw new ServletException(e.getStackTrace());
        }
    }
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InputStream object = facade.getObject(req.getParameter("id"));
            ServletOutputStream outputStream = resp.getOutputStream();
            object.transferTo(outputStream);
        } catch (Exception e) {
            throw new ServletException(e.getMessage(),e);
        }
    }
}
