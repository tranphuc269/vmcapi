package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.branch.BranchDTO;
import com.vai.vmcapi.repo.entity.BranchEntity;
import com.vai.vmcapi.repo.jpa.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public BranchDTO createBranch(BranchDTO branchDTO) {
        // Convert DTO to Entity
        BranchEntity branchEntity = convertToEntity(branchDTO);

        // Save the entity
        BranchEntity savedEntity = branchRepository.save(branchEntity);

        // Convert the saved entity back to DTO and return
        return convertToDTO(savedEntity);
    }

    public List<BranchDTO> getAllBranches() {
        // Fetch all entities
        List<BranchEntity> allEntities = branchRepository.findAll();

        // Convert all entities to DTOs and return
        return allEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) {
        // Fetch entity by id
        BranchEntity existingEntity = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        // Update fields
        updateEntityFields(existingEntity, branchDTO);

        // Save and return updated DTO
        BranchEntity savedEntity = branchRepository.save(existingEntity);
        return convertToDTO(savedEntity);
    }

    public void deleteBranch(Long id) {
        // Delete entity by id
        branchRepository.deleteById(id);
    }

    private BranchEntity convertToEntity(BranchDTO branchDTO) {
        // Implement conversion logic
        return BranchEntity
                .builder()
                .name(branchDTO.getName())
                .logo(branchDTO.getLogo())
                .build();
    }

    private BranchDTO convertToDTO(BranchEntity branchEntity) {
        // Implement conversion logic
        return BranchDTO
                .builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .logo(branchEntity.getLogo())
                .createdAt(branchEntity.getCreatedAt())
                .updatedAt(branchEntity.getUpdatedAt())
                .build();
    }

    private BranchEntity updateEntityFields(BranchEntity existingEntity, BranchDTO branchDTO) {
        // Implement update logic
        existingEntity.setName(branchDTO.getName());
        existingEntity.setLogo(branchDTO.getLogo());
        return existingEntity;
    }
}
