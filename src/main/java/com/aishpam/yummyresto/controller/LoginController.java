package com.aishpam.yummyresto.controller;

import com.aishpam.yummyresto.dto.LoginRequest;
import com.aishpam.yummyresto.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aishpam.yummyresto.service.LoginService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        System.out.println("Login request: " + request);
        return ResponseEntity.ok(loginService.loginUser(request));
    }
}
