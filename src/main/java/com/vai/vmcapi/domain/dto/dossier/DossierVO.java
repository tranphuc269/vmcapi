package com.vai.vmcapi.domain.dto.dossier;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DossierVO extends BaseVO {
    private String name;
    private String userCreated;
    private Integer status;
    private Integer type;

}
