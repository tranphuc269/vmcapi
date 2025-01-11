package com.vai.vmcapi.controller;


import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.admin.AdminReportDTO;
import com.vai.vmcapi.domain.dto.admin.AdminSearchParams;
import com.vai.vmcapi.domain.dto.car.CarDTO;
import com.vai.vmcapi.domain.dto.user.UserVO;
import com.vai.vmcapi.service.impl.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    @PostMapping("/users")
    public ResponseDTO<PageableResponse<UserVO>> getUsers(@RequestBody AdminSearchParams params) {
        return ResponseDTO.success(adminService.getUsers(params));
    }

    @PostMapping("/cars")
    public ResponseDTO<PageableResponse<CarDTO>> getCars(@RequestBody AdminSearchParams params) {
        return ResponseDTO.success(adminService.getCars(params));
    }

    @GetMapping("/users/{id}")
    public ResponseDTO<UserVO> detailUser(@PathVariable Long id) {
        return ResponseDTO.success(adminService.detailUser(id));
    }

    @PutMapping("/users/{id}/lock")
    public ResponseDTO<Boolean> lockUser(@PathVariable Long id) {
        return ResponseDTO.success(adminService.lockUser(id));
    }

    @PutMapping("/users/{id}/unlock")
    public ResponseDTO<Boolean> unlockUser(@PathVariable Long id) {
        return ResponseDTO.success(adminService.unlockUser(id));
    }

    @GetMapping("/users/{id}/cars")
    public ResponseDTO<List<CarDTO>> getCarsByUser(@PathVariable Long id) {
        return ResponseDTO.success(adminService.getCarsByUser(id));
    }


    @PutMapping("/users/{id}/lock-car")
    public ResponseDTO<Boolean> lockCar(@PathVariable Long id) {
        return ResponseDTO.success(adminService.lockCar(id));
    }

    @PutMapping("/users/{id}/unlock-car")
    public ResponseDTO<Boolean> unLockCar(@PathVariable Long id) {
        return ResponseDTO.success(adminService.unLockCar(id));
    }

    @GetMapping("/reports/in-day")
    public ResponseDTO<AdminReportDTO> getReportInDay() {
        return ResponseDTO.success(adminService.getReportInDay());
    }
}
