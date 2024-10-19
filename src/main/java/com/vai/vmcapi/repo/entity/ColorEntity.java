package com.vai.vmcapi.repo.entity;


import com.vai.vmcapi.domain.dto.color.ColorDTO;
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
@Table(name = "colors")
@SuperBuilder
public class ColorEntity extends BaseEntityAudit {
    private String name;
    private String hex;

    public ColorDTO toDto() {
        return ColorDTO.builder()
                .id(this.getId())
                .name(this.name)
                .hex(this.hex)
                .build();
    }
}
