package com.aluracursos.FORO_HUB.service;

import com.aluracursos.FORO_HUB.records.AuthResponse;
import com.aluracursos.FORO_HUB.records.LoginRequest;
import com.aluracursos.FORO_HUB.records.RegisterRequest;
import com.aluracursos.FORO_HUB.records.RegisterResponse;

public interface IAuthService {

    AuthResponse login(LoginRequest request);

    RegisterResponse register(RegisterRequest request);
}


