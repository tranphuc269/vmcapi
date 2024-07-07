package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.user.UserVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")

public class UserEntity extends BaseEntityAudit {
    private String username;

    private String password;

    @OneToMany(mappedBy = "userCreated")
    private List<DossierEntity> dossiers;

    @OneToMany(mappedBy = "userCreated")
    private List<DocumentEntity> documents;

    public UserVO toVO() {
        return UserVO
                .builder()
                .username(this.getUsername())
                .build();
    }
}
