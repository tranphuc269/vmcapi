package com.vai.vmcapi;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class VmcapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VmcapiApplication.class, args);
    }

}
