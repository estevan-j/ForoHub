package com.ForoHub.ForoHub.services;

import com.ForoHub.ForoHub.domain.topic.Topic;
import com.ForoHub.ForoHub.domain.topic.TopicData;
import com.ForoHub.ForoHub.domain.topic.TopicResponse;
import com.ForoHub.ForoHub.domain.topic.TopicUpdate;
import com.ForoHub.ForoHub.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    public TopicResponse searchTopicBy(Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        return convertTopicToTopicResponseDTO(topic);
    }


    public TopicResponse updateTopic(Long id, @Valid TopicUpdate topicData) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic no encontrado con ID: " + id));

        updateIfNotBlank(topic::setMessage, topicData.message());
        updateIfNotBlank(topic::setCourse, topicData.course());
        updateIfNotBlank(topic::setTitle, topicData.title());
        topicRepository.save(topic);
        return convertTopicToTopicResponseDTO(topic);
    }

    private void updateIfNotBlank(Consumer<String> updater, String value) {
        if (value != null && !value.isBlank()) {
            updater.accept(value);
        }
    }
}
