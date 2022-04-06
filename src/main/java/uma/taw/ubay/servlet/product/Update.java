package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.dao.ProductFacade;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/product/update")
public class Update extends HttpServlet {
    @EJB
    ProductFacade facade;

    @EJB
    CategoryFacade catfacade;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String estado = req.getParameter("estado");
        String desc = req.getParameter("description");
        Double precio = Double.parseDouble(req.getParameter("precio"));
        CategoryEntity cat = catfacade.find(req.getParameter("categoria"));

        ProductEntity p = facade.find(Integer.parseInt(req.getParameter("id")));

        if(estado == "Cerrado"){
            if(p.getCloseDate() == null){
                p.setCloseDate(new Date(new java.util.Date().getTime()));
            }
        } else if(estado == "Activo") {
            if(p.getCloseDate() != null){
                p.setCloseDate(null);
            }
        }

        p.setDescription(desc);
        p.setCategory(cat);
        p.setOutPrice(precio);



    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductEntity p = facade.find(Integer.parseInt(req.getParameter("id")));
        List<CategoryEntity> cats = catfacade.findAll();
        req.setAttribute("product", p);
        req.setAttribute("cats", cats);
        req.getRequestDispatcher("update.jsp").forward(req,resp);
    }
}
