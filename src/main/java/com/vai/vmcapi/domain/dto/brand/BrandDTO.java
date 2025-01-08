package com.vai.vmcapi.domain.dto.brand;

import lombok.*;

import java.time.Instant;

@Data
@Builder
public class BrandDTO {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private String logo;
    private Boolean isTruck;
}
