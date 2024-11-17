package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.article.CarBuyingArticleDTO;
import com.vai.vmcapi.domain.dto.car_article.QueryCarArticleParams;
import com.vai.vmcapi.repo.entity.CarBuyingArticleEntity;
import com.vai.vmcapi.repo.jpa.CarBuyingArticleRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return savedEntity.toDto();
    }

    public List<CarBuyingArticleDTO> getAllCarBuyingArticles() {
        List<CarBuyingArticleEntity> allEntities = carBuyingArticleRepository.findAll();
        return allEntities.stream()
                .map(CarBuyingArticleEntity::toDto)
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
        return savedEntity.toDto();
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
                .code(carBuyingArticleDTO.getCode())
                .build();
    }

    private CarBuyingArticleEntity updateEntityFields(CarBuyingArticleEntity existingEntity, CarBuyingArticleDTO carBuyingArticleDTO) {
        existingEntity.setMin(carBuyingArticleDTO.getMin());
        existingEntity.setMax(carBuyingArticleDTO.getMax());
        existingEntity.setTitle(carBuyingArticleDTO.getTitle());
        existingEntity.setContent(carBuyingArticleDTO.getContent());
        return existingEntity;
    }

    public PageableResponse<CarBuyingArticleDTO> queryArticle(QueryCarArticleParams params) {
        Pageable pageable = PageRequest.of(params.getPage(), params.getPageSize());

        Specification<CarBuyingArticleEntity> spec = getSpecification(params);
        Page<CarBuyingArticleEntity> carBuyingPage = carBuyingArticleRepository.findAll(spec, pageable);
        List<CarBuyingArticleDTO> dtos = carBuyingPage.getContent().stream()
                .map(CarBuyingArticleEntity::toDto)
                .collect(Collectors.toList());
        return PageableResponse.<CarBuyingArticleDTO>builder()
                .list(dtos)
                .page(params.getPage())
                .pageSize(params.getPageSize())
                .totalSize((int) carBuyingPage.getTotalElements())
                .build();
    }

    private Specification<CarBuyingArticleEntity> getSpecification(QueryCarArticleParams params) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (params.getMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("min"), params.getMin()));
            }
            if (params.getMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("max"), params.getMax()));
            }
            if (params.getKeyword() != null) {
                predicates.add(cb.like(root.get("title"), "%" + params.getKeyword() + "%"));
            }
            if (params.getCode() != null) {
                predicates.add(cb.equal(root.get("code"), params.getCode()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}