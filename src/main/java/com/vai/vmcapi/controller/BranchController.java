package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vai.vmcapi.domain.dto.branch.BranchDTO;
import com.vai.vmcapi.service.impl.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseDTO<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) {
        return ResponseDTO.ok(branchService.createBranch(branchDTO));
    }

    @GetMapping
    public ResponseDTO<List<BranchDTO>> getAllBranches() {
        return ResponseDTO.ok(branchService.getAllBranches());
    }

    @PutMapping("/{id}")
    public ResponseDTO<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) {
        return ResponseDTO.ok(
                branchService.updateBranch(id, branchDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}