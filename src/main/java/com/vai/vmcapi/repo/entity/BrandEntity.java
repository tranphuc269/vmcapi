package com.vai.vmcapi.repo.entity;


import com.vai.vmcapi.domain.dto.brand.BrandDTO;
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
@Table(name = "brands")
@SuperBuilder
public class BrandEntity extends BaseEntityAudit {
    private String name;
    private String logo;

    public BrandDTO toDto() {
        return BrandDTO.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .name(this.name)
                .logo(this.logo)
                .build();
    }
}
