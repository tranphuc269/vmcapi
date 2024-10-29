package com.vai.vmcapi.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {
    @Resource
    private S3Client s3Client;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        Path tempFile = Files.createTempFile("upload-", multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile.toFile()); // Save the file locally
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        String bucketName = "market-car";
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .acl("public-read")
                .build();
        s3Client.putObject(putObjectRequest,
                RequestBody.fromFile(tempFile));
        return getUrl(bucketName, fileName);
    }

    public String getUrl(String bucketName, String fileName) {
        return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toExternalForm();
    }
}
