package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.StyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {
}