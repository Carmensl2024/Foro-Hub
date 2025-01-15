package com.aluracursos.FORO_HUB.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record TopicResponse(
        Long id,
        String titulo,
        String curso,
        @JsonProperty("Creado_En")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate creadoEn,
        UserResponse user,
        List<MessageResponse> messages

) {
}
