package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.user.AuthResponse;
import com.vai.vmcapi.domain.dto.user.CreateUserRequest;
import com.vai.vmcapi.domain.dto.user.LoginRequest;
import com.vai.vmcapi.domain.dto.user.UserVO;
import com.vai.vmcapi.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private IUserService userService;
    @PostMapping
    public ResponseDTO<UserVO> createUser(@RequestBody @Valid CreateUserRequest request){
        return ResponseDTO.success(userService.createUser(request));
    }

    @PostMapping("/auth")
    public ResponseDTO<AuthResponse> login(@RequestBody @Valid LoginRequest request){
        return ResponseDTO.success(userService.login(request));
    }
    @PutMapping("/{id}/reset-password")
    public ResponseDTO<UserVO> resetPassword(@PathVariable Long id){
        return ResponseDTO.success(userService.resetPassword(id));
    }
}
