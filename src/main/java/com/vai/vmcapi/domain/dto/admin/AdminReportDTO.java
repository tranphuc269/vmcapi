package com.vai.vmcapi.domain.dto.admin;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class AdminReportDTO {
    private Integer totalUserInDay;
    private Integer totalCarInDay;
}
