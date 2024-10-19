package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.address.AddressDTO;
import com.vai.vmcapi.repo.entity.CityEntity;
import com.vai.vmcapi.repo.entity.DistrictEntity;
import com.vai.vmcapi.repo.entity.WardEntity;
import com.vai.vmcapi.repo.jpa.CityRepository;
import com.vai.vmcapi.repo.jpa.DistrictRepository;
import com.vai.vmcapi.repo.jpa.WardRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AddressService {
    @Resource
    private CityRepository cityRepository;
    @Resource
    private DistrictRepository districtRepository;
    @Resource
    private WardRepository wardRepository;

    public List<AddressDTO> getCities() {
        return cityRepository.findAllByOrderByCode().stream()
                .map(CityEntity::toDto)
                .collect(Collectors.toList());
    }

    public List<AddressDTO> getDistrictByCityCode(String cityCode) {
        return districtRepository.findByParentCodeOrderByCode(cityCode).stream()
                .map(DistrictEntity::toDto)
                .collect(Collectors.toList());
    }

    public List<AddressDTO> getWardByDistrictCode(String districtCode) {
        return wardRepository.findByParentCodeOrderByCode(districtCode).stream()
                .map(WardEntity::toDto)
                .collect(Collectors.toList());
    }
}
