package com.vai.vmcapi.domain.dto.color;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ColorDTO extends BaseVO {
    private String name;
    private String hex;
}
