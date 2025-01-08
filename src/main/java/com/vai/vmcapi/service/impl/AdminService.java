package com.vai.vmcapi.service.impl;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.admin.AdminSearchParams;
import com.vai.vmcapi.domain.dto.car.CarDTO;
import com.vai.vmcapi.domain.dto.user.UserVO;
import com.vai.vmcapi.repo.entity.CarEntity;
import com.vai.vmcapi.repo.entity.UserEntity;
import com.vai.vmcapi.repo.jpa.CarRepository;
import com.vai.vmcapi.repo.jpa.UserRepository;
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
public class AdminService {

    @Resource
    private CarRepository carRepository;

    @Resource
    private UserRepository userRepository;

    public PageableResponse<UserVO> getUsers(AdminSearchParams params) {
        Pageable pageable = PageRequest.of(params.getPage(), params.getPageSize(), getSort(params.getSortItems()));
        Specification<UserEntity> spec = getSpecificationUser(params);
        Page<UserEntity> userPage = userRepository.findAll(spec, pageable);

        List<UserVO> userVOS = userPage.getContent().stream()
                .map(UserEntity::toVO)
                .toList();

        return PageableResponse.<UserVO>builder()
                .list(userVOS)
                .page(params.getPage())
                .pageSize(params.getPageSize())
                .totalSize((int) userPage.getTotalElements())
                .build();
    }

    public PageableResponse<CarDTO> getCars(AdminSearchParams params) {
        Pageable pageable = PageRequest.of(params.getPage(), params.getPageSize(), getSort(params.getSortItems()));
        Specification<CarEntity> spec = getSpecificationCar(params);
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

    private Sort getSort(List<AdminSearchParams.SortItem> sortItems) {
        if (sortItems == null || sortItems.isEmpty()) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        for (AdminSearchParams.SortItem sortItem : sortItems) {
            Sort.Order order = new Sort.Order(sortItem.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, sortItem.getField());
            orders.add(order);
        }
        return Sort.by(orders);
    }

    private Specification<UserEntity> getSpecificationUser(AdminSearchParams params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getKeywords() != null && !params.getKeywords().isEmpty()) {
                String keyword = "%" + params.getKeywords().toLowerCase() + "%";
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), keyword);
                Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("fullname")), keyword);
                Predicate codePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("phoneNum")), keyword);
                predicates.add(criteriaBuilder.or(namePredicate, descriptionPredicate, codePredicate));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<CarEntity> getSpecificationCar(AdminSearchParams params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getKeywords() != null && !params.getKeywords().isEmpty()) {
                String keyword = "%" + params.getKeywords().toLowerCase() + "%";
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), keyword);
                Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), keyword);
                Predicate codePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), keyword);
                predicates.add(criteriaBuilder.or(namePredicate, descriptionPredicate, codePredicate));
            }
            predicates.add(criteriaBuilder.equal(root.get("isPublish"), 1));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    public UserVO detailUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            return userEntity.toVO();
        }
        return null;
    }


    public Boolean lockUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            userEntity.setLock(true);
            userRepository.save(userEntity);
        }
        return true;
    }


    public Boolean unlockUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            userEntity.setLock(false);
            userRepository.save(userEntity);
        }
        return true;
    }

    public List<CarDTO> getCarsByUser(Long id) {
        return carRepository.findByCreatedBy(id).stream().map(CarEntity::toDto).toList();
    }

    public Boolean lockCar(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElse(null);
        if (carEntity != null) {
            carEntity.setLock(Boolean.TRUE);
            carRepository.save(carEntity);
        }
        return true;
    }

    public Boolean unLockCar(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElse(null);
        if (carEntity != null) {
            carEntity.setLock(Boolean.FALSE);
            carRepository.save(carEntity);
        }
        return true;
    }
}
