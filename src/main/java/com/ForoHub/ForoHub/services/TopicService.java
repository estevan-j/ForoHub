package com.ForoHub.ForoHub.services;

import com.ForoHub.ForoHub.domain.topic.Topic;
import com.ForoHub.ForoHub.domain.topic.TopicData;
import com.ForoHub.ForoHub.domain.topic.TopicResponse;
import com.ForoHub.ForoHub.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public TopicResponse createTopic(TopicData topicData) {
        Topic topic = topicRepository.save(new Topic(topicData));
        return convertTopicToTopicResponseDTO(topic);
    }

    private TopicResponse convertTopicToTopicResponseDTO(Topic topic){
        return new TopicResponse(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getAuthor(), topic.getCourse(), topic.getCreationDate());
    }

    public List<TopicResponse> getTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream().map(topic -> convertTopicToTopicResponseDTO(topic)).toList();
    }
}
