package com.vai.vmcapi.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class PageableResponse <T>{
    private List<T> list;
    private Integer page;
    private Integer pageSize;
    private Integer totalSize;
}
