package com.aluracursos.FORO_HUB.controller;
import com.aluracursos.FORO_HUB.models.User;
import com.aluracursos.FORO_HUB.records.MessageResponse;
import com.aluracursos.FORO_HUB.records.TopicRequest;
import com.aluracursos.FORO_HUB.records.TopicResponse;
import com.aluracursos.FORO_HUB.records.UpdateTopicRequest;
import com.aluracursos.FORO_HUB.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
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
    public ResponseEntity<List<TopicResponse>> getAlltopicByUser(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.ok(topicService.getAllTopicsByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid UpdateTopicRequest request) {
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(topicService.updateTopic(userId, id, request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> addMessage(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid MessageResponse request){
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.addMessage(id,userId,request));
    }

    @PutMapping("/{topicId}/{messageId}")
    public ResponseEntity<MessageResponse> updateMessage(
            @PathVariable Long topicId,
            @PathVariable Long messageId,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid MessageResponse request){
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.updateMessage(topicId,messageId,userId,request));
    }

}
