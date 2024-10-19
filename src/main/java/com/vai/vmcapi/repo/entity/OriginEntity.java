package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.origin.OriginDTO;
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
@Table(name = "origins")
@SuperBuilder
public class OriginEntity extends BaseEntity{
    private String name;

    public OriginDTO toDto() {
        return OriginDTO.builder()
                .id(this.getId())
                .name(this.name)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}
