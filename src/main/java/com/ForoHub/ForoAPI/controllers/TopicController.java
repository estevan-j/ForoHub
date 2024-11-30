package com.ForoHub.ForoAPI.controllers;

import com.ForoHub.ForoAPI.domain.topic.TopicData;
import com.ForoHub.ForoAPI.domain.topic.TopicResponse;
import com.ForoHub.ForoAPI.domain.topic.TopicUpdate;
import com.ForoHub.ForoAPI.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
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
    public ResponseEntity<Page<TopicResponse>> getAllTopics(@PageableDefault(size = 5) Pageable pagination){
        Page<TopicResponse> topics = topicService.getTopics(pagination);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopicById(@PathVariable Long id){
        TopicResponse topic = topicService.searchTopicBy(id);
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdate topicData){
        TopicResponse updatedTopic = topicService.updateTopic(id, topicData);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(@PathVariable Long id){
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }


}
