package com.vai.vmcapi.repo.infra.impl;

import com.vai.vmcapi.repo.infra.IS3Repository;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public class S3RepositoryImpl implements IS3Repository {
//
//    @Resource
//    private MinioClient minioClient;
//
//    @Value("${app.s3.bucket}")
//    private String minioBucket;

    @Override
    public void putObject(InputStream data, String src, String contentType) throws Exception {
//        minioClient.putObject(PutObjectArgs
//                .builder()
//                .bucket(minioBucket)
//                .contentType(contentType)
//                .stream(data, data.available(), -1)
//                .build());
    }

    @Override
    public byte[] getObject(String src) throws Exception {
//        GetObjectResponse response = minioClient
//                .getObject(GetObjectArgs
//                        .builder()
//                        .bucket(minioBucket)
//                        .object(src)
//                        .build());
//        return response.readAllBytes();
        return null;
    }
}
