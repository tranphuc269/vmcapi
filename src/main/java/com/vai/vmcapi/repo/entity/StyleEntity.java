package com.vai.vmcapi.repo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "styles")
public class StyleEntity extends BaseEntityAudit {
}
