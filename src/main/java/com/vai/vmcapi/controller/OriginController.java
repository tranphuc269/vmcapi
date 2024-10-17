package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.origin.OriginDTO;
import com.vai.vmcapi.service.impl.OriginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/origins")
public class OriginController {

    private final OriginService originService;

    public OriginController(OriginService originService) {
        this.originService = originService;
    }

    @PostMapping
    public ResponseDTO<OriginDTO> createOrigin(@RequestBody OriginDTO originDTO) {
        return ResponseDTO.ok(originService.createOrigin(originDTO));
    }

    @GetMapping
    public ResponseDTO<List<OriginDTO>> getAllOrigins() {
        return ResponseDTO.ok(originService.getAllOrigins());
    }

    @PutMapping("/{id}")
    public ResponseDTO<OriginDTO> updateOrigin(@PathVariable Long id, @RequestBody OriginDTO originDTO) {
        return ResponseDTO.ok(
                originService.updateOrigin(id, originDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrigin(@PathVariable Long id) {
        originService.deleteOrigin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}