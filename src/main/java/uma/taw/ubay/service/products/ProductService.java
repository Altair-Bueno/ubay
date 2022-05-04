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
import uma.taw.ubay.entity.ProductEntity;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
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


    public ProductsDTO getProductsList(String productName, String category, String page){
        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductFacade.ProductTupleResult ptr;
        int tam = 0;

        // Filters:


        if(productName != null || (category != null && !category.equals("--"))){
            CategoryEntity cat = null;
            if(!category.equals("--")){
                cat = categoryFacade.searchById(category);
            }
            ptr = productFacade.filterAndGetByPage(productName, cat, Integer.parseInt(page));
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
    public boolean isProductUserFavourite(LoginDTO login, int id){
        ClientEntity user = clientFacade.find(login.getUser().getId());

        if(user != null){
            List<ProductEntity> products = productFavouritesFacade.getClientFavouriteProducts(user);
            var product = productFacade.find(id);
            return products.contains(product);
        }

        return false;
    }

    public ProductDTO findProduct(int id){
        return productEntityToDTO(productFacade.find(id));
    }

    @NotNull
    public List<CategoryDTO> categories(){
        return categoryFacade.findAll().stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
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

    private ClientDTO clientEntityToDto(ClientEntity client){
        return new ClientDTO(client.getId());
    }

    private CategoryDTO categoryEntityToDTO(CategoryEntity category){
        return new CategoryDTO(category.getId(), category.getName());
    }


}
