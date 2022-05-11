package uma.taw.ubay.service.users;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.jetbrains.annotations.NotNull;
import uma.taw.ubay.dao.*;
import uma.taw.ubay.dto.LoginDTO;
import uma.taw.ubay.dto.notifications.BidsDTO;
import uma.taw.ubay.dto.users.ClientDTO;
import uma.taw.ubay.dto.users.PasswordChangeDTO;
import uma.taw.ubay.dto.users.ProductDTO;
import uma.taw.ubay.entity.*;
import uma.taw.ubay.service.AuthService;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author José Luis Bueno Pachón 80%
 * @author Altair Bueno 20%
 */

@Stateless
public class UsersService {
    @EJB
    ProductFavouritesFacade favouritesFacade;

    @EJB
    ClientFacade clientFacade;

    @EJB
    ProductFacade productFacade;

    @EJB
    LoginCredentialsFacade loginFacade;

    @EJB
    AuthService authService;

    @EJB
    BidFacade bidFacade;

    @EJB
    PasswordResetFacade passwordResetFacade;

    public void addFavProduct(String productID, String clientID) {
        ProductEntity product = productFacade.find(Integer.parseInt(productID));
        ClientEntity client = clientFacade.find(Integer.parseInt(clientID));

        ProductFavouritesEntity fav = new ProductFavouritesEntity();
        fav.setProduct(product);
        fav.setUser(client);
        favouritesFacade.create(fav);
    }

    public void deleteUser(String id) {
        if(id != null){
            try{
                ClientEntity client = clientFacade.find(Integer.parseInt(id));
                LoginCredentialsEntity login = loginFacade.searchClientLoginByClient(client);
                PasswordResetEntity passwordReset = passwordResetFacade.searchPasswordResetByLoginCredentials(login);

                if(passwordReset != null){
                    passwordResetFacade.remove(passwordReset);
                }

                loginFacade.remove(login);
                clientFacade.remove(client);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteFavProduct(String productID, String clientID) {
        ProductEntity product = productFacade.find(Integer.parseInt(productID));
        ClientEntity client = clientFacade.find(Integer.parseInt(clientID));

        ProductFavouritesEntity fav = favouritesFacade.getTuple(client, product);
        favouritesFacade.remove(fav);
    }

    public void modifyUser(String id, String name, String lastName, GenderEnum gender, String address, String city, Date birthDate) {
        ClientEntity client = clientFacade.find(Integer.parseInt(id));
        client.setName(name);
        client.setLastName(lastName);
        client.setGender(gender);
        client.setAddress(address);
        client.setCity(city);
        client.setBirthDate(birthDate);
        clientFacade.edit(client);
    }

    public List<ProductDTO> products(LoginDTO client) {
        ClientEntity user = authService.getCredentialsEntity(client).getUser();

        List<ProductDTO> favouriteProducts = favouritesFacade.getClientFavouriteProducts(user).stream().map(this::productEntityToDTO).collect(Collectors.toList());
        return favouriteProducts;
    }

    private ProductDTO productEntityToDTO(ProductEntity productEntity) {
        return new ProductDTO(productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getImages(),
                productEntity.getCloseDate());
    }

    private uma.taw.ubay.dto.notifications.ProductDTO notificationsProductEntityToDTO(ProductEntity productEntity){
        return new uma.taw.ubay.dto.notifications.ProductDTO(productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getImages(),
                productEntity.getCloseDate());
    }

    public int getClientID(LoginDTO login){
        return authService.getCredentialsEntity(login).getUser().getId();
    }

    private BidsDTO bidEntityToDto(BidEntity bidEntity){
        return new BidsDTO(bidEntity.getPublishDate(),
                bidEntity.getAmount(),
                notificationsProductEntityToDTO(bidEntity.getProduct())
        );
    }

    public HashMap<BidsDTO, Boolean> getNotifications(LoginDTO login) {
        var user = authService.getCredentialsEntity(login).getUser();
        HashMap<BidsDTO, Boolean> notifications = new LinkedHashMap(); // Key: Bid; Value: Is the client the bid winner
        List<BidEntity> closedBidsByClient = bidFacade.productsBiddedClosedProducts(user);

        for(BidEntity b : closedBidsByClient){
            BidsDTO bidDto = bidEntityToDto(b);
            notifications.put(bidDto, bidFacade.isWinnerBid(user, b));
        }

        return notifications;
    }

    public List<ClientDTO> users(String id, String name, String lastName, String address, String city, String genderString) {
        GenderEnum gender = null;
        if(genderString != null && !"".equals(genderString) && !genderString.equals("--")){
            gender = GenderEnum.valueOf(genderString);
        }

        List<ClientEntity> clientEntityList;
        clientEntityList = clientFacade.filterClients(name, lastName,gender, address, city, id);
        return clientEntityList.stream().map(this::clientEntityToDTO).collect(Collectors.toList());
    }

    private ClientDTO clientEntityToDTO(ClientEntity client){
        return new ClientDTO(client.getId(), client.getName(), client.getLastName(), client.getGender(), client.getAddress(), client.getCity(), client.getBirthDate());
    }

    private String generateRandomString(int size, Random random) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @NotNull
    public PasswordChangeDTO passwordChange(String id) {
        String passwordChangeID = generateRandomString(20,new Random());
        ClientEntity client = clientFacade.find(Integer.parseInt(id));
        LoginCredentialsEntity loginCredentialsEntity = loginFacade.searchClientLoginByClient(client);

        PasswordResetEntity passwordResetEntity = new PasswordResetEntity();
        passwordResetEntity.setUser(loginCredentialsEntity);
        passwordResetEntity.setRequestId(passwordChangeID);
        passwordResetFacade.create(passwordResetEntity);

        return new PasswordChangeDTO(passwordChangeID, loginCredentialsEntity.getUsername());
    }
}
