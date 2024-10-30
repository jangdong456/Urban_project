package com.gudee.urban.controller;

import com.gudee.urban.dto.ApartmentData;
import com.gudee.urban.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    // 아파트 데이터 리스트 조회 (JSON 형식으로 반환)
    @GetMapping
    public List<ApartmentData> getAllApartments(
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "minArea", required = false) Double minArea,
            @RequestParam(value = "maxArea", required = false) Double maxArea,
            @RequestParam(value = "sort", required = false) String sort) {
        return apartmentService.findFilteredApartments(minPrice, maxPrice, minArea, maxArea, sort);
    }

    // 아파트 데이터를 JSON 형식으로 반환 (기존 기능 유지)
    @GetMapping("/json")
    public List<ApartmentData> getAllApartmentsJson() {
        return apartmentService.findAll();
    }
}
