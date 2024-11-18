package com.aishpam.yummyresto.service;

import com.aishpam.yummyresto.dto.CustomerRequest;
import com.aishpam.yummyresto.dto.CustomerResponse;
import com.aishpam.yummyresto.entity.Customer;
import com.aishpam.yummyresto.mapper.CustomerMapper;
import com.aishpam.yummyresto.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}
