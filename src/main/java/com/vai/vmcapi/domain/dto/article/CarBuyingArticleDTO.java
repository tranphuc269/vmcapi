package com.vai.vmcapi.domain.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vai.vmcapi.domain.dto.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class CarBuyingArticleDTO {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer min;
    private Integer max;
    private String title;
    private String content;
    private String code;
    private UserVO user;
}
