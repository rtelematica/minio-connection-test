package com.minio.test.minioconectiontest;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinioConectionTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MinioConectionTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String minioUrl = args[0];
        String accessKey = args[1];
        String accessSecret = args[2];
        String bucket = args[3];

        System.out.println("minioUrl: " + minioUrl);
        System.out.println("accessKey: " + accessKey);
        System.out.println("accessSecret: " + accessSecret);
        System.out.println("bucket: " + bucket);

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minioUrl)
                        .credentials(accessKey, accessSecret)
                        .build();

        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }
}
