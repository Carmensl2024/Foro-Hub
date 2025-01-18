package com.aluracursos.FORO_HUB.service;

import com.aluracursos.FORO_HUB.errors.UpdateTopicException;
import com.aluracursos.FORO_HUB.models.Message;
import com.aluracursos.FORO_HUB.models.Topic;
import com.aluracursos.FORO_HUB.models.User;
import com.aluracursos.FORO_HUB.records.*;
import com.aluracursos.FORO_HUB.repository.MessageRepository;
import com.aluracursos.FORO_HUB.repository.TopicRepository;
import com.aluracursos.FORO_HUB.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final MessageRepository messageRepository;

    public TopicService(UserRepository userRepository, TopicRepository topicRepository,
                        MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.messageRepository = messageRepository;
    }

    // crea un nuevo topico
    public TopicResponse createTopic(Long userId, TopicRequest request){

        User user=userRepository.getReferenceById(userId);
        Topic topic= new Topic(request.titulo(),request.contenido(), request.curso(), user);
        topicRepository.save(topic);
        return new TopicResponse(topic);
    }


    public TopicResponse getTopicById(Long topicId){
        Topic topic = topicRepository.getReferenceById(topicId);
        return new TopicResponse(topic);
    }

    // devuelve todos los topicos del usuario
    public List<TopicResponse> getAllTopicsByUser(Long userId){
        User user = userRepository.getReferenceById(userId);
        List<Topic> topics=topicRepository.findAllByUser(user);
        List<TopicResponse> topicResponses=new ArrayList<>();
        topics.forEach(topic -> topicResponses.add(new TopicResponse(topic)));
        return  topicResponses;
    }

    public List<TopicResponse> getAllTopics(){
        List<Topic> topics=topicRepository.findAll();
        List<TopicResponse> topicResponses=new ArrayList<>();
        topics.forEach(topic -> topicResponses.add(new TopicResponse(topic)));
        return  topicResponses;
    }

    @Transactional
    public TopicResponse updateTopic(Long userId, Long topicId, UpdateTopicRequest request){
        User user = userRepository.getReferenceById(userId);
        Topic topic = topicRepository.getReferenceById(topicId);
        if(user!=topic.getUser()) throw new UpdateTopicException("el usuario no tiene permisos para editar el topic");
        topic.update(request);
        return new TopicResponse(topic);
    }

    @Transactional
    public MessageResponse addMessage(Long id, Long userId, @Valid MessageResponse request) {
        User user = userRepository.getReferenceById(userId);
        Topic topic = topicRepository.getReferenceById(id);
        Message message = new Message(request,user,topic);
        messageRepository.save(message);
        topic.addMessage(message);
        return new MessageResponse(message);
    }

    @Transactional
    public MessageResponse updateMessage(Long topicId, Long messageId, Long userId, @Valid MessageResponse request) {
        User user = userRepository.getReferenceById(userId);
        Message message = messageRepository.getReferenceById(messageId);
        if(message.getUser()!=user)throw new UpdateTopicException("el usuario no tiene permisos para editar el mesage");
        Topic topic = topicRepository.getReferenceById(topicId);
        message.update(request);

        return new MessageResponse(message);
    }
}



