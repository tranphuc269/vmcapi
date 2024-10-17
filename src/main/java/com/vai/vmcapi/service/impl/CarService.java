package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.car.CarDTO;
import com.vai.vmcapi.repo.entity.CarEntity;
import com.vai.vmcapi.repo.jpa.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDTO createCar(CarDTO carDTO) {
        CarEntity carEntity = convertToEntity(carDTO);
        CarEntity savedEntity = carRepository.save(carEntity);
        return convertToDTO(savedEntity);
    }

    public List<CarDTO> getAllCars() {
        List<CarEntity> allEntities = carRepository.findAll();
        return allEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        CarEntity existingEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        existingEntity = updateEntityFields(existingEntity, carDTO);
        CarEntity savedEntity = carRepository.save(existingEntity);
        return convertToDTO(savedEntity);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private CarEntity convertToEntity(CarDTO carDTO) {
        return CarEntity
                .builder()
                .name(carDTO.getName())
                .logo(carDTO.getLogo())
                .branchId(carDTO.getBranchId())
                .styleId(carDTO.getStyleId())
                .originId(carDTO.getOriginId())
                .fuelId(carDTO.getFuelId())
                .outsideColorId(carDTO.getOutsideColorId())
                .insideColorId(carDTO.getInsideColorId())
                .build();
    }

    private CarDTO convertToDTO(CarEntity carEntity) {
        return CarDTO
                .builder()
                .id(carEntity.getId())
                .name(carEntity.getName())
                .logo(carEntity.getLogo())
                .branchId(carEntity.getBranchId())
                .styleId(carEntity.getStyleId())
                .originId(carEntity.getOriginId())
                .fuelId(carEntity.getFuelId())
                .outsideColorId(carEntity.getOutsideColorId())
                .insideColorId(carEntity.getInsideColorId())
                .createdAt(carEntity.getCreatedAt())
                .updatedAt(carEntity.getUpdatedAt())
                .build();
    }

    private CarEntity updateEntityFields(CarEntity existingEntity, CarDTO carDTO) {
        existingEntity.setName(carDTO.getName());
        existingEntity.setLogo(carDTO.getLogo());
        existingEntity.setBranchId(carDTO.getBranchId());
        existingEntity.setStyleId(carDTO.getStyleId());
        existingEntity.setOriginId(carDTO.getOriginId());
        existingEntity.setFuelId(carDTO.getFuelId());
        existingEntity.setOutsideColorId(carDTO.getOutsideColorId());
        existingEntity.setInsideColorId(carDTO.getInsideColorId());
        return existingEntity;
    }
}