package com.vai.vmcapi.domain.dto.style;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class StyleDTO {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String name;
}