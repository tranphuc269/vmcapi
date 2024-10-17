package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.branch.BranchDTO;
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

    public BranchDTO createBranch(BranchDTO branchDTO) {
        // Convert DTO to Entity
        BrandEntity brandEntity = convertToEntity(branchDTO);

        // Save the entity
        BrandEntity savedEntity = brandRepository.save(brandEntity);

        // Convert the saved entity back to DTO and return
        return convertToDTO(savedEntity);
    }

    public List<BranchDTO> getAllBranches() {
        // Fetch all entities
        List<BrandEntity> allEntities = brandRepository.findAll();

        // Convert all entities to DTOs and return
        return allEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) {
        // Fetch entity by id
        BrandEntity existingEntity = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        // Update fields
        updateEntityFields(existingEntity, branchDTO);

        // Save and return updated DTO
        BrandEntity savedEntity = brandRepository.save(existingEntity);
        return convertToDTO(savedEntity);
    }

    public void deleteBranch(Long id) {
        // Delete entity by id
        brandRepository.deleteById(id);
    }

    private BrandEntity convertToEntity(BranchDTO branchDTO) {
        // Implement conversion logic
        return BrandEntity
                .builder()
                .name(branchDTO.getName())
                .logo(branchDTO.getLogo())
                .build();
    }

    private BranchDTO convertToDTO(BrandEntity brandEntity) {
        // Implement conversion logic
        return BranchDTO
                .builder()
                .id(brandEntity.getId())
                .name(brandEntity.getName())
                .logo(brandEntity.getLogo())
                .createdAt(brandEntity.getCreatedAt())
                .updatedAt(brandEntity.getUpdatedAt())
                .build();
    }

    private BrandEntity updateEntityFields(BrandEntity existingEntity, BranchDTO branchDTO) {
        // Implement update logic
        existingEntity.setName(branchDTO.getName());
        existingEntity.setLogo(branchDTO.getLogo());
        return existingEntity;
    }
}
