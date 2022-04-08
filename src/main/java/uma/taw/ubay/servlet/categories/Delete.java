package uma.taw.ubay.servlet.categories;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.UbayException;
import uma.taw.ubay.dao.CategoryFacade;
import uma.taw.ubay.entity.CategoryEntity;
import java.io.IOException;

@WebServlet("/categories/delete")
public class Delete extends HttpServlet {
    @EJB
    CategoryFacade facade;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        if(id != null){
            try{
                CategoryEntity category = facade.find(Integer.parseInt(id));
                facade.remove(category);
            } catch (Exception e){
                throw new UbayException("Cannot delete a category that is being used by a product.");
            }
        }
        request.getRequestDispatcher("delete.jsp").forward(request,response);
    }
}
