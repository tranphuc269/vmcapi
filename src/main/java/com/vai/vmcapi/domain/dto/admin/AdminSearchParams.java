package com.vai.vmcapi.domain.dto.admin;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class AdminSearchParams {
    private String keywords;
    private List<SortItem> sortItems;
    private Integer page;
    private Integer pageSize;

    @Data
    public static class SortItem {
        private String field;
        private boolean isDesc;
    }
}
