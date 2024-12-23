package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.car.CarDTO;
import com.vai.vmcapi.domain.dto.car.QueryCarParams;
import com.vai.vmcapi.domain.dto.car.UpSertCarRequest;
import com.vai.vmcapi.security.CurrentUser;
import com.vai.vmcapi.security.UserContext;
import com.vai.vmcapi.service.impl.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseDTO<CarDTO> createCar(@CurrentUser UserContext userContext, @RequestBody UpSertCarRequest request) {
        return ResponseDTO.success(carService.createCar(userContext, request));
    }

    @GetMapping("/{slug}")
    public ResponseDTO<CarDTO> getCarBySlug(@PathVariable String slug) {
        return ResponseDTO.success(carService.findBySlug(slug));
    }

    @PostMapping("/query")
    public ResponseDTO<PageableResponse<CarDTO>> getAllCars(@RequestBody QueryCarParams params) {
        return ResponseDTO.success(carService.queryCars(params));
    }

    @GetMapping("/{userId}/users")
    public ResponseDTO<List<CarDTO>> getCarByUserCreated(@PathVariable Long userId){
        return ResponseDTO.success(carService.getCarByUserCreated(userId));
    }

    @PutMapping("/{id}")
    public ResponseDTO<CarDTO> updateCar(@CurrentUser UserContext userContext, @PathVariable Long id, @RequestBody UpSertCarRequest request) {
        return ResponseDTO.success(carService.updateCar(userContext, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
