package com.aishpam.yummyresto.mapper;

import com.aishpam.yummyresto.dto.CustomerUpdateRequest;
import com.aishpam.yummyresto.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdateMapper {
    public Customer toEntity(CustomerUpdateRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
