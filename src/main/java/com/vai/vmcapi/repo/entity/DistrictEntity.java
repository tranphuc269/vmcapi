package com.vai.vmcapi.repo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vai.vmcapi.domain.dto.address.AddressDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "districts")
public class DistrictEntity extends BaseEntity {
    private String name;
    private String type;
    private String slug;
    private String name_with_type;

    private String path;
    @JsonProperty("path_with_type")
    private String pathWithType;
    @Column(unique = true)
    private String code;
    @JsonProperty("parent_code")
    private String parentCode;


    public AddressDTO toDto() {
        return AddressDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .slug(this.getSlug())
                .type(this.getType())
                .path(this.getPath())
                .pathWithType(this.getPathWithType())
                .code(this.getCode())
                .parentCode(this.getParentCode())
                .build();
    }
}
