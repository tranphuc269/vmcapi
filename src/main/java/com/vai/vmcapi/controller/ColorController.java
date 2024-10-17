package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.color.ColorDTO;
import com.vai.vmcapi.service.impl.ColorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping
    public ResponseDTO<ColorDTO> createColor(@RequestBody ColorDTO colorDTO) {
        return ResponseDTO.ok(colorService.createColor(colorDTO));
    }

    @GetMapping
    public ResponseDTO<List<ColorDTO>> getAllColors() {
        return ResponseDTO.ok(colorService.getAllColors());
    }

    @PutMapping("/{id}")
    public ResponseDTO<ColorDTO> updateColor(@PathVariable Long id, @RequestBody ColorDTO colorDTO) {
        return ResponseDTO.ok(
                colorService.updateColor(id, colorDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}