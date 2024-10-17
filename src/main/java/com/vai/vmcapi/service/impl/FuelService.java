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
        return convertToDTO(savedEntity);
    }

    public List<FuelDTO> getAllFuels() {
        List<FuelEntity> allEntities = fuelRepository.findAll();
        return allEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FuelDTO updateFuel(Long id, FuelDTO fuelDTO) {
        FuelEntity existingEntity = fuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found"));
        existingEntity = updateEntityFields(existingEntity, fuelDTO);
        FuelEntity savedEntity = fuelRepository.save(existingEntity);
        return convertToDTO(savedEntity);
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

    private FuelDTO convertToDTO(FuelEntity fuelEntity) {
        return FuelDTO
                .builder()
                .id(fuelEntity.getId())
                .name(fuelEntity.getName())
                .createdAt(fuelEntity.getCreatedAt())
                .updatedAt(fuelEntity.getUpdatedAt())
                .build();
    }

    private FuelEntity updateEntityFields(FuelEntity existingEntity, FuelDTO fuelDTO) {
        existingEntity.setName(fuelDTO.getName());
        return existingEntity;
    }
}