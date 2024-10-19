package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.fuel.FuelDTO;
import com.vai.vmcapi.repo.entity.FuelEntity;
import com.vai.vmcapi.repo.jpa.FuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelService {

    private final FuelRepository fuelRepository;

    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    public FuelDTO createFuel(FuelDTO fuelDTO) {
        FuelEntity fuelEntity = convertToEntity(fuelDTO);
        FuelEntity savedEntity = fuelRepository.save(fuelEntity);
        return savedEntity.toDto();
    }

    public List<FuelDTO> getAllFuels() {
        List<FuelEntity> allEntities = fuelRepository.findAll();
        return allEntities.stream()
                .map(FuelEntity::toDto)
                .collect(Collectors.toList());
    }

    public FuelDTO updateFuel(Long id, FuelDTO fuelDTO) {
        FuelEntity existingEntity = fuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found"));
        existingEntity = updateEntityFields(existingEntity, fuelDTO);
        FuelEntity savedEntity = fuelRepository.save(existingEntity);
        return savedEntity.toDto();
    }

    public void deleteFuel(Long id) {
        fuelRepository.deleteById(id);
    }

    private FuelEntity convertToEntity(FuelDTO fuelDTO) {
        return FuelEntity
                .builder()
                .name(fuelDTO.getName())
                .build();
    }


    private FuelEntity updateEntityFields(FuelEntity existingEntity, FuelDTO fuelDTO) {
        existingEntity.setName(fuelDTO.getName());
        return existingEntity;
    }
}