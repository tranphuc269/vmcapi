package com.vai.vmcapi.domain.dto.branch;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BranchDTO extends BaseVO {
    private String name;
    private String logo;
}