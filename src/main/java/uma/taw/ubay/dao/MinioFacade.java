package uma.taw.ubay.dao;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
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

    private static final String MINIO_BUCKET = "ubay";
    private final MinioClient minioClient;

    public MinioFacade() {
        minioClient = getClient();
    }

    private MinioClient getClient() {
        String url = System.getenv("MINIO_URL");
        String username = System.getenv("MINIO_USERNAME");
        String password = System.getenv("MINIO_PASSWORD");
        username = username == null ? "":username;
        password = password == null ? "":password;
        url = url == null ? "http://localhost:9000" : url;
        return MinioClient
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
                .bucket(MINIO_BUCKET)
                .stream(uploadStream,uploadStream.available(),-1)
                .object(objectName)
                .build();
        minioClient.putObject(uploadObjectArgs);
        return objectName;
    }

    public InputStream getObject(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (objectName == null) return null;
        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(MINIO_BUCKET).object(objectName).build();
        return minioClient.getObject(getObjectArgs);
    }

    public void removeObject(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (objectName == null) return;
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .object(objectName)
                .bucket(MINIO_BUCKET)
                .build();
        minioClient.removeObject(removeObjectArgs);
    }
}
