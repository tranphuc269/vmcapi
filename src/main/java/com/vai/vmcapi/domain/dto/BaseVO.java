package com.vai.vmcapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
}
