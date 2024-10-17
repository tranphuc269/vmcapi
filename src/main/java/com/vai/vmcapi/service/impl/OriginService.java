package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.origin.OriginDTO;
import com.vai.vmcapi.repo.entity.OriginEntity;
import com.vai.vmcapi.repo.jpa.OriginRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OriginService {

    private final OriginRepository originRepository;

    public OriginService(OriginRepository originRepository) {
        this.originRepository = originRepository;
    }

    public OriginDTO createOrigin(OriginDTO originDTO) {
        OriginEntity originEntity = convertToEntity(originDTO);
        OriginEntity savedEntity = originRepository.save(originEntity);
        return convertToDTO(savedEntity);
    }

    public List<OriginDTO> getAllOrigins() {
        List<OriginEntity> allEntities = originRepository.findAll();
        return allEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OriginDTO updateOrigin(Long id, OriginDTO originDTO) {
        OriginEntity existingEntity = originRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Origin not found"));
        existingEntity = updateEntityFields(existingEntity, originDTO);
        OriginEntity savedEntity = originRepository.save(existingEntity);
        return convertToDTO(savedEntity);
    }

    public void deleteOrigin(Long id) {
        originRepository.deleteById(id);
    }

    private OriginEntity convertToEntity(OriginDTO originDTO) {
        return OriginEntity
                .builder()
                .name(originDTO.getName())
                .build();
    }

    private OriginDTO convertToDTO(OriginEntity originEntity) {
        return OriginDTO
                .builder()
                .id(originEntity.getId())
                .name(originEntity.getName())
                .createdAt(originEntity.getCreatedAt())
                .updatedAt(originEntity.getUpdatedAt())
                .build();
    }

    private OriginEntity updateEntityFields(OriginEntity existingEntity, OriginDTO originDTO) {
        existingEntity.setName(originDTO.getName());
        return existingEntity;
    }
}