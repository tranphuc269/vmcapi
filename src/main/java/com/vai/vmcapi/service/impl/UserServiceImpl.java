package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.user.AuthResponse;
import com.vai.vmcapi.domain.dto.user.CreateUserRequest;
import com.vai.vmcapi.domain.dto.user.LoginRequest;
import com.vai.vmcapi.domain.dto.user.UserVO;
import com.vai.vmcapi.domain.exception.NotFoundUserException;
import com.vai.vmcapi.repo.entity.UserEntity;
import com.vai.vmcapi.repo.jpa.JpaUserRepository;
import com.vai.vmcapi.security.UserPrincipal;
import com.vai.vmcapi.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {

    @Resource
    private JpaUserRepository userRepository;
    @Override
    public UserPrincipal loadUserByUsername(String username) {
        return UserPrincipal.fromEntity(userRepository.findByUsername(username).orElseThrow(NotFoundUserException::new));
    }

    @Override
    public UserVO createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(request.getPassword());
        userRepository.save(userEntity);
        return userEntity.toVO();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public UserVO resetPassword(Long id) {
        return null;
    }
}
