package com.aluracursos.FORO_HUB.service;

import com.aluracursos.FORO_HUB.errors.LoginException;
import com.aluracursos.FORO_HUB.models.User;
import com.aluracursos.FORO_HUB.records.AuthResponse;
import com.aluracursos.FORO_HUB.records.LoginRequest;
import com.aluracursos.FORO_HUB.records.RegisterRequest;
import com.aluracursos.FORO_HUB.records.RegisterResponse;
import com.aluracursos.FORO_HUB.repository.UserRepository;
import com.aluracursos.FORO_HUB.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private JwtTokenProvider jwtTokenProvider;

        public AuthResponse login(LoginRequest request) {

            User user = userRepository.findByUsername(request.username())
                    .orElseThrow(() -> new LoginException("Usuario no encontrado"));


            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                throw new LoginException("Contraseña incorrecta");
            }

            String token = jwtTokenProvider.generateToken(user);
            return new AuthResponse("",token);
        }

        public RegisterResponse register(RegisterRequest request) {

            if (userRepository.existsByUsername(request.username())) {
                throw new RuntimeException("El usuario ya existe");
            }

            User newUser = new User();
            newUser.setUsername(request.username());
            newUser.setPassword(passwordEncoder.encode(request.password()));
            userRepository.save(newUser);

            return new RegisterResponse("Usuario registrado exitosamente");
        }
    }

