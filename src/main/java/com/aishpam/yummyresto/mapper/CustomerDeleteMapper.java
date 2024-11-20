package com.aishpam.yummyresto.mapper;

import com.aishpam.yummyresto.dto.CustomerDeleteRequest;
import com.aishpam.yummyresto.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerDeleteMapper {
    public Customer toEntity(CustomerDeleteRequest request) {
        return Customer.builder()
                .email(request.email())
                .build();
    }
}