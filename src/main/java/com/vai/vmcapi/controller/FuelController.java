package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.fuel.FuelDTO;
import com.vai.vmcapi.service.impl.FuelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fuels")
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @PostMapping
    public ResponseDTO<FuelDTO> createFuel(@RequestBody FuelDTO fuelDTO) {
        return ResponseDTO.ok(fuelService.createFuel(fuelDTO));
    }

    @GetMapping
    public ResponseDTO<List<FuelDTO>> getAllFuels() {
        return ResponseDTO.ok(fuelService.getAllFuels());
    }

    @PutMapping("/{id}")
    public ResponseDTO<FuelDTO> updateFuel(@PathVariable Long id, @RequestBody FuelDTO fuelDTO) {
        return ResponseDTO.ok(
                fuelService.updateFuel(id, fuelDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuel(@PathVariable Long id) {
        fuelService.deleteFuel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}