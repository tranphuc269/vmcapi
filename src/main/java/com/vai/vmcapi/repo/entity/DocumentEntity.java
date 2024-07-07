package com.vai.vmcapi.repo.entity;


import com.vai.vmcapi.domain.dto.document.DocumentVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "documents")
public class DocumentEntity extends BaseEntityAudit {
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_created_id")
    private UserEntity userCreated;
    private Integer status;
    private String path;
    private Long size;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private DossierEntity dossier;
//
//    @Column(name = "dossier_id", updatable = false)
//    private Long dossierId;

    @Column(columnDefinition = "TEXT")
    private String extractionData;

    public DocumentVO toVO() {
        return DocumentVO.builder().build();
    }
}
