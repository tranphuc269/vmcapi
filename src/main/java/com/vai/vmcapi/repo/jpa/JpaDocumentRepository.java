package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaDocumentRepository extends JpaRepository<DocumentEntity, Long> {
    Page<DocumentEntity> findByDossierIdAndNameContainingOrderByCreatedAt(Long dossierId,
                                                                          String name,
                                                                          Pageable pageable);
}
