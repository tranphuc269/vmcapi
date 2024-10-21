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
        return savedEntity.toDto();
    }

    public List<ModelDTO> getAllModels(Long brandId){
        List<ModelEntity> allEntities = modelRepository.findAllByBrandId(brandId);
        return allEntities.stream()
                .map(ModelEntity::toDto)
                .collect(Collectors.toList());
    }

    public ModelDTO updateModel(Long id, ModelDTO modelDTO) {
        ModelEntity existingEntity = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        updateEntityFields(existingEntity, modelDTO);
        ModelEntity savedEntity = modelRepository.save(existingEntity);
        return savedEntity.toDto();
    }

    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }

    private ModelEntity convertToEntity(ModelDTO modelDTO) {
        return ModelEntity
                .builder()
                .name(modelDTO.getName())
                .brandId(modelDTO.getBrandId())
                .logo(modelDTO.getLogo())
                .build();
    }

    private void updateEntityFields(ModelEntity existingEntity, ModelDTO modelDTO) {
        existingEntity.setName(modelDTO.getName());
        existingEntity.setBrandId(modelDTO.getBrandId());
        existingEntity.setLogo(modelDTO.getLogo());
    }
}