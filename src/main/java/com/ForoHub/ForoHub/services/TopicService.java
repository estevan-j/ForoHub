package com.ForoHub.ForoHub.services;

import com.ForoHub.ForoHub.domain.topic.Topic;
import com.ForoHub.ForoHub.domain.topic.TopicData;
import com.ForoHub.ForoHub.domain.topic.TopicResponse;
import com.ForoHub.ForoHub.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public TopicResponse createTopic(TopicData topicData) {
        Topic topic = new Topic(topicData);
    }
}
