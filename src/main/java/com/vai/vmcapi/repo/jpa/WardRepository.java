package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.WardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends CrudRepository<WardEntity, Long> {

    List<WardEntity> findByParentCodeOrderByCode(String districtCode);
}
