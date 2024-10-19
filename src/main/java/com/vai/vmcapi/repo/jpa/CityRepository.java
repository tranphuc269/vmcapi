package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {

    List<CityEntity> findAllByOrderByCode();

}
