package com.aluracursos.FORO_HUB.repository;

import com.aluracursos.FORO_HUB.models.Topic;
import com.aluracursos.FORO_HUB.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    List<Topic> findAllByUser(User user);
}
