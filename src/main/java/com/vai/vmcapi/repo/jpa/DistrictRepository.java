package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.DistrictEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends CrudRepository<DistrictEntity, Long> {

    List<DistrictEntity> findByParentCodeOrderByCode(String cityCode);
}
