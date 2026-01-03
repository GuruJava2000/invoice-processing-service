
package com.example.invoice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;


import java.io.InputStream;

@Service
public class S3Service {

    private final S3Client s3Client = S3Client.create();

    @Value("${aws.target-bucket}")
    private String targetBucket;

    public InputStream download(String bucket, String key) {
        return s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build());
    }

    public void copy(String sourceBucket, String key) {
        s3Client.copyObject(CopyObjectRequest.builder()
                .sourceBucket(sourceBucket)
                .sourceKey(key)
                .destinationBucket(targetBucket)
                .destinationKey(key)
                .build());
    }
}
