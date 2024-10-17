package com.vai.vmcapi.repo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDossierRepository extends JpaRepository<DossierEntity, Long> {

}
