package com.vai.vmcapi.domain.dto.dossier;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateDossierRequest {
    private String name;
    private Integer type;
}
