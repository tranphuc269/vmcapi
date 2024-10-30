package com.vai.vmcapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class AppComponent {
    @Value("${app.s3.accessKey}")
    private String accessKey;
    @Value("${app.s3.secretKey}")
    private String secretKey;

    @Bean
    public S3Client amazonS3() {
        return S3Client
                .builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .build();
    }
}
