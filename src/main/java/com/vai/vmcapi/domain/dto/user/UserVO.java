package com.vai.vmcapi.domain.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserVO {
    private Long id;
    private String username;

}