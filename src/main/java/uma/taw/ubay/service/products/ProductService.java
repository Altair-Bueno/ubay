package uma.taw.ubay.service.products;

import io.minio.errors.*;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import org.jetbrains.annotations.NotNull;
import uma.taw.ubay.dao.*;
import uma.taw.ubay.dto.products.*;
import uma.taw.ubay.entity.*;
import uma.taw.ubay.service.AuthService;
import uma.taw.ubay.service.users.UsersService;
import uma.taw.ubay.dao.ProductFacade.ProductTupleResult;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Francisco Javier Hernández
 */

@Stateless
public class ProductService {
    @EJB
    BidFacade bidFacade;

    @EJB
    UsersService usersService;

    @EJB
    ProductFacade productFacade;

    @EJB
    CategoryFacade categoryFacade;

    @EJB
    ProductFavouritesFacade productFavouritesFacade;

    @EJB
    ClientFacade clientFacade;

    @EJB
    MinioFacade minioFacade;

    @EJB
    AuthService authService;


    public ProductsDTO getProductsList(ProductClientDTO sesionClient, String productName, String category, String favOwnedFilter, String page) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductTupleResult<ProductEntity> ptr;
        CategoryEntity cat = null;
        ClientEntity clientEntity = null;
        String name = null;
        boolean favFilter = false, ownedFilter = false;
        int pageParam = Integer.parseInt(page) - 1;

        // Set fav / owned filters
        if(favOwnedFilter.equals("favFilter")){
            favFilter = true;
        } else if(favOwnedFilter.equals("ownedFilter")){
            ownedFilter = true;
        }

        // Set category
        if (!category.equals("0")) {
            int catId = Integer.parseInt(category);
            cat = categoryFacade.searchById(catId);
        }

        // Set client
        if(sesionClient != null) {
            clientEntity = clientFacade.find(sesionClient.getId());
        }

        // Set name
        if(!productName.equals("")){
            name = productName;
        }

        // Filters:
        if(favFilter){
            ptr = productFavouritesFacade.getClientFavouriteProductsFiltered(clientEntity, name, cat, pageParam);
        } else {
            ptr = productFacade.filterAndGetByPage(clientEntity, name, cat, ownedFilter, pageParam);
        }

        for (ProductEntity p : ptr.getProductEntities()) {
            productDTOS.add(
                    productEntityToDTO(p)
            );
        }

        return new ProductsDTO(productDTOS, ptr.getActualSize());
    }

    @NotNull
    public boolean isProductUserFavourite(ProductClientDTO client, int id) {
        ClientEntity user = clientFacade.find(client.getId());

        if (user != null) {
            List<ProductEntity> products = productFavouritesFacade.getClientFavouriteProducts(user);
            var product = productFacade.find(id);
            return products.contains(product);
        }

        return false;
    }

    @NotNull
    public ProductCategoryDTO findCategory(int id) {
        return categoryEntityToDTO(categoryFacade.find(id));
    }

    public ProductDTO createProduct(String title, String description, double outPrice, String images, java.util.Date publishDate, int vendorId, int categoryId) {
        ProductEntity p = new ProductEntity();
        ClientEntity vendorEntity = clientFacade.find(vendorId);
        CategoryEntity categoryEntity = categoryFacade.find(categoryId);

        p.setTitle(title);
        p.setDescription(description);
        p.setOutPrice(outPrice);
        p.setImages(images);
        p.setCloseDate(null);
        p.setPublishDate(new Timestamp(publishDate.getTime()));
        p.setVendor(vendorEntity);
        p.setCategory(categoryEntity);

        productFacade.create(p);
        p = productFacade.find(p.getId());

        return productEntityToDTO(p);
    }

    public ProductDTO findProduct(int id) {
        return productEntityToDTO(productFacade.find(id));
    }

    @NotNull
    public List<ProductCategoryDTO> categories() {
        return categoryFacade.findAllSortedById().stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
    }

    private ProductDTO productEntityToDTO(ProductEntity p) {
        return new ProductDTO(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getOutPrice(),
                p.getImages(),
                p.getCloseDate(),
                p.getPublishDate(),
                clientEntityToDto(p.getVendor()),
                categoryEntityToDTO(p.getCategory())

        );
    }

    public ProductClientDTO loginDTOtoClientDTO(uma.taw.ubay.dto.LoginDTO logindto) {
        LoginCredentialsEntity credentials = authService.getCredentialsEntity(logindto);
        if (credentials.getUser() == null) return null;
        return new ProductClientDTO(credentials.getUser().getId(), credentials.getKind());
    }

    public void deleteProduct(int id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ProductEntity p = productFacade.find(id);

        if (p.getImages() != null) {
            minioFacade.removeObject(p.getImages());
        }

        productFacade.remove(p);
    }

    public void updateProduct(int producto, int categoria, String estado, String desc, String titulo, Double precio, Part file) throws IOException, ServletException {
        CategoryEntity cat = categoryFacade.find(categoria);
        ProductEntity p = productFacade.find(producto);


        // IMAGEN
        if (file != null && !file.getSubmittedFileName().equals("")) {
            InputStream inputStream = file.getInputStream();
            String img = "";

            try {
                img = minioFacade.uploadObject(inputStream);
                if (!img.equals(p.getImages())) {
                    minioFacade.removeObject(p.getImages());
                }
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }

            p.setImages(img);
        }

        // ESTADO
        if(estado != null){
            if (estado.equals("Cerrado")) {
                if (p.getCloseDate() == null) {
                    p.setCloseDate(new Date(new java.util.Date().getTime()));
                }
            } else if (estado.equals("Activo")) {
                if (p.getCloseDate() != null) {
                    p.setCloseDate(null);
                }
            }
        }

        p.setTitle(titulo);
        p.setDescription(desc);
        p.setCategory(cat);
        p.setOutPrice(precio);

        productFacade.edit(p);
    }

    private ProductClientDTO clientEntityToDto(ClientEntity client) {
        return new ProductClientDTO(client.getId(), KindEnum.client);
    }

    private ProductCategoryDTO categoryEntityToDTO(CategoryEntity category) {
        return new ProductCategoryDTO(category.getId(), category.getName());
    }

    public ProductBidDTO getHighestBid(int productId) {
        ProductEntity producto = productFacade.find(productId);
        BidEntity highestBid = bidFacade.getHighestBidByProduct(producto);

        if (highestBid == null) return null;
        return new ProductBidDTO(highestBid.getAmount());
    }


}
