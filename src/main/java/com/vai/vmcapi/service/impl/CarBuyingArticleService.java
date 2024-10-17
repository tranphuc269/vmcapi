package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.article.CarBuyingArticleDTO;
import com.vai.vmcapi.repo.entity.CarBuyingArticleEntity;
import com.vai.vmcapi.repo.jpa.CarBuyingArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarBuyingArticleService {

    private final CarBuyingArticleRepository carBuyingArticleRepository;

    public CarBuyingArticleService(CarBuyingArticleRepository carBuyingArticleRepository) {
        this.carBuyingArticleRepository = carBuyingArticleRepository;
    }

    public CarBuyingArticleDTO createCarBuyingArticle(CarBuyingArticleDTO carBuyingArticleDTO) {
        if (carBuyingArticleDTO.getMax() < carBuyingArticleDTO.getMin()) {
            throw new IllegalArgumentException("Max must be greater than or equal to Min");
        }
        CarBuyingArticleEntity carBuyingArticleEntity = convertToEntity(carBuyingArticleDTO);
        CarBuyingArticleEntity savedEntity = carBuyingArticleRepository.save(carBuyingArticleEntity);
        return convertToDTO(savedEntity);
    }

    public List<CarBuyingArticleDTO> getAllCarBuyingArticles() {
        List<CarBuyingArticleEntity> allEntities = carBuyingArticleRepository.findAll();
        return allEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CarBuyingArticleDTO updateCarBuyingArticle(Long id, CarBuyingArticleDTO carBuyingArticleDTO) {
        if (carBuyingArticleDTO.getMax() < carBuyingArticleDTO.getMin()) {
            throw new IllegalArgumentException("Max must be greater than or equal to Min");
        }
        CarBuyingArticleEntity existingEntity = carBuyingArticleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarBuyingArticle not found"));
        existingEntity = updateEntityFields(existingEntity, carBuyingArticleDTO);
        CarBuyingArticleEntity savedEntity = carBuyingArticleRepository.save(existingEntity);
        return convertToDTO(savedEntity);
    }

    public void deleteCarBuyingArticle(Long id) {
        carBuyingArticleRepository.deleteById(id);
    }

    private CarBuyingArticleEntity convertToEntity(CarBuyingArticleDTO carBuyingArticleDTO) {
        return CarBuyingArticleEntity
                .builder()
                .min(carBuyingArticleDTO.getMin())
                .max(carBuyingArticleDTO.getMax())
                .title(carBuyingArticleDTO.getTitle())
                .content(carBuyingArticleDTO.getContent())
                .build();
    }

    private CarBuyingArticleDTO convertToDTO(CarBuyingArticleEntity carBuyingArticleEntity) {
        return CarBuyingArticleDTO
                .builder()
                .id(carBuyingArticleEntity.getId())
                .min(carBuyingArticleEntity.getMin())
                .max(carBuyingArticleEntity.getMax())
                .title(carBuyingArticleEntity.getTitle())
                .content(carBuyingArticleEntity.getContent())
                .createdAt(carBuyingArticleEntity.getCreatedAt())
                .updatedAt(carBuyingArticleEntity.getUpdatedAt())
                .build();
    }

    private CarBuyingArticleEntity updateEntityFields(CarBuyingArticleEntity existingEntity, CarBuyingArticleDTO carBuyingArticleDTO) {
        existingEntity.setMin(carBuyingArticleDTO.getMin());
        existingEntity.setMax(carBuyingArticleDTO.getMax());
        existingEntity.setTitle(carBuyingArticleDTO.getTitle());
        existingEntity.setContent(carBuyingArticleDTO.getContent());
        return existingEntity;
    }
}