package com.vai.vmcapi.repo.jpa;

import com.vai.vmcapi.repo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> findAll(Specification<UserEntity> spec, Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameOrPhoneNum(String username, String phoneNum);
}
