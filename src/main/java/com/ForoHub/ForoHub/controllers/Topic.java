package com.ForoHub.ForoHub.controllers;

import com.ForoHub.ForoHub.domain.topic.TopicData;
import com.ForoHub.ForoHub.domain.topic.TopicResponse;
import com.ForoHub.ForoHub.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics/")
public class Topic {

    @Autowired
    private TopicService topicService;

    @PostMapping()
    public ResponseEntity<TopicResponse> createNewTopic(@RequestBody @Valid TopicData topicData){


    }
}
