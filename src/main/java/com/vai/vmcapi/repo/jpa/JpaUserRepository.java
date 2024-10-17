package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<UserEntity> findByUsername(String username);
}
