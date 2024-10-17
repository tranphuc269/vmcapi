package com.vai.vmcapi.config.aware;


import com.vai.vmcapi.repo.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class PersistentConfig {
    @Bean
    AuditorAware<UserEntity> auditorAware() {
        return new AuditorAwareImpl();
    }
}
