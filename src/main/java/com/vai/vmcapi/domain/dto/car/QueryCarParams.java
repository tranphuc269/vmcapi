package com.vai.vmcapi.domain.dto.car;

import lombok.Data;

import java.util.List;

@Data
public class QueryCarParams {
    private Long brandId;
    private Long styleId;
    private Long originId;
    private Long fuelId;
    private Long outsideColorId;
    private Long insideColorId;
    private Long cityId;
    private Long districtId;
    private Long wardId;
    private Integer page;
    private Integer pageSize;
    private Long minPrice;
    private Long maxPrice;
    private List<SortItem> sortItems;

    @Data
    public static class SortItem {
        private String field;
        private boolean isDesc;
    }
}