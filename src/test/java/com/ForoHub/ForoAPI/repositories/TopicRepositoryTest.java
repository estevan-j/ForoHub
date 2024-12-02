package com.ForoHub.ForoAPI.repositories;

import com.ForoHub.ForoAPI.domain.topic.Topic;
import com.ForoHub.ForoAPI.domain.topic.TopicData;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest//setting test to DB
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//say no use memory DB
@ActiveProfiles("test")//use test.properties
class TopicRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private TopicRepository topicRepository;

    @Test
    @DisplayName("Return null when there is not topics registered")
    void findAllActivesTopics() {
        Pageable pages = Pageable.unpaged();
        var topics = topicRepository.findAllActivesTopics(pages);
        assertEquals(0, topics.getTotalElements());
    }

    @Test
    @DisplayName("Return False when there's no topic with that title")
    void noExistsByTitle() {
        var existTopic = topicRepository.existsByTitle("test");
        assertFalse(existTopic);
    }

    @Test
    @DisplayName("Return True when the topic was found")
    void existsByTitle() {
        String title = "test";
        topicRepository.save(new Topic(new TopicData(title, "this is my test", "anom", "testing")));
        var existTopic = topicRepository.existsByTitle(title);
        assertTrue(existTopic);
    }

    @Test
    @DisplayName("Return null when there aren't any topic with that title")
    void findByTitleNotFound() {
        var topic = topicRepository.findByTitle("test");
        assertNull(topic);
    }

    @Test
    @DisplayName("Return Topic when it was found by title")
    void findByTitleFound() {
        String title = "test";
        topicRepository.save(new Topic(new TopicData(title, "this is my test", "anom", "testing")));
        var topic = topicRepository.findByTitle(title);
        assertNotNull(topic);
    }
}