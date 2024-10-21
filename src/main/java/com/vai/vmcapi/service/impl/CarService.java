package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.car.CarDTO;
import com.vai.vmcapi.domain.dto.car.QueryCarParams;
import com.vai.vmcapi.domain.dto.car.UpSertCarRequest;
import com.vai.vmcapi.domain.exception.BusinessException;
import com.vai.vmcapi.repo.entity.CarEntity;
import com.vai.vmcapi.repo.jpa.*;
import jakarta.annotation.Resource;
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

    @Resource
    private CarRepository carRepository;
    @Resource
    private ModelRepository modelRepository;

    @Resource
    private BrandRepository brandRepository;

    @Resource
    private StyleRepository styleRepository;

    @Resource
    private OriginRepository originRepository;

    @Resource
    private FuelRepository fuelRepository;

    @Resource
    private ColorRepository colorRepository;

    @Resource
    private CityRepository cityRepository;

    @Resource

    private DistrictRepository districtRepository;

    @Resource

    private WardRepository wardRepository;


    public CarDTO createCar(UpSertCarRequest carDTO) {
        carRepository.findBySlug(carDTO.getSlug())
                .ifPresent(entity -> {
                    throw new BusinessException("Car with slug " + carDTO.getSlug() + " already exists");
                });
        CarEntity carEntity = convertToEntity(carDTO);
        CarEntity savedEntity = carRepository.save(carEntity);
        return savedEntity.toDto();
    }

    public CarDTO findBySlug(String slug) {
        CarEntity carEntity = carRepository.findBySlug(slug)
                .orElseThrow(() -> new BusinessException("Car not found"));
        return carEntity.toDto();
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
                .orElseThrow(() -> new BusinessException("Car not found"));
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
                .description(carDTO.getDescription())
                .logo(carDTO.getLogo())
                .brand(brandRepository.findById(carDTO.getBrandId()).get())
                .style(styleRepository.findById(carDTO.getStyleId()).get())
                .origin(originRepository.findById(carDTO.getOriginId()).get())
                .fuel(fuelRepository.findById(carDTO.getFuelId()).get())
                .outsideColor(colorRepository.findById(carDTO.getOutsideColorId()).get())
                .insideColor(colorRepository.findById(carDTO.getInsideColorId()).get())
                .city(cityRepository.findById(carDTO.getCityId()).get())
                .district(districtRepository.findById(carDTO.getDistrictId()).get())
                .ward(wardRepository.findById(carDTO.getWardId()).get())
                .address(carDTO.getAddress())
                .price(carDTO.getPrice())
                .build();
    }

    private void updateEntityFields(CarEntity existingEntity, UpSertCarRequest carDTO) {
        existingEntity.setName(carDTO.getName());
        existingEntity.setDescription(carDTO.getDescription());
        existingEntity.setManufacturingYear(carDTO.getManufacturingYear());
        existingEntity.setVersion(carDTO.getVersion());
        existingEntity.setKmDriven(carDTO.getKmDriven());
        existingEntity.setSeatCapacity(carDTO.getSeatCapacity());
        existingEntity.setStatus(carDTO.getStatus());
        existingEntity.setTransmission(carDTO.getTransmission());
        existingEntity.setDrivetrain(carDTO.getDrivetrain());
        existingEntity.setImages(String.join(",", carDTO.getImages()));
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
        existingEntity.setPrice(carDTO.getPrice());
        existingEntity.setSlug(carDTO.getSlug());
    }
}
