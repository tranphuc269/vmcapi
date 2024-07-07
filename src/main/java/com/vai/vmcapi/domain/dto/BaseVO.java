package com.vai.vmcapi.domain.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Data
public class BaseVO {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
}
