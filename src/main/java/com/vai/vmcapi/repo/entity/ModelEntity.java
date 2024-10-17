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
@Table(name = "models")
@SuperBuilder
public class ModelEntity extends BaseEntityAudit{
    private String name;
    private Long branchId;
    private String logo;
}
