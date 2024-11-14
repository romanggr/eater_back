package com.eater.eater.service.S3;

import java.io.IOException;

public interface S3Service {
    void getObjectFromBucket(String bucketName, String objectName) throws IOException;

    void putObjectIntoBucket(String bucketName, String objectName, String filePathToUpload);

}
