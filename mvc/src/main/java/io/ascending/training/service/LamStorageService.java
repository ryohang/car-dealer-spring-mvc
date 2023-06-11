package io.ascending.training.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import io.ascending.training.domain.Image;
import io.ascending.training.extend.exp.ServiceException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class LamStorageService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String clientRegion="us-east-1";
    public void uploadObject(String bucketName,File f){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        // Upload a file as a new object with ContentType and title specified.
        PutObjectRequest request = new PutObjectRequest(bucketName, f.getName(), f);
        s3Client.putObject(request);
    }


    public void uploadObject(String bucketName,
                             String key, String fullFilepath) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        s3Client.putObject(bucketName, key,
                new File(fullFilepath));
    }

    public String uploadMultipartFile(MultipartFile multipartFile) throws ServiceException, IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        String s3Key = multipartFile.getOriginalFilename();
        String homeDir = System.getProperty("catalina.base") !=null ? System.getProperty("catalina.base") : "/tmp/";
        File localFile = new File(homeDir +"/"+ s3Key);
        multipartFile.transferTo(localFile);
        s3Client.putObject("car-service-dev", s3Key,
                localFile);
        return null;
    }


    public String generatePresignedURL(String bucketName,
                                       String objectKey, String httpMethodString) {

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        // Set the pre-signed URL to expire after one hour.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        // Generate the pre-signed URL.
        logger.info("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.valueOf(httpMethodString))
                        .withExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
