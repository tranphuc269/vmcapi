package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.user.AuthResponse;
import com.vai.vmcapi.domain.dto.user.CreateUserRequest;
import com.vai.vmcapi.domain.dto.user.LoginRequest;
import com.vai.vmcapi.domain.dto.user.UserVO;
import com.vai.vmcapi.repo.entity.UserEntity;
import com.vai.vmcapi.repo.jpa.UserRepository;
import com.vai.vmcapi.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserVO createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(request.getPassword()); // You should hash the password before saving
        userEntity = userRepository.save(userEntity);
        return new UserVO(userEntity.getId(), userEntity.getUsername());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(request.getUsername());
        if (userEntity.isPresent() && userEntity.get().getPassword().equals(request.getPassword())) { // You should hash the password before comparing
            return new AuthResponse(userEntity.get().getId(), userEntity.get().getUsername(), "Bearer token"); // You should generate a real JWT token here
        }
        return null;
    }

    @Override
    public UserVO resetPassword(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            userEntity.setPassword("123456aA@"); // You should hash the password before saving
            userEntity = userRepository.save(userEntity);
            return new UserVO(userEntity.getId(), userEntity.getUsername());
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
