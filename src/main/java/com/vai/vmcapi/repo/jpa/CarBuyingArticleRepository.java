package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.CarBuyingArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBuyingArticleRepository extends JpaRepository<CarBuyingArticleEntity, Long> {


    Page<CarBuyingArticleEntity> findAll(Specification<CarBuyingArticleEntity> spec, Pageable pageable);
}