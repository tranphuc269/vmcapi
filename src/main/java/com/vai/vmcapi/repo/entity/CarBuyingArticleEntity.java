package com.vai.vmcapi.repo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "car_buying_articles")
public class CarBuyingArticleEntity extends BaseEntityAudit {
    private Integer min;
    private Integer max;
    private String title;
    private String content;
}
