package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.FuelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends JpaRepository<FuelEntity, Long> {
}