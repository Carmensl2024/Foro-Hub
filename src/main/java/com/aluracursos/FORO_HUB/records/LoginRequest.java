package com.aluracursos.FORO_HUB.records;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El username no puede ir vacio")
        String username,
        @NotBlank(message = "el password no puede ir vacio")
        String password
) {
}
