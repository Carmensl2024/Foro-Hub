package com.aluracursos.FORO_HUB.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTopicRequest(
    String titulo,
    String contenido,
    String curso) {}
