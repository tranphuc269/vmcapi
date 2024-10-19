package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.color.ColorDTO;
import com.vai.vmcapi.repo.entity.ColorEntity;
import com.vai.vmcapi.repo.jpa.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public ColorDTO createColor(ColorDTO colorDTO) {
        ColorEntity colorEntity = convertToEntity(colorDTO);
        ColorEntity savedEntity = colorRepository.save(colorEntity);
        return savedEntity.toDto();
    }

    public List<ColorDTO> getAllColors() {
        List<ColorEntity> allEntities = colorRepository.findAll();
        return allEntities.stream()
                .map(ColorEntity::toDto)
                .collect(Collectors.toList());
    }

    public ColorDTO updateColor(Long id, ColorDTO colorDTO) {
        ColorEntity existingEntity = colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Color not found"));
        existingEntity = updateEntityFields(existingEntity, colorDTO);
        ColorEntity savedEntity = colorRepository.save(existingEntity);
        return savedEntity.toDto();
    }

    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }

    private ColorEntity convertToEntity(ColorDTO colorDTO) {
        return ColorEntity
                .builder()
                .name(colorDTO.getName())
                .hex(colorDTO.getHex())
                .build();
    }


    private ColorEntity updateEntityFields(ColorEntity existingEntity, ColorDTO colorDTO) {
        existingEntity.setName(colorDTO.getName());
        existingEntity.setHex(colorDTO.getHex());
        return existingEntity;
    }
}