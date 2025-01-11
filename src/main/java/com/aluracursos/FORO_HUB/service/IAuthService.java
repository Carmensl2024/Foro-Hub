package com.aluracursos.FORO_HUB.service;

public interface IAuthService {

    AuthResponse login(LoginRequest request);

    RegisterResponse register(RegisterRequest request);
}


