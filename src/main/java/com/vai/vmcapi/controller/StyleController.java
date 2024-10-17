package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.style.StyleDTO;
import com.vai.vmcapi.service.impl.StyleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styles")
public class StyleController {

    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostMapping
    public ResponseDTO<StyleDTO> createStyle(@RequestBody StyleDTO styleDTO) {
        return ResponseDTO.ok(styleService.createStyle(styleDTO));
    }

    @GetMapping
    public ResponseDTO<List<StyleDTO>> getAllStyles() {
        return ResponseDTO.ok(styleService.getAllStyles());
    }

    @PutMapping("/{id}")
    public ResponseDTO<StyleDTO> updateStyle(@PathVariable Long id, @RequestBody StyleDTO styleDTO) {
        return ResponseDTO.ok(
                styleService.updateStyle(id, styleDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStyle(@PathVariable Long id) {
        styleService.deleteStyle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}