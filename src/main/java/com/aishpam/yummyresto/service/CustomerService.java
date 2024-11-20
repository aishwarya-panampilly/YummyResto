package com.aishpam.yummyresto.service;

import java.lang.reflect.Field;
import com.aishpam.yummyresto.dto.CustomerDeleteRequest;
import com.aishpam.yummyresto.dto.CustomerRequest;
import com.aishpam.yummyresto.dto.CustomerResponse;
import com.aishpam.yummyresto.dto.CustomerUpdateRequest;
import com.aishpam.yummyresto.entity.Customer;
import com.aishpam.yummyresto.exception.CustomerNotFoundException;
import com.aishpam.yummyresto.helper.EncryptionService;
import com.aishpam.yummyresto.helper.JWTHelper;
import com.aishpam.yummyresto.mapper.CustomerDeleteMapper;
import com.aishpam.yummyresto.mapper.CustomerMapper;
import com.aishpam.yummyresto.mapper.CustomerUpdateMapper;
import com.aishpam.yummyresto.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final CustomerDeleteMapper deleteMapper;
    private final CustomerUpdateMapper updateMapper;

    private final JWTHelper jwtHelper;

    //create new customer
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

    //delete customer
    public Customer deleteCustomer(CustomerDeleteRequest request) {
        Optional<Customer> existingCustomerOpt = customerRepo.findByEmail(request.email());
        if (existingCustomerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found with email: " + request.getEmail());
        }

        Customer existingCustomer = existingCustomerOpt.get();

        // Convert the DTO to an entity using Mapper
        Customer customer = deleteMapper.toEntity(request);

        // deletes entity into database using Repo
        customerRepo.delete(existingCustomer);
        return customer;
    }

    public Customer updateCustomer(CustomerUpdateRequest request) {
        System.out.println("==================== update service");

        // Retrieve the existing customer from the database
        Optional<Customer> existingCustomerOpt = customerRepo.findByEmail(request.email());

        if (existingCustomerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found with email: " + request.email());
        }

        Customer existingCustomer = existingCustomerOpt.get();

        // Convert the DTO to an entity using Mapper
        Customer updatedCustomer = updateMapper.toEntity(request);

        // Compare each field dynamically and update only the changed fields
        Field[] fields = Customer.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object oldValue = field.get(existingCustomer);
                Object newValue = field.get(updatedCustomer);

                if (newValue != null && (!newValue.equals(oldValue))) {
                    System.out.println(field.getName() + " changed from " + oldValue + " to " + newValue);
                    field.set(existingCustomer, newValue); // Update the field in the existing customer
                }
            } catch (IllegalAccessException e) {
                System.err.println("Error accessing field: " + field.getName());
            }
        }

        // Save the updated entity
        customerRepo.save(existingCustomer);
        return existingCustomer;
    }
}
