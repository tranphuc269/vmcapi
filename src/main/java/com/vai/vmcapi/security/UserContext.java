package com.vai.vmcapi.security;

import com.vai.vmcapi.repo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@SuperBuilder
@AllArgsConstructor
@Getter
@Setter
public class UserContext implements UserDetails {
    private String username;
    private Long id;

    public static UserContext fromEntity(UserEntity userEntity) {
        return UserContext
                .builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
