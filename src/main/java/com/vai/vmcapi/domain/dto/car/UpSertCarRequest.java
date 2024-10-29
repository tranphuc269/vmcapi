package com.vai.vmcapi.domain.dto.car;

import com.vai.vmcapi.domain.validator.EnumValidator;
import com.vai.vmcapi.enums.ECarStatus;
import com.vai.vmcapi.enums.EDrivetrain;
import com.vai.vmcapi.enums.ETransmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class UpSertCarRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Manufacturing year cannot be null")
    private Integer manufacturingYear;

    @Min(value = 1, message = "Seat capacity must be greater than 0")
    private Integer seatCapacity;

    @EnumValidator(enumClass = ECarStatus.class, message = "Invalid car status")
    private String status;

    @EnumValidator(enumClass = ETransmission.class, message = "Invalid transmission type")
    private String transmission;

    @EnumValidator(enumClass = EDrivetrain.class, message = "Invalid drivetrain type")
    private String drivetrain;
    private List<String> images;
    private String slug;
    private String version;
    private Integer kmDriven;
    private Long price;
    private String logo;
    private Long brandId;
    private Long modelId;
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
