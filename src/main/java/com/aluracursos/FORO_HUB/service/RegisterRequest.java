package com.aluracursos.FORO_HUB.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "El nombre no puede ir vacio")
        @Size(min = 5, max = 60)
        String nombre,
        @NotBlank(message = "El username no puede ir vacio")
        @Email(message = "El username debe ser un email")
        String username,
        @NotBlank(message = "El password no puede ir vacio")
        @Size(min = 5, max = 60)
        String password
) {
}