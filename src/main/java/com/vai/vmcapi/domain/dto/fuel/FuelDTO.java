package com.vai.vmcapi.domain.dto.fuel;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FuelDTO extends BaseVO {
    private String name;
}
