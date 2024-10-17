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

public class UserEntity extends BaseEntity {
    private String username;

    private String password;

    public UserVO toVO() {
        return UserVO
                .builder()
                .id(this.getId())
                .username(this.getUsername())
                .build();
    }
}
