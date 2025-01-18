package com.aluracursos.FORO_HUB.repository;

import com.aluracursos.FORO_HUB.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
