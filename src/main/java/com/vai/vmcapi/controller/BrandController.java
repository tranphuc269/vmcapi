package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vai.vmcapi.domain.dto.branch.BranchDTO;
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
    public ResponseDTO<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) {
        return ResponseDTO.ok(brandService.createBranch(branchDTO));
    }

    @GetMapping
    public ResponseDTO<List<BranchDTO>> getAllBranches() {
        return ResponseDTO.ok(brandService.getAllBranches());
    }

    @PutMapping("/{id}")
    public ResponseDTO<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) {
        return ResponseDTO.ok(
                brandService.updateBranch(id, branchDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        brandService.deleteBranch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}