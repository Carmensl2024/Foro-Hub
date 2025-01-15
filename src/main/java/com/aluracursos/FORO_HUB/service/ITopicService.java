package com.aluracursos.FORO_HUB.service;

import com.aluracursos.FORO_HUB.records.DeleteResponse;
import com.aluracursos.FORO_HUB.records.TopicRequest;
import com.aluracursos.FORO_HUB.records.TopicResponse;

import java.util.List;

public interface ITopicService {
    TopicResponse createTopic(Long userId, TopicRequest request);

    TopicResponse getTopicById(Long topicId);

    List<TopicResponse> getAllTopicsByUser(Long userId);

    List<TopicResponse> getAllTopics();

    TopicResponse updateTopic(Long userId, Long topicId, TopicRequest request);

    DeleteResponse deleteTopic(Long topicId, Long userId);
}


