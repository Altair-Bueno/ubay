package uma.taw.ubay.dao;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Altair Bueno
 */
@Stateless
public class MinioFacade {

    private MinioClient minioClient;
    private String bucket;
    private String url;
    private String username;
    private String password;

    @PostConstruct
    public void _postConstruct() {
        // From https://stackoverflow.com/questions/51038324/jndi-lookup-on-glassfish-4-1-1-custom-resources
        try {
            Context c = new InitialContext();
            username = (String) c.lookup("minio/username");
            password = ((String) c.lookup("minio/password"));
            url = ((String) c.lookup("minio/url"));
            bucket = (String) c.lookup("minio/bucket");
            minioClient =  MinioClient
                    .builder()
                    .endpoint(url)
                    .credentials(username, password)
                    .build();
        } catch (Exception e) {
            minioClient = null;
        }
    }

    public String uploadObject(InputStream inputStream) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
        byte [] inputStreamContent = inputStream.readAllBytes();
        byte[] digest = shaDigest.digest(inputStreamContent);
        String objectName = Base64.getEncoder().encodeToString(digest);

        InputStream uploadStream = new ByteArrayInputStream(inputStreamContent);
        PutObjectArgs uploadObjectArgs = PutObjectArgs.builder()
                .bucket(bucket)
                .stream(uploadStream,uploadStream.available(),-1)
                .object(objectName)
                .build();
        minioClient.putObject(uploadObjectArgs);
        uploadStream.close();
        return objectName;
    }

    public InputStream getObject(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (objectName == null) return null;
        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(bucket).object(objectName).build();
        return minioClient.getObject(getObjectArgs);
    }

    public void removeObject(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (objectName == null) return;
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .object(objectName)
                .bucket(bucket)
                .build();
        minioClient.removeObject(removeObjectArgs);
    }
}
