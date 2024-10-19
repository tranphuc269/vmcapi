package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.fuel.FuelDTO;
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
@Table(name = "fuels")
@SuperBuilder
public class FuelEntity extends BaseEntityAudit{
    private String name;

    public FuelDTO toDto() {
        return FuelDTO.builder()
                .id(this.getId())
                .name(this.name)
                .build();
    }
}
