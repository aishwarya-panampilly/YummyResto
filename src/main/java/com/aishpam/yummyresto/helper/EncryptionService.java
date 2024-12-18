package com.aishpam.yummyresto.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionService {
    private final PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean validates(String password, String encodedPassword) {
        System.out.println("Password: " + password);
        System.out.println("EncodedPassword: " + encodedPassword);
        return passwordEncoder.matches(password, encodedPassword);
    }
}
