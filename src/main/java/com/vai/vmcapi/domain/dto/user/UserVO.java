package com.vai.vmcapi.domain.dto.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.vai.vmcapi.domain.dto.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class UserVO {
    private Long id;
    private String username;
    private String phoneNum;
    private String fullname;
    private String role;
    private AddressDTO ward;

}
