package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vai.vmcapi.domain.dto.brand.BrandDTO;
import com.vai.vmcapi.service.impl.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseDTO<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        return ResponseDTO.success(brandService.createBrand(brandDTO));
    }

    @GetMapping
    public ResponseDTO<List<BrandDTO>> getAllBrands() {
        return ResponseDTO.success(brandService.getAllBrands());
    }

    @PutMapping("/{id}")
    public ResponseDTO<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        return ResponseDTO.success(
                brandService.updateBrand(id, brandDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}