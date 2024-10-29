package com.vai.vmcapi.domain.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String phoneNum;

    @NotBlank
    private String fullname;

    @NotNull
    private Long wardId;

    @NotBlank
//    @Min(value = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;
}
