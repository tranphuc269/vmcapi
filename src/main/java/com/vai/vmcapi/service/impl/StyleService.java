package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.style.StyleDTO;
import com.vai.vmcapi.repo.entity.StyleEntity;
import com.vai.vmcapi.repo.jpa.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public StyleDTO createStyle(StyleDTO styleDTO) {
        StyleEntity styleEntity = convertToEntity(styleDTO);
        StyleEntity savedEntity = styleRepository.save(styleEntity);
        return savedEntity.toDto();
    }

    public List<StyleDTO> getAllStyles() {
        List<StyleEntity> allEntities = styleRepository.findAll();
        return allEntities.stream()
                .map(StyleEntity::toDto)
                .collect(Collectors.toList());
    }

    public StyleDTO updateStyle(Long id, StyleDTO styleDTO) {
        StyleEntity existingEntity = styleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Style not found"));
        existingEntity = updateEntityFields(existingEntity, styleDTO);
        StyleEntity savedEntity = styleRepository.save(existingEntity);
        return savedEntity.toDto();
    }

    public void deleteStyle(Long id) {
        styleRepository.deleteById(id);
    }

    private StyleEntity convertToEntity(StyleDTO styleDTO) {
        return StyleEntity
                .builder()
                .name(styleDTO.getName())
                .build();
    }


    private StyleEntity updateEntityFields(StyleEntity existingEntity, StyleDTO styleDTO) {
        existingEntity.setName(styleDTO.getName());
        return existingEntity;
    }
}