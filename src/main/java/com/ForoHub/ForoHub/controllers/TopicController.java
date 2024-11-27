package com.ForoHub.ForoHub.controllers;

import com.ForoHub.ForoHub.domain.topic.TopicData;
import com.ForoHub.ForoHub.domain.topic.TopicResponse;
import com.ForoHub.ForoHub.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics/")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping()
    public ResponseEntity<TopicResponse> createNewTopic(@RequestBody @Valid TopicData topicData, UriComponentsBuilder uriComponentsBuilder){
        TopicResponse topic = topicService.createTopic(topicData);
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.id()).toUri();
        return ResponseEntity.created(url).body(topic);
    }

    @GetMapping()
    public ResponseEntity<List<TopicResponse>> getAllTopics(){
        List<TopicResponse> topics = topicService.getTopics();
        return ResponseEntity.ok(topics);
    }
}
