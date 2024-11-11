package com.eater.eater.service.S3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.ServerSideEncryption;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class s3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public s3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadUserAvatar(String username, MultipartFile file) throws IOException {
        String key = "avatars/" + username + "/" + file.getOriginalFilename();

        // Вказуємо SSE-KMS з використанням Bucket Key
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .serverSideEncryption(ServerSideEncryption.AWS_KMS)
                .bucketKeyEnabled(true)
                .build();

        s3Client.putObject(putObjectRequest, Paths.get(file.getOriginalFilename()));

        return getObjectUrl(key);
    }

    private String getObjectUrl(String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, s3Client.region().id(), key);
    }
}
