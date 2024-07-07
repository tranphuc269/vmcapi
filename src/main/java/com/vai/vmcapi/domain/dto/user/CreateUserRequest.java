package com.vai.vmcapi.domain.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Min(value = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;
}
