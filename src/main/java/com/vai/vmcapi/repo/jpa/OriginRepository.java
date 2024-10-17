package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.OriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<OriginEntity, Long> {
}