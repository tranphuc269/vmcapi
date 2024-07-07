package com.vai.vmcapi.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import java.net.MalformedURLException;
import java.net.URI;

public class AppComponent {
    @Value("${app.s3.accessKey}")
    private String accessKey;
    @Value("${app.s3.secretKey}")
    private String secretKey;
    @Value("${app.s3.url}")
    private String minioUrl;

    @Bean
    public MinioClient minioClient() throws MalformedURLException {
        return MinioClient
                .builder()
                .endpoint(URI.create(minioUrl).toURL())
                .credentials(accessKey, secretKey)
                .build();
    }
}
