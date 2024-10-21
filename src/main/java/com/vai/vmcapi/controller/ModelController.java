package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.model.ModelDTO;
import com.vai.vmcapi.service.impl.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseDTO<ModelDTO> createModel(@RequestBody ModelDTO modelDTO) {
        return ResponseDTO.success(modelService.createModel(modelDTO));
    }

    @GetMapping
    public ResponseDTO<List<ModelDTO>> getAllModels(@RequestParam Long brandId) {
        return ResponseDTO.success(modelService.getAllModels(brandId));
    }

    @PutMapping("/{id}")
    public ResponseDTO<ModelDTO> updateModel(@PathVariable Long id, @RequestBody ModelDTO modelDTO) {
        return ResponseDTO.success(
                modelService.updateModel(id, modelDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}