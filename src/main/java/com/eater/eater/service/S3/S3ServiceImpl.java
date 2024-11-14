package com.eater.eater.service.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3ServiceImpl {
    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3ServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    private String generateKey(Long id, MultipartFile file) {
        String fileName = file.getOriginalFilename().replaceAll(" ", "-");
        return "avatars/" + id + "-" + fileName;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }


    public String putObjectIntoBucket(Long id, MultipartFile file) {
        String objectKey = generateKey(id, file);
        try {
            // Convert MultipartFile to File
            File convertedFile = convertMultipartFileToFile(file);

            // Build put request without any ACL
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, convertedFile);

            // Upload the file
            s3Client.putObject(putObjectRequest);

            // Return the public URL of the uploaded file
            return s3Client.getUrl(bucketName, objectKey).toString();
        } catch (AmazonServiceException | IOException e) {
            System.err.println("Failed to upload file: " + e.getMessage());
            return null;
        }
    }


    // Deletes a file from the S3 bucket
//    public void deleteObjectFromBucket(String objectKey) {
//        try {
//            s3Client.deleteObject(bucketName, objectKey);
//            System.out.println("File deleted successfully");
//        } catch (AmazonServiceException e) {
//            System.err.println("Failed to delete file: " + e.getMessage());
//        }
//    }
}
