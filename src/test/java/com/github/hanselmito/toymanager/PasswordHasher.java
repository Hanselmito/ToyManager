package com.github.hanselmito.toymanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Si123456789";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Contraseña hasheada: " + hashedPassword);
    }
}