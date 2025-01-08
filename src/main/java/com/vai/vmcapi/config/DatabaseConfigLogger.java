package com.vai.vmcapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfigLogger {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    public void logUrl() {
        System.out.println("Database URL: " + datasourceUrl);
    }
}
