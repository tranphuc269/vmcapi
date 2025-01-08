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
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private Long price;
    private String priceText;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private String logo;
    private Integer manufacturingYear;
    private String version;
    private Integer kmDriven;
    private Integer seatCapacity;
    private String status;
    private String transmission;
    private String drivetrain;
    private List<String> images;
    private String slug;
    private String code;

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
    private String description;
    private Integer isPublish;

    @Builder.Default
    private String userPhoneNum = "0123456789";
    @Builder.Default
    private String username = "Không xác định";
}
