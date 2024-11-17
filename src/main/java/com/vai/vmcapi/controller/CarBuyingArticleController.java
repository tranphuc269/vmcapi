package com.vai.vmcapi.controller;

import com.vai.vmcapi.domain.dto.PageableResponse;
import com.vai.vmcapi.domain.dto.ResponseDTO;
import com.vai.vmcapi.domain.dto.article.CarBuyingArticleDTO;
import com.vai.vmcapi.domain.dto.car_article.QueryCarArticleParams;
import com.vai.vmcapi.service.impl.CarBuyingArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-buying-articles")
public class CarBuyingArticleController {

    private final CarBuyingArticleService carBuyingArticleService;

    public CarBuyingArticleController(CarBuyingArticleService carBuyingArticleService) {
        this.carBuyingArticleService = carBuyingArticleService;
    }

    @PostMapping
    public ResponseDTO<CarBuyingArticleDTO> createCarBuyingArticle(@RequestBody CarBuyingArticleDTO carBuyingArticleDTO) {
        return ResponseDTO.ok(carBuyingArticleService.createCarBuyingArticle(carBuyingArticleDTO));
    }

    @GetMapping
    public ResponseDTO<List<CarBuyingArticleDTO>> getAllCarBuyingArticles() {
        return ResponseDTO.ok(carBuyingArticleService.getAllCarBuyingArticles());
    }

    @PostMapping("/query")
    public ResponseDTO<PageableResponse<CarBuyingArticleDTO>> getAllCars(@RequestBody QueryCarArticleParams params) {
        return ResponseDTO.success(carBuyingArticleService.queryArticle(params));
    }

    @PutMapping("/{id}")
    public ResponseDTO<CarBuyingArticleDTO> updateCarBuyingArticle(@PathVariable Long id, @RequestBody CarBuyingArticleDTO carBuyingArticleDTO) {
        return ResponseDTO.ok(
                carBuyingArticleService.updateCarBuyingArticle(id, carBuyingArticleDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarBuyingArticle(@PathVariable Long id) {
        carBuyingArticleService.deleteCarBuyingArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}