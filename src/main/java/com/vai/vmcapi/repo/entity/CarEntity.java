package com.vai.vmcapi.repo.entity;

import jakarta.persistence.*;
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
@Table(name = "cars")
@SuperBuilder
public class CarEntity extends BaseEntityAudit {
    private String name;
    private String logo;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BrandEntity branch;

    @JoinColumn(name = "branch_id")
    @Column(name = "branch_id", insertable = false, updatable = false)
    private Long branchId;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private StyleEntity style;
    @Column(name = "style_id", insertable = false, updatable = false)
    private Long styleId;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private OriginEntity origin;
    @Column(name = "origin_id", insertable = false, updatable = false)
    private Long originId;


    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private FuelEntity fuel;
    @Column(name = "fuel_id", insertable = false, updatable = false)
    private Long fuelId;

    @ManyToOne
    @JoinColumn(name = "outside_color_id")
    private ColorEntity outsideColor;
    @Column(name = "outside_color_id", insertable = false, updatable = false)
    private Long outsideColorId;

    @ManyToOne
    @JoinColumn(name = "inside_color_id")
    private ColorEntity insideColor;
    @Column(name = "inside_color_id", insertable = false, updatable = false)
    private Long insideColorId;
}