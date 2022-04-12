package uma.taw.ubay.dao;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Stateless
public class MinioFacade {

    private MinioClient minioClient;
    @Resource(name = "bucket",lookup = "jndi/minio")
    private String bucket;
    @Resource(name = "url",lookup = "jndi/minio")
    private String url;
    @Resource(name = "username",lookup = "jndi/minio")
    private String username;
    @Resource(name = "password",lookup = "jndi/minio")
    private String password;

    @PostConstruct
    public void _postConstruct() {
        minioClient =  MinioClient
                .builder()
                .endpoint(url)
                .credentials(username, password)
                .build();

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
