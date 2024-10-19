package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.car.CarDTO;
import com.vai.vmcapi.domain.dto.car.QueryCarParams;
import com.vai.vmcapi.domain.dto.car.UpSertCarRequest;
import com.vai.vmcapi.repo.entity.CarEntity;
import com.vai.vmcapi.repo.jpa.CarRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDTO createCar(UpSertCarRequest carDTO) {
        CarEntity carEntity = convertToEntity(carDTO);
        CarEntity savedEntity = carRepository.save(carEntity);
        return savedEntity.toDto();
    }

    public List<CarDTO> getAllCars() {
        List<CarEntity> allEntities = carRepository.findAll();
        return allEntities.stream()
                .map(CarEntity::toDto)
                .collect(Collectors.toList());
    }

    public PageableResponse<CarDTO> queryCars(QueryCarParams params) {
        Pageable pageable = PageRequest.of(params.getPage(), params.getPageSize(), getSort(params.getSortItems()));
        Specification<CarEntity> spec = getSpecification(params);
        Page<CarEntity> carPage = carRepository.findAll(spec, pageable);

        List<CarDTO> carDTOs = carPage.getContent().stream()
                .map(CarEntity::toDto)
                .collect(Collectors.toList());

        return PageableResponse.<CarDTO>builder()
                .list(carDTOs)
                .page(params.getPage())
                .pageSize(params.getPageSize())
                .totalSize((int) carPage.getTotalElements())
                .build();
    }

    public CarDTO updateCar(Long id, UpSertCarRequest carDTO) {
        CarEntity existingEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        updateEntityFields(existingEntity, carDTO);
        CarEntity savedEntity = carRepository.save(existingEntity);
        return savedEntity.toDto();
    }

    private Sort getSort(List<QueryCarParams.SortItem> sortItems) {
        if (sortItems == null || sortItems.isEmpty()) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        for (QueryCarParams.SortItem sortItem : sortItems) {
            Sort.Order order = new Sort.Order(sortItem.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, sortItem.getField());
            orders.add(order);
        }
        return Sort.by(orders);
    }

    private Specification<CarEntity> getSpecification(QueryCarParams params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getBrandId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("brandId"), params.getBrandId()));
            }
            if (params.getStyleId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("styleId"), params.getStyleId()));
            }
            if (params.getOriginId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("originId"), params.getOriginId()));
            }
            if (params.getFuelId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("fuelId"), params.getFuelId()));
            }
            if (params.getOutsideColorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("outsideColorId"), params.getOutsideColorId()));
            }
            if (params.getInsideColorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("insideColorId"), params.getInsideColorId()));
            }
            if (params.getCityId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cityId"), params.getCityId()));
            }
            if (params.getDistrictId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("districtId"), params.getDistrictId()));
            }
            if (params.getWardId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("wardId"), params.getWardId()));
            }
            if (params.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), params.getMinPrice()));
            }
            if (params.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), params.getMaxPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private CarEntity convertToEntity(UpSertCarRequest carDTO) {
        return CarEntity
                .builder()
                .name(carDTO.getName())
                .logo(carDTO.getLogo())
                .brandId(carDTO.getBrandId())
                .styleId(carDTO.getStyleId())
                .originId(carDTO.getOriginId())
                .fuelId(carDTO.getFuelId())
                .outsideColorId(carDTO.getOutsideColorId())
                .insideColorId(carDTO.getInsideColorId())
                .cityId(carDTO.getCityId())
                .districtId(carDTO.getDistrictId())
                .wardId(carDTO.getWardId())
                .address(carDTO.getAddress())
                .build();
    }

    private void updateEntityFields(CarEntity existingEntity, UpSertCarRequest carDTO) {
        existingEntity.setName(carDTO.getName());
        existingEntity.setLogo(carDTO.getLogo());
        existingEntity.setBrandId(carDTO.getBrandId());
        existingEntity.setStyleId(carDTO.getStyleId());
        existingEntity.setOriginId(carDTO.getOriginId());
        existingEntity.setFuelId(carDTO.getFuelId());
        existingEntity.setOutsideColorId(carDTO.getOutsideColorId());
        existingEntity.setInsideColorId(carDTO.getInsideColorId());
        existingEntity.setCityId(carDTO.getCityId());
        existingEntity.setDistrictId(carDTO.getDistrictId());
        existingEntity.setWardId(carDTO.getWardId());
        existingEntity.setAddress(carDTO.getAddress());
    }
}
