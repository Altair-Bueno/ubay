package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uma.taw.ubay.SessionKeys;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.MinioFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.products.ProductCategoryDTO;
import uma.taw.ubay.dto.products.ProductClientDTO;
import uma.taw.ubay.dto.products.ProductDTO;
import uma.taw.ubay.service.products.ProductService;

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

    @EJB
    ProductService productService;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new.jsp").forward(req,resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductCategoryDTO> cats = productService.categories();
        var loginDTO = (LoginDTO) req.getSession().getAttribute(SessionKeys.LOGIN_DTO);
        ProductClientDTO cliente = productService.loginDTOtoClientDTO(loginDTO);

        req.setAttribute("cats", cats);
        req.setAttribute("user", cliente);
        req.getRequestDispatcher("new.jsp").forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("categoria"));


        String vendorIdParameter = req.getParameter("vendor");
        if(vendorIdParameter == null) throw new RuntimeException("ERROR: Intentelo de nuevo.");
        int vendorId = Integer.parseInt(vendorIdParameter);
        String description = req.getParameter("description");
        String title = req.getParameter("titulo");
        Double outprice  = Double.parseDouble(req.getParameter("precio"));
        Part file = req.getPart("img");
        String img = "";

        // IMAGEN
        if(!file.getSubmittedFileName().equals("")){
            InputStream inputStream = file.getInputStream();


            try {
                img = minioFacade.uploadObject(inputStream);
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }
        }

        ProductDTO p = productService.createProduct(
                title,
                description,
                outprice,
                img,
                new Timestamp(new Date().getTime()),
                vendorId,
                categoryId
        );

        req.getRequestDispatcher("/product/item?id=" + p.getId()).forward(req, resp);
    }
}
