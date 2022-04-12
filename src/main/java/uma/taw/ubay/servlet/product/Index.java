package uma.taw.ubay.servlet.product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ProductFacade;

import java.io.IOException;

@WebServlet("/product")
public class Index extends HttpServlet {

    @EJB
    ProductFacade facade;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product-tam", facade.findAll().size());
        var page = req.getParameter("page");
        req.setAttribute("product-list", facade.getByPage(page == null ? 0 : Integer.parseInt(page) - 1));
        req.getRequestDispatcher("product/index.jsp").forward(req,resp);

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
}
