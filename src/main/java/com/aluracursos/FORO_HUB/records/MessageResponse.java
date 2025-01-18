package com.aluracursos.FORO_HUB.records;

import com.aluracursos.FORO_HUB.models.Message;

public record MessageResponse (
        Long id,
        String content){
    public MessageResponse(Message message) {
        this(message.getId(),message.getContent());
    }
}
