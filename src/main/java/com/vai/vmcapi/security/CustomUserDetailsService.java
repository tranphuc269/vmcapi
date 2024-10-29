package com.vai.vmcapi.security;

import com.vai.vmcapi.domain.exception.BusinessException;
import com.vai.vmcapi.repo.jpa.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserContext loadUserById(Long userId) {
        return UserContext.fromEntity(userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
