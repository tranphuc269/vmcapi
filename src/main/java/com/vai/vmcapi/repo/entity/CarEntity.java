package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.car.CarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "cars")
@SuperBuilder
public class CarEntity extends BaseEntityAudit {
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String logo;
    @Column(columnDefinition = "TEXT")
    private String images;

    private Long price;
    private Integer manufacturingYear;
    private String version;
    private Integer kmDriven;
    private Integer seatCapacity;
    private String status;

    @Column(unique = true)
    private String slug;


    private String transmission;
    private String drivetrain;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @JoinColumn(name = "brand_id")
    @Column(name = "brand_id", insertable = false, updatable = false)
    private Long brandId;

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


    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
    @Column(name = "city_id", insertable = false, updatable = false)
    private Long cityId;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private DistrictEntity district;
    @Column(name = "district_id", insertable = false, updatable = false)
    private Long districtId;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private WardEntity ward;
    @Column(name = "ward_id", insertable = false, updatable = false)
    private Long wardId;
    private String address;

    public CarDTO toDto() {
        return CarDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .logo(this.getLogo())
                .price(this.getPrice())
                .description(this.getDescription())
                .manufacturingYear(this.getManufacturingYear())
                .version(this.getVersion())
                .kmDriven(this.getKmDriven())
                .seatCapacity(this.getSeatCapacity())
                .status(this.getStatus())
                .transmission(this.getTransmission())
                .drivetrain(this.getDrivetrain())
                .slug(this.getSlug())
                .brand(this.getBrand() == null ? null : this.getBrand().toDto())
                .style(this.getStyle() == null ? null : this.getStyle().toDto())
                .origin(this.getOrigin() == null ? null : this.getOrigin().toDto())
                .fuel(this.getFuel() == null ? null : this.getFuel().toDto())
                .outsideColor(this.getOutsideColor() == null ? null : this.getOutsideColor().toDto())
                .insideColor(this.getInsideColor() == null ? null : this.getInsideColor().toDto())
                .city(this.getCity() == null ? null : this.getCity().toDto())
                .district(this.getDistrict() == null ? null : this.getDistrict().toDto())
                .ward(this.getWard() == null ? null : this.getWard().toDto())
                .images(this.getImages() == null ? new ArrayList<>() : List.of(this.getImages().split(",")))
                .address(this.getAddress())
                .build();
    }
}
