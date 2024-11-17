package com.vai.vmcapi.repo.entity;


import com.vai.vmcapi.domain.dto.article.CarBuyingArticleDTO;
import jakarta.persistence.Column;
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
    @Column(columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String code;


    public CarBuyingArticleDTO toDto() {
        return CarBuyingArticleDTO.builder()
                .id(this.getId())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .min(this.min)
                .max(this.max)
                .title(this.title)
                .content(this.content)
                .code(this.code)
                .user(this.getUserCreated() == null ? null : this.getUserCreated().toVO())
                .build();
    }
}
