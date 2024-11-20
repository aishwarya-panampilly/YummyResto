package com.aishpam.yummyresto.controller;

import com.aishpam.yummyresto.dto.CustomerRequest;
import com.aishpam.yummyresto.dto.CustomerResponse;
import com.aishpam.yummyresto.entity.Customer;
import com.aishpam.yummyresto.service.CustomerService;
import com.aishpam.yummyresto.dto.CustomerDeleteRequest;
import com.aishpam.yummyresto.dto.CustomerUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(
            @PathVariable("email") String email){
            System.out.println(email);

        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request) {

        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update") // for post request
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerUpdateRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @DeleteMapping("/remove_account") // for post request
    public ResponseEntity<Customer> deleteCustomer(@RequestBody @Valid CustomerDeleteRequest request) {
        return ResponseEntity.ok(customerService.deleteCustomer(request));
    }

}
