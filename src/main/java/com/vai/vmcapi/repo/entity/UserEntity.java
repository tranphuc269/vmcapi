package com.vai.vmcapi.repo.entity;

import com.vai.vmcapi.domain.dto.user.UserVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")

public class UserEntity extends BaseEntity {
    private String username;

    private String password;

    private String phoneNum;

    private String fullname;
    private String role;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean lock;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private WardEntity ward;
    @Column(name = "ward_id", insertable = false, updatable = false)
    private Long wardId;


    public UserVO toVO() {
        return UserVO
                .builder()
                .id(this.getId())
                .username(this.getUsername())
                .phoneNum(this.getPhoneNum())
                .fullname(this.getFullname())
                .ward(this.getWard() == null ? null : this.getWard().toDto())
                .role(this.getRole())
                .build();
    }
}
