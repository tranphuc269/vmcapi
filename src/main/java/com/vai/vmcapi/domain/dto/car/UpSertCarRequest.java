package com.vai.vmcapi.domain.dto.car;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UpSertCarRequest {
    private String name;
    private String logo;
    private Long brandId;
    private Long styleId;
    private Long originId;
    private Long fuelId;
    private Long outsideColorId;
    private Long insideColorId;

    private Long cityId;

    private Long districtId;

    private Long wardId;
    private String address;
}
