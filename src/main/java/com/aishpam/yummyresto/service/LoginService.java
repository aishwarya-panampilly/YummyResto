package com.aishpam.yummyresto.service;
import com.aishpam.yummyresto.dto.LoginRequest;
import com.aishpam.yummyresto.entity.Customer;
import com.aishpam.yummyresto.helper.EncryptionService;
import com.aishpam.yummyresto.helper.JWTHelper;
import com.aishpam.yummyresto.mapper.LoginMapper;
import com.aishpam.yummyresto.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.aishpam.yummyresto.dto.CustomerRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CustomerRepo repo;
    private final LoginMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    //accepts login request DTO and returns a string
    public String loginUser(LoginRequest request) {
        //converts login dto to customer entity using mapper
        Customer customer = mapper.toEntity(request);
        //attempt to find customer in the database
        Optional<Customer> existingCustomer = repo.findByEmail(customer.getEmail());

        if (existingCustomer.isPresent()) {
            //check if password matches
            if (!encryptionService.validates(request.password(), existingCustomer.get().getPassword())) {
                return "Wrong password";
            } else {
                return jwtHelper.generateToken(request.email());
            }
        } else {
            return "user not found";
        }
    }
}


