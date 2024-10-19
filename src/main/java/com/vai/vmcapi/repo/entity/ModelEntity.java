package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.model.ModelDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "models")
@SuperBuilder
public class ModelEntity extends BaseEntityAudit{
    private String name;
    private Long brandId;
    private String logo;

    public ModelDTO toDto() {
        return ModelDTO.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .name(this.name)
                .brandId(this.brandId)
                .logo(this.logo)
                .build();
    }
}
