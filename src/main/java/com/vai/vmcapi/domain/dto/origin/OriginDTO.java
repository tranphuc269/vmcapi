package com.vai.vmcapi.domain.dto.origin;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class OriginDTO extends BaseVO {
    private String name;
}
