package com.aluracursos.FORO_HUB.controller;

import com.aluracursos.FORO_HUB.records.DeleteResponse;
import com.aluracursos.FORO_HUB.records.TopicRequest;
import com.aluracursos.FORO_HUB.records.TopicResponse;
import com.aluracursos.FORO_HUB.service.ITopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
@PreAuthorize("Autenticada()")
@SecurityRequirement(name = "bearerKey")
public class TopicController {

    private final ITopicService topicService;

    @Autowired
    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicResponse> createTopic(
            @RequestBody @Valid TopicRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = ((User) userDetails).getId();
        TopicResponse topicResponse = topicService.createTopic(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TopicResponse>> allTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAlltopicByUser(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.ok(topicService.getAllTopicsByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid TopicRequest request) {
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(topicService.updateTopic(userId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteTopic(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.ok(topicService.deleteTopic(id, userId));
    }
}
