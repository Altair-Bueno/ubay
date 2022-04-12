package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.MinioFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet("/product/new")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class New extends HttpServlet {
    @EJB
    ProductFacade facade;

    @EJB
    ClientFacade clientFacade;

    @EJB
    MinioFacade minioFacade;

    @EJB
    CategoryFacade catfacade;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new.jsp").forward(req,resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryEntity> cats = catfacade.findAll();
        req.setAttribute("cats", cats);
        req.getRequestDispatcher("new.jsp").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoria = Integer.parseInt(req.getParameter("categoria"));
        CategoryEntity cat = catfacade.find(categoria);
        ProductEntity p = new ProductEntity();

        String vendorIdParameter = req.getParameter("vendor");
        if(vendorIdParameter == null) throw new RuntimeException("ERROR: Intentelo de nuevo.");
        int vendorId = Integer.parseInt(vendorIdParameter);
        String desc = req.getParameter("description");
        String titulo = req.getParameter("titulo");
        Double precio = Double.parseDouble(req.getParameter("precio"));
        Part file = req.getPart("img");

        // IMAGEN
        if(!file.getSubmittedFileName().equals("")){
            InputStream inputStream = file.getInputStream();
            String img = "";

            try {
                img = minioFacade.uploadObject(inputStream);
                minioFacade.removeObject(p.getImages());
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }

            p.setImages(img);
        }


        p.setTitle(titulo);
        p.setDescription(desc);
        p.setCategory(cat);
        p.setOutPrice(precio);
        p.setVendor(clientFacade.find(vendorId));
        p.setPublishDate(new Timestamp(new Date().getTime()));

        facade.create(p);

        req.getRequestDispatcher("/product/item?id=" + p.getId()).forward(req, resp);
    }
}
