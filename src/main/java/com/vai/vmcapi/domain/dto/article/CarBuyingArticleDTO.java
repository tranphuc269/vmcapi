package com.vai.vmcapi.domain.dto.article;

import com.vai.vmcapi.domain.dto.BaseVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CarBuyingArticleDTO extends BaseVO {
    private Integer min;
    private Integer max;
    private String title;
    private String content;
}