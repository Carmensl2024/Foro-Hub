package com.aluracursos.FORO_HUB.records;

import com.aluracursos.FORO_HUB.models.Topic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record TopicResponse(
    String titulo,
    String contenido,
    String curso,
    String username,
    List<MessageResponse> messages) {
    public TopicResponse(Topic topic) {
        this(
            topic.getTitulo(),
            topic.getContenido(),
            topic.getCurso(),
            topic.getUser().getUsername(),
            topic.getMessageList().stream().map(MessageResponse::new).toList());
    }
}
