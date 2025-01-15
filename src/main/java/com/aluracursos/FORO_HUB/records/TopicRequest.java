package com.aluracursos.FORO_HUB.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicRequest(
        @NotBlank
        @Size(min = 5, max = 80, message = "Minimo de 5 y maximo de 80")
        String titulo,
        @NotBlank
        @Size(min = 5, max = 80, message = "Minimo de 5 y maximo de 80")
        String curso

) {
}
