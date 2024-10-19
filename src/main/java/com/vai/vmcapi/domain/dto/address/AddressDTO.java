package com.vai.vmcapi.domain.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Long id;
    private String name;
    private String slug;
    private String type;
    private String path;
    private String pathWithType;
    private String code;
    private String parentCode;
}
