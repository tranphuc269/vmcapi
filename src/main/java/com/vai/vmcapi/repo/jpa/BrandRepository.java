package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    List<BrandEntity> findAllByOrderByOrderNum();
}