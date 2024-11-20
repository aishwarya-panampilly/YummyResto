package com.aishpam.yummyresto.mapper;

import com.aishpam.yummyresto.dto.ProductRequest;
import com.aishpam.yummyresto.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productrequest) {
        return Product.builder()
                .productName(productrequest.productName())
                .price(productrequest.price())
                .build();
    }

}
