package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.style.StyleDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "styles")
@SuperBuilder
public class StyleEntity extends BaseEntityAudit {
    private String name;


    public StyleDTO toDto() {
        return StyleDTO.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .name(this.name)
                .build();
    }
}
