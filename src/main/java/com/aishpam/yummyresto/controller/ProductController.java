package com.aishpam.yummyresto.controller;

import com.aishpam.yummyresto.dto.ProductRequest;
import com.aishpam.yummyresto.entity.Product;
import com.aishpam.yummyresto.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    //adding new products
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @GetMapping("/top2-in-range")
    public List<Product> getTop2ProductsInRange() {
        return productService.getTop2ProductsInFixedRange();
    }
}
