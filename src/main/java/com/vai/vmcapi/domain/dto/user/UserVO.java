package com.vai.vmcapi.domain.dto.user;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserVO extends BaseVO {
    private String username;
}
