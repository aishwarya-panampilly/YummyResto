package com.aishpam.yummyresto.service;

import com.aishpam.yummyresto.dto.CustomerRequest;
import com.aishpam.yummyresto.dto.CustomerResponse;
import com.aishpam.yummyresto.dto.LoginRequest;
import com.aishpam.yummyresto.entity.Customer;
import com.aishpam.yummyresto.exception.CustomerNotFoundException;
import com.aishpam.yummyresto.helper.EncryptionService;
import com.aishpam.yummyresto.helper.JWTHelper;
import com.aishpam.yummyresto.mapper.CustomerMapper;
import com.aishpam.yummyresto.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }
}
