package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.address.AddressDTO;
import com.vai.vmcapi.service.impl.AddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @GetMapping("/cities")
    public ResponseDTO<List<AddressDTO>> getCities() {
        return ResponseDTO.success(addressService.getCities());
    }

    @GetMapping("/districts")
    public ResponseDTO<List<AddressDTO>> getDistricts(@RequestParam String cityCode) {
        return ResponseDTO.success(addressService.getDistrictByCityCode(cityCode));
    }

    @GetMapping("/wards")
    public ResponseDTO<List<AddressDTO>> getWards(@RequestParam String districtCode) {
        return ResponseDTO.success(addressService.getWardByDistrictCode(districtCode));
    }
}
