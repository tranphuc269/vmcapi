package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.car.CarBrands;
import com.vai.vmcapi.domain.dto.car.CarDTO;
import com.vai.vmcapi.domain.dto.car.QueryCarParams;
import com.vai.vmcapi.domain.dto.car.UpSertCarRequest;
import com.vai.vmcapi.domain.exception.BusinessException;
import com.vai.vmcapi.repo.entity.BrandEntity;
import com.vai.vmcapi.repo.entity.CarEntity;
import com.vai.vmcapi.repo.entity.ModelEntity;
import com.vai.vmcapi.repo.jpa.*;
import com.vai.vmcapi.security.UserContext;
import com.vai.vmcapi.utils.RandomStringGenerator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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

    @Resource
    private ResourceLoader resourceLoader;


    public CarDTO createCar(UserContext userContext, UpSertCarRequest carDTO) {
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

    public CarDTO updateCar(UserContext userContext, Long id, UpSertCarRequest carDTO) {
        CarEntity existingEntity = carRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Car not found"));
        if(!userContext.getId().equals(existingEntity.getCreatedBy())){
            throw new BusinessException("Can't update car", HttpStatus.FORBIDDEN);
        }
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
            if (params.getManufacturingYear() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("manufacturingYear"), params.getManufacturingYear()));
            }
            if (params.getVersion() != null) {
                predicates.add(criteriaBuilder.equal(root.get("version"), params.getVersion()));
            }
            if (params.getKmDriven() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("kmDriven"), params.getKmDriven()));
            }
            if (params.getSeatCapacity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("seatCapacity"), params.getSeatCapacity()));
            }
            if (params.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), params.getStatus()));
            }
            if (params.getTransmission() != null) {
                predicates.add(criteriaBuilder.equal(root.get("transmission"), params.getTransmission()));
            }
            if (params.getDrivetrain() != null) {
                predicates.add(criteriaBuilder.equal(root.get("drivetrain"), params.getDrivetrain()));
            }

            if (params.getKeyword() != null && !params.getKeyword().isEmpty()) {
                String keyword = "%" + params.getKeyword().toLowerCase() + "%";
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), keyword);
                Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), keyword);
                Predicate codePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), keyword);
                predicates.add(criteriaBuilder.or(namePredicate, descriptionPredicate, codePredicate));
            }
            predicates.add(criteriaBuilder.equal(root.get("isPublish"), 1));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void deleteCar(Long id) {
        CarEntity carEntity = carRepository.findById(id).get();
        carEntity.setDeleted(true);
        carRepository.save(carEntity);
    }

    private CarEntity convertToEntity(UpSertCarRequest carDTO) {
        return CarEntity
                .builder()
                .name(carDTO.getName())
                .code(RandomStringGenerator.generateUniqueString())
                .description(carDTO.getDescription())
                .logo(carDTO.getLogo())
                .images(String.join(",", carDTO.getImages()))
                .version(carDTO.getVersion())
                .kmDriven(carDTO.getKmDriven())
                .drivetrain(carDTO.getDrivetrain())
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
                .slug(carDTO.getSlug())
                .manufacturingYear(carDTO.getManufacturingYear())
                .seatCapacity(carDTO.getSeatCapacity())
                .status(carDTO.getStatus())
                .transmission(carDTO.getTransmission())
                .isPublish(carDTO.getIsPublish())
                .deleted(false)
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
        existingEntity.setIsPublish(carDTO.getIsPublish());
        existingEntity.setSlug(carDTO.getSlug());
    }

    @PostConstruct
    public void init() {
        List<BrandEntity> brandEntities = brandRepository.findAll();
        if (!brandEntities.isEmpty()) {
            return;
        }
        CarBrands carBrands;
        try {
            carBrands = loadCarBrandsFromExcel();
            System.out.println(carBrands);

            // create brand
            carBrands.getBrands().forEach((brandName, models) -> {
                BrandEntity brandEntity = BrandEntity.builder().name(brandName).build();
                brandRepository.save(brandEntity);

                // create models
                models.forEach(modelName -> {
                    ModelEntity modelEntity = ModelEntity
                            .builder()
                            .name(Objects.equals(modelName, "Khác") ? modelName : brandName + "-" + modelName)
                            .brandId(brandEntity.getId())
                            .build();
                    modelRepository.save(modelEntity);
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CarBrands loadCarBrandsFromExcel() throws IOException {
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:hangxe.xlsx");

        Map<String, List<String>> brands = new HashMap<>();
        try (InputStream file = resource.getInputStream();
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            Row headerRow = sheet.getRow(0); // Dòng đầu tiên chứa tên thương hiệu

            // Duyệt qua các cột để lấy tên thương hiệu
            for (int col = 0; col < headerRow.getPhysicalNumberOfCells(); col++) {
                Cell brandCell = headerRow.getCell(col);
                if (brandCell != null) {
                    String brand = brandCell.getStringCellValue();
                    List<String> models = new ArrayList<>();

                    // Duyệt qua các dòng để lấy các mẫu xe của từng thương hiệu
                    for (int row = 1; row <= sheet.getLastRowNum(); row++) {
                        Row modelRow = sheet.getRow(row);
                        Cell modelCell = modelRow.getCell(col);
                        if (modelCell != null && modelCell.getCellType() == CellType.STRING) {
                            String model = modelCell.getStringCellValue();
                            if (!model.isEmpty()) {
                                models.add(model);
                            }
                        }
                    }
                    brands.put(brand, models);
                }

            }
        }

        return new CarBrands(brands);
    }

    public List<CarDTO> getCarByUserCreated(Long userId) {
        return carRepository.findByCreatedBy(userId).stream().map(CarEntity::toDto).toList();
    }
}
