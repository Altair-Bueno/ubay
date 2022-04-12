package uma.taw.ubay.servlet.product;

import io.minio.errors.*;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.MinioFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/product/delete")
public class Delete extends HttpServlet {
    @EJB
    ProductFacade facade;

    @EJB
    MinioFacade minioFacade;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String idParam = req.getParameter("id");
        if(idParam == null) throw new RuntimeException("ERROR: Inténtelo de nuevo.");
        int id = Integer.parseInt(idParam);

        ProductEntity p = facade.find(id);

        if(p.getImages() != null){
            minioFacade.removeObject(p.getImages());
        }

        facade.remove(p);

        req.getRequestDispatcher("/product").forward(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            process(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("exception.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("exception.jsp").forward(req, resp);
        }
    }
}
