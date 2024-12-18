package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.brand.BrandDTO;
import com.vai.vmcapi.repo.entity.BrandEntity;
import com.vai.vmcapi.repo.jpa.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandDTO createBrand(BrandDTO brandDTO) {
        // Convert DTO to Entity
        BrandEntity brandEntity = convertToEntity(brandDTO);

        // Save the entity
        BrandEntity savedEntity = brandRepository.save(brandEntity);

        // Convert the saved entity back to DTO and return
        return savedEntity.toDto();
    }

    public List<BrandDTO> getAllBrands() {
        // Fetch all entities
        List<BrandEntity> allEntities = brandRepository.findAllByOrderByOrderNum();

        // Convert all entities to DTOs and return
        return allEntities.stream()
                .map(BrandEntity::toDto)
                .collect(Collectors.toList());
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        // Fetch entity by id
        BrandEntity existingEntity = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        // Update fields
        updateEntityFields(existingEntity, brandDTO);

        // Save and return updated DTO
        BrandEntity savedEntity = brandRepository.save(existingEntity);
        return savedEntity.toDto();
    }

    public void deleteBrand(Long id) {
        // Delete entity by id
        brandRepository.deleteById(id);
    }

    private BrandEntity convertToEntity(BrandDTO brandDTO) {
        // Implement conversion logic
        return BrandEntity
                .builder()
                .name(brandDTO.getName())
                .logo(brandDTO.getLogo())
                .build();
    }


    private BrandEntity updateEntityFields(BrandEntity existingEntity, BrandDTO brandDTO) {
        // Implement update logic
        existingEntity.setName(brandDTO.getName());
        existingEntity.setLogo(brandDTO.getLogo());
        return existingEntity;
    }
}
