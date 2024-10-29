package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.user.AuthResponse;
import com.vai.vmcapi.domain.dto.user.CreateUserRequest;
import com.vai.vmcapi.domain.dto.user.LoginRequest;
import com.vai.vmcapi.domain.dto.user.UserVO;
import com.vai.vmcapi.domain.exception.BusinessException;
import com.vai.vmcapi.repo.entity.UserEntity;
import com.vai.vmcapi.repo.jpa.UserRepository;
import com.vai.vmcapi.repo.jpa.WardRepository;
import com.vai.vmcapi.security.JwtProvider;
import com.vai.vmcapi.security.UserContext;
import com.vai.vmcapi.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final WardRepository wardRepository;
    private final JwtProvider jwtUtils;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, WardRepository wardRepository, JwtProvider jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.wardRepository = wardRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserVO createUser(CreateUserRequest request) {
        userRepository.findByUsernameOrPhoneNum(request.getUsername(), request.getPhoneNum()).ifPresent(userEntity -> {
            throw new BusinessException("Username or phone number already exists");
        });
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setPhoneNum(request.getPhoneNum());
        userEntity.setFullname(request.getFullname());
        userEntity.setWard(wardRepository.findById(request.getWardId()).orElse(null));

        userEntity = userRepository.save(userEntity);
        return userEntity.toVO();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // Authenticate the user
        Optional<UserEntity> userEntity = userRepository.findByUsername(request.getUsername());
        if (userEntity.isPresent() && passwordEncoder.matches(request.getPassword(), userEntity.get().getPassword())) { // You should hash the password before comparing
            String token = jwtUtils.generateToken(userEntity.get().getId());
//            return new AuthResponse(userEntity.get().getId(), userEntity.get().getUsername(), token);
            return new AuthResponse(userEntity.get().getId(), userEntity.get().getUsername(), token); // You should generate a real JWT token here
        }
        return null;
    }

    @Override
    public UserVO resetPassword(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            userEntity.setPassword("123456aA@"); // You should hash the password before saving
            userEntity = userRepository.save(userEntity);
            return userEntity.toVO();
        }
        return null;
    }

    @Override
    public UserVO me(UserContext userContext) {
        UserEntity userEntity = userRepository.findById(userContext.getId()).orElse(null);
        if (userEntity != null) {
            return userEntity.toVO();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserContext.fromEntity(userEntity);
    }
}
