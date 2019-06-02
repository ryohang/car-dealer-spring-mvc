package io.ascending.training.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class LamStorageService {

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
}
