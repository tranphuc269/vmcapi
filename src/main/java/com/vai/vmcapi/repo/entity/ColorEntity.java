package com.vai.vmcapi.repo.entity;


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
}
