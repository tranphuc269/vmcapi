package com.vai.vmcapi.domain.dto.model;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ModelDTO extends BaseVO {
    private String name;
    private Long branchId;
    private String logo;
}