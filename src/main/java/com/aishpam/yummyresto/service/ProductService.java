package com.aishpam.yummyresto.service;

import com.aishpam.yummyresto.dto.ProductRequest;
import com.aishpam.yummyresto.entity.Product;
import com.aishpam.yummyresto.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.aishpam.yummyresto.mapper.ProductMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    // Adding new products
    public Product addProduct(@Valid ProductRequest productRequest) {
        // Use the mapper to convert ProductRequest to Product
        Product product = productMapper.toProduct(productRequest);
        return productRepo.save(product);
    }

    // Fetching top 2 products in the range of 15 to 30
    public List<Product> getTop2ProductsInFixedRange() {
        Pageable pageable = PageRequest.of(0, 2); // Page 0, Size 2
        return productRepo.findAllProducts(pageable);
    }
}