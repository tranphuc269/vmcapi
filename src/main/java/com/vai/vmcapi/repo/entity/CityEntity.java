package com.vai.vmcapi.repo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vai.vmcapi.domain.dto.address.AddressDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cities")
public class CityEntity extends BaseEntity {
    private String name;
    private String slug;
    private String type;
    @JsonProperty("name_with_type")
    private String nameWithType;
    @Column(unique = true)
    private String code;

    public AddressDTO toDto() {
        return AddressDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .slug(this.getSlug())
                .type(this.getType())
                .path(this.getNameWithType())
                .code(this.getCode())
                .parentCode(null)
                .build();
    }
}
