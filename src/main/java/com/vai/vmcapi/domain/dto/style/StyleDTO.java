package com.vai.vmcapi.domain.dto.style;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StyleDTO extends BaseVO {
    private String name;
}