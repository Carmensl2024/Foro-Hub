package com.aluracursos.FORO_HUB.models;

import com.aluracursos.FORO_HUB.records.UpdateTopicRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String contenido;
    private String curso;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messageList;

    public Topic(String titulo, String contenido, String curso, User user) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.curso=curso;
        this.user = user;
    }

    public void update(UpdateTopicRequest request) {
        if(request.titulo()!=null && !request.titulo().isEmpty())titulo=request.titulo();
        if(request.contenido()!=null && !request.contenido().isEmpty())contenido=request.contenido();
        if(request.curso()!=null && !request.curso().isEmpty())curso=request.curso();
    }

    public void addMessage(Message message) {
    }
}


