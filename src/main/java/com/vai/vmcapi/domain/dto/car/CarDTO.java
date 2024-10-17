package com.vai.vmcapi.domain.dto.car;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CarDTO extends BaseVO {
    private String name;
    private String logo;
    private Long branchId;
    private Long styleId;
    private Long originId;
    private Long fuelId;
    private Long outsideColorId;
    private Long insideColorId;
}