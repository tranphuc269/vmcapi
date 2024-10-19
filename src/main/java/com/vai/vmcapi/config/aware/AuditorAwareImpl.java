package com.vai.vmcapi.config.aware;

import com.vai.vmcapi.repo.entity.UserEntity;
import com.vai.vmcapi.repo.jpa.UserRepository;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<UserEntity> {

    @Resource
    private UserRepository userRepository;

    @NotNull
    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return Optional.empty();
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
