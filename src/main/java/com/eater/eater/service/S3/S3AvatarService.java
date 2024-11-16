package com.eater.eater.service.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.eater.eater.enums.FileCategory;
import com.eater.eater.exception.AwsS3Exception;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3AvatarService {
    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3AvatarService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    private String generateKey(Long id, MultipartFile file, FileCategory category) {
        String fileName = file.getOriginalFilename().replaceAll(" ", "-");
        return category.getDirectory() + id + "-" + fileName;
    }


    public String putObjectIntoBucket(Long id, MultipartFile file, FileCategory category) {
        avatarValidation(file);

        String objectKey = generateKey(id, file, category);
        try (FileOutputStream fos = new FileOutputStream(file.getOriginalFilename())) {
            File convertedFile = new File(file.getOriginalFilename());
            fos.write(file.getBytes());
            s3Client.putObject(new PutObjectRequest(bucketName, objectKey, convertedFile));

            return s3Client.getUrl(bucketName, objectKey).toString();
        } catch (AmazonServiceException | IOException e) {
            throw new AwsS3Exception("Amazon S3 couldn't process the request: " + e.getMessage());
        } catch (SdkClientException e) {
            throw new AwsS3Exception("Failed to connect to Amazon S3: " + e.getMessage());
        } finally {
            new File(file.getOriginalFilename()).delete();
        }
    }

    public void avatarValidation(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Avatar cannot be empty.");
        }

        String fileName = file.getOriginalFilename().toLowerCase();
        if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".webp")) {
            throw new IllegalArgumentException("Unsupported image file extension. You can provide .png, .jpg, .jpeg, .webp");
        }

        if (file.getSize() > 400000) {
            throw new IllegalArgumentException("Avatar size exceeds 4 kilobytes.");
        }
    }
}