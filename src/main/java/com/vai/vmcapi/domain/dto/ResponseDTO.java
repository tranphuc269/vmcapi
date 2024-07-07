package com.vai.vmcapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ResponseDTO<V> {
    private Integer code;

    private String msg;

    private V data;


    public static <V> ResponseDTO<V> success(V v) {
        return new ResponseDTO<>(200, "ok", v);
    }
}
