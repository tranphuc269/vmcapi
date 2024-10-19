package com.vai.vmcapi.domain.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vai.vmcapi.domain.dto.address.AddressDTO;
import com.vai.vmcapi.domain.dto.brand.BrandDTO;
import com.vai.vmcapi.domain.dto.color.ColorDTO;
import com.vai.vmcapi.domain.dto.fuel.FuelDTO;
import com.vai.vmcapi.domain.dto.origin.OriginDTO;
import com.vai.vmcapi.domain.dto.style.StyleDTO;
import lombok.*;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private String logo;
    private Long brandId;
    private BrandDTO brand;
    private Long styleId;
    private StyleDTO style;
    private Long originId;
    private OriginDTO origin;
    private Long fuelId;
    private FuelDTO fuel;
    private Long outsideColorId;
    private ColorDTO outsideColor;
    private Long insideColorId;
    private ColorDTO insideColor;

    private Long cityId;
    private AddressDTO city;

    private Long districtId;
    private AddressDTO district;

    private Long wardId;
    private AddressDTO ward;
    private String address;
}
