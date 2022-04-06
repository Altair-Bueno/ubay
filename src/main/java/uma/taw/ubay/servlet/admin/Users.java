package uma.taw.ubay.servlet.admin;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uma.taw.ubay.dao.ClientFacade;
import uma.taw.ubay.dao.LoginCredentialsFacade;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.GenderEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/admin/users")
public class Users extends HttpServlet {
    @EJB
    ClientFacade facade;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request, response);}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {process(request,response);}

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String search = request.getParameter("search");
        String filter = request.getParameter("filterBy");
        String order = request.getParameter("order");
        List<ClientEntity> clientEntityList = facade.findAll();
        request.setAttribute("users-list",clientEntityList);
        try{
            if(!filter.equals("--") && search != null){
                switch(filter){
                    case "Name" : clientEntityList = facade.filterByName(search); break;
                    case "Address" : clientEntityList = facade.filterByAddress(search); break;
                    case "Gender" : clientEntityList = facade.filterByGender(GenderEnum.valueOf(search)); break;
                    case "City" : clientEntityList = facade.filterByCity(search); break;
                    case "ID" : clientEntityList = facade.filterByID(search); break;
                }
            }

            if(order.equals("Ascendente") && !filter.equals("--")){
                switch(filter) {
                    case "Name":
                        clientEntityList.sort(new Comparator<ClientEntity>() {
                            @Override
                            public int compare(ClientEntity o1, ClientEntity o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });

                    case "Address":
                        clientEntityList.sort(new Comparator<ClientEntity>() {
                            @Override
                            public int compare(ClientEntity o1, ClientEntity o2) {
                                return o1.getAddress().compareTo(o2.getAddress());
                            }
                        });

                    case "Gender":
                        clientEntityList.sort(new Comparator<ClientEntity>() {
                            @Override
                            public int compare(ClientEntity o1, ClientEntity o2) {
                                return o1.getGender().compareTo(o2.getGender());
                            }
                        });

                    case "City":
                        clientEntityList.sort(new Comparator<ClientEntity>() {
                            @Override
                            public int compare(ClientEntity o1, ClientEntity o2) {
                                return o1.getCity().compareTo(o2.getCity());
                            }
                        });

                    case "ID":
                        clientEntityList.sort(new Comparator<ClientEntity>() {
                            @Override
                            public int compare(ClientEntity o1, ClientEntity o2) {
                                return o1.getId() == o2.getId() ? 1 : 0;
                            }
                        });
                }
            } else if(order.equals("Descendente")){
                switch(filter){
                    case "Name" : clientEntityList.sort(new Comparator<ClientEntity>() {
                        @Override
                        public int compare(ClientEntity o1, ClientEntity o2) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    });

                    case "Address": clientEntityList.sort(new Comparator<ClientEntity>() {
                        @Override
                        public int compare(ClientEntity o1, ClientEntity o2) {
                            return o2.getAddress().compareTo(o1.getAddress());
                        }
                    });

                    case "Gender": clientEntityList.sort(new Comparator<ClientEntity>() {
                        @Override
                        public int compare(ClientEntity o1, ClientEntity o2) {
                            return o2.getGender().compareTo(o1.getGender());
                        }
                    });

                    case "City": clientEntityList.sort(new Comparator<ClientEntity>() {
                        @Override
                        public int compare(ClientEntity o1, ClientEntity o2) {
                            return o2.getCity().compareTo(o1.getCity());
                        }
                    });

                    case "ID": clientEntityList.sort(new Comparator<ClientEntity>() {
                        @Override
                        public int compare(ClientEntity o1, ClientEntity o2) {
                            return o2.getId() == o1.getId() ? 1 : 0;
                        }
                    });
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        request.setAttribute("search-user", clientEntityList);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
