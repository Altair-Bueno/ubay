package uma.taw.ubay.service.products;

import io.minio.errors.*;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import org.jetbrains.annotations.NotNull;
import uma.taw.ubay.dao.*;
import uma.taw.ubay.dto.products.*;
import uma.taw.ubay.entity.CategoryEntity;
import uma.taw.ubay.entity.ClientEntity;
import uma.taw.ubay.entity.LoginCredentialsEntity;
import uma.taw.ubay.entity.ProductEntity;
import uma.taw.ubay.service.AuthService;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductService {
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


    public ProductsDTO getProductsList(String productName, String category, String page){
        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductFacade.ProductTupleResult ptr;
        page = page.equals("") ? "1" : page;
        int tam = 0;

        // Filters:


        if(productName != null || (category != null && !category.equals("--"))){
            CategoryEntity cat = null;
            if(!category.equals("--")){
                int catId = Integer.parseInt(category);
                cat = categoryFacade.searchById(catId);
            }
            ptr = productFacade.filterAndGetByPage(productName, cat, Integer.parseInt(page) - 1);
        } else {
            ptr = productFacade.getByPage(page == null ? 0 : Integer.parseInt(page) - 1);
        }

        for(ProductEntity p : ptr.getProductEntities()){
            productDTOS.add(
                    productEntityToDTO(p)
            );
        }
        return new ProductsDTO(productDTOS, ptr.getActualSize());
    }

    @NotNull
    public boolean isProductUserFavourite(ProductClientDTO client, int id){
        ClientEntity user = clientFacade.find(client.getId());

        if(user != null){
            List<ProductEntity> products = productFavouritesFacade.getClientFavouriteProducts(user);
            var product = productFacade.find(id);
            return products.contains(product);
        }

        return false;
    }

    @NotNull
    public ProductCategoryDTO findCategory(int id){
        return categoryEntityToDTO(categoryFacade.find(id));
    }

    public ProductDTO createProduct(String title, String description, double outPrice, String images, java.util.Date publishDate, int vendorId, int categoryId){
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

    public ProductDTO findProduct(int id){
        return productEntityToDTO(productFacade.find(id));
    }

    @NotNull
    public List<ProductCategoryDTO> categories(){
        return categoryFacade.findAllSortedById().stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
    }

    private ProductDTO productEntityToDTO(ProductEntity p){
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

    public ProductClientDTO loginDTOtoClientDTO(uma.taw.ubay.dto.LoginDTO logindto){
        LoginCredentialsEntity credentials = authService.getCredentialsEntity(logindto);
        if(credentials.getUser() == null) return null;
        return new ProductClientDTO(credentials.getUser().getId());
    }

    public void deleteProduct(int id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ProductEntity p = productFacade.find(id);

        if(p.getImages() != null){
            minioFacade.removeObject(p.getImages());
        }

        productFacade.remove(p);
    }

    public void updateProduct(int producto, int categoria, String estado, String desc, String titulo, Double precio, Part file) throws IOException, ServletException {
        CategoryEntity cat = categoryFacade.find(categoria);
        ProductEntity p = productFacade.find(producto);


        // IMAGEN
        if(!file.getSubmittedFileName().equals("")){
            InputStream inputStream = file.getInputStream();
            String img = "";

            try {
                img = minioFacade.uploadObject(inputStream);
                if(!img.equals(p.getImages())){
                    minioFacade.removeObject(p.getImages());
                }
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }

            p.setImages(img);
        }

        // ESTADO
        if(estado.equals("Cerrado")){
            if(p.getCloseDate() == null){
                p.setCloseDate(new Date(new java.util.Date().getTime()));
            }
        } else if(estado.equals("Activo")) {
            if(p.getCloseDate() != null){
                p.setCloseDate(null);
            }
        }

        p.setTitle(titulo);
        p.setDescription(desc);
        p.setCategory(cat);
        p.setOutPrice(precio);

        productFacade.edit(p);
    }

    private ProductClientDTO clientEntityToDto(ClientEntity client){
        return new ProductClientDTO(client.getId());
    }

    private ProductCategoryDTO categoryEntityToDTO(CategoryEntity category){
        return new ProductCategoryDTO(category.getId(), category.getName());
    }


}
