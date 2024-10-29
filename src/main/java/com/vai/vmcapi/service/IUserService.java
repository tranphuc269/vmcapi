package com.vai.vmcapi.service;

import com.vai.vmcapi.domain.dto.user.AuthResponse;
import com.vai.vmcapi.domain.dto.user.CreateUserRequest;
import com.vai.vmcapi.domain.dto.user.LoginRequest;
import com.vai.vmcapi.domain.dto.user.UserVO;
import com.vai.vmcapi.security.UserContext;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    UserVO createUser(CreateUserRequest request);

    AuthResponse login(LoginRequest request);

    UserVO resetPassword(Long id);

    UserVO me(UserContext userContext);
}
