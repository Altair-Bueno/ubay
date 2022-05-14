package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uma.taw.ubay.service.products.ProductService;

import java.io.IOException;

@WebServlet("/product/update")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Update extends HttpServlet {
    @EJB
    ProductService productService;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoria = Integer.parseInt(req.getParameter("categoria"));
        int id = Integer.parseInt(req.getParameter("id"));

        String estado = req.getParameter("estado");
        String desc = req.getParameter("descripcion");
        String titulo = req.getParameter("titulo");
        Double precio = Double.parseDouble(req.getParameter("precio"));
        Part file = req.getPart("img");

        productService.updateProduct(id, categoria, estado, desc, titulo, precio, file);

        req.getRequestDispatcher("/product/item?id=" + id).forward(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", productService.findProduct(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("cats", productService.categories());
        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }
}
