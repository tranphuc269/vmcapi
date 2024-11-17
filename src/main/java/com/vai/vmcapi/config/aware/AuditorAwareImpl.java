package com.vai.vmcapi.config.aware;

import com.vai.vmcapi.repo.entity.UserEntity;
import com.vai.vmcapi.repo.jpa.UserRepository;
import com.vai.vmcapi.security.UserContext;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
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
        Long id = ((UserContext) authentication.getPrincipal()).getId();
        return userRepository.findById(id);
    }
}
