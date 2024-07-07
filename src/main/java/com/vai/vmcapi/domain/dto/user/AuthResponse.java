package com.vai.vmcapi.domain.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
}
