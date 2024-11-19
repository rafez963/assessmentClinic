package com.assessment.assessment.controller;

import com.assessment.assessment.config.JwtService;
import com.assessment.assessment.model.User;
import com.assessment.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService; // Servicio para manejar JWT

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // Encriptar la contraseña antes de guardarla
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Buscar el usuario por correo electrónico
        User user = userService.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Invalid email or password"));


        // Verificar la contraseña
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        /*
        // Comparar la contraseña directamente
        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }*/

        // Generar el token JWT
        String token = jwtService.generateToken(user.getEmail());

        // Retornar el token en la respuesta
        return ResponseEntity.ok(Map.of("token", token));
    }
}
