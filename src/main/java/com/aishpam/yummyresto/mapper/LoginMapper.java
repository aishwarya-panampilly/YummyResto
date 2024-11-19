//mapping dto to entity
package com.aishpam.yummyresto.mapper;

import com.aishpam.yummyresto.dto.LoginRequest;
import com.aishpam.yummyresto.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {
    public Customer toEntity(LoginRequest loginRequest) {
        // object is build in such a manner
        return Customer.builder()
                .email(loginRequest.email()) //extract email value
                .password(loginRequest.password())
                .build(); //complete creation of the object
    }
}
