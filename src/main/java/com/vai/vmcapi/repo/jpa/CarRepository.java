package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Page<CarEntity> findAll(Specification<CarEntity> spec, Pageable pageable);
    Optional<CarEntity> findBySlug(String slug);
    List<CarEntity> findByCreatedBy(Long userCreatedId);
}