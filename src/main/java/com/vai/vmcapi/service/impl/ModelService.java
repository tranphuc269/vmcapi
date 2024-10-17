package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.model.ModelDTO;
import com.vai.vmcapi.repo.entity.ModelEntity;
import com.vai.vmcapi.repo.jpa.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public ModelDTO createModel(ModelDTO modelDTO) {
        ModelEntity modelEntity = convertToEntity(modelDTO);
        ModelEntity savedEntity = modelRepository.save(modelEntity);
        return convertToDTO(savedEntity);
    }

    public List<ModelDTO> getAllModels() {
        List<ModelEntity> allEntities = modelRepository.findAll();
        return allEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ModelDTO updateModel(Long id, ModelDTO modelDTO) {
        ModelEntity existingEntity = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        existingEntity = updateEntityFields(existingEntity, modelDTO);
        ModelEntity savedEntity = modelRepository.save(existingEntity);
        return convertToDTO(savedEntity);
    }

    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }

    private ModelEntity convertToEntity(ModelDTO modelDTO) {
        return ModelEntity
                .builder()
                .name(modelDTO.getName())
                .branchId(modelDTO.getBranchId())
                .logo(modelDTO.getLogo())
                .build();
    }

    private ModelDTO convertToDTO(ModelEntity modelEntity) {
        return ModelDTO
                .builder()
                .id(modelEntity.getId())
                .name(modelEntity.getName())
                .branchId(modelEntity.getBranchId())
                .logo(modelEntity.getLogo())
                .createdAt(modelEntity.getCreatedAt())
                .updatedAt(modelEntity.getUpdatedAt())
                .build();
    }

    private ModelEntity updateEntityFields(ModelEntity existingEntity, ModelDTO modelDTO) {
        existingEntity.setName(modelDTO.getName());
        existingEntity.setBranchId(modelDTO.getBranchId());
        existingEntity.setLogo(modelDTO.getLogo());
        return existingEntity;
    }
}