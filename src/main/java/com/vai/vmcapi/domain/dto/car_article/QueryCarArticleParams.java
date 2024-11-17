package com.vai.vmcapi.domain.dto.car_article;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QueryCarArticleParams {
    private Integer min;
    private Integer max;
    private String keyword;
    private String code;
    private Integer page;
    private Integer pageSize;

    public Integer getPage() {
        return page == null ? 0 : page;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
