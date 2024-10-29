package com.vai.vmcapi.domain.dto.car;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@Builder
public class CarBrands {
    private Map<String, List<String>> brands;

    public CarBrands(Map<String, List<String>> brands) {
        this.brands = brands;
    }

}