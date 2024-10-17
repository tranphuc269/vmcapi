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
@Table(name = "car_buying_articles")
@SuperBuilder
public class CarBuyingArticleEntity extends BaseEntityAudit {
    private Integer min;
    private Integer max;
    private String title;
    private String content;
}
