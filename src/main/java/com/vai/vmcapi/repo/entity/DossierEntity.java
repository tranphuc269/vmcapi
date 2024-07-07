package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.dossier.DossierVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "dossiers")
public class DossierEntity extends BaseEntityAudit {
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_created_id")
    private UserEntity userCreated;

    @OneToMany(mappedBy = "dossier")
    private List<DocumentEntity> documents;
    private Integer status;
    private Integer type;

    public DossierVO toVO(){
        return DossierVO
                .builder()
                .id(this.getId())
                .name(this.getName())
                .userCreated(this.getUserCreated().getUsername())
                .status(this.getStatus())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}
