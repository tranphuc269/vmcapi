package com.vai.vmcapi.domain.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class ModelDTO {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
    private Long brandId;
    private String logo;

}
