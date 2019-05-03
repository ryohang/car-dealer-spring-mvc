package io.ascending.training.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class StorageService {

    private AmazonS3 s3;
    private String bucket;
    @Value("#{ applicationProperties['amazon.s3.url'] }")
    private String cdnUrl;

    public StorageService(AmazonS3 s3){
        this.s3 = s3;
    }

    public StorageService(){
    }

    public AmazonS3 getStorageClient(){
        return s3;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void putObject(String S3key, File file) {
        putObject(bucket, S3key, file);
    }

    public void putObject(String bucket, String S3key, File file) {
        s3.putObject(bucket, S3key, file);
    }

    public S3Object getObject(String S3key) {
        if(S3key==null){
            return null;
        }else{
            return getObject(bucket, S3key);
        }
    }

    public S3Object getObject(String bucket , String S3key) {
        return s3.getObject(bucket, S3key);
    }

    public String getObjectUrl(String S3key) {
        return s3.getUrl(bucket,S3key).toString();
    }
}
