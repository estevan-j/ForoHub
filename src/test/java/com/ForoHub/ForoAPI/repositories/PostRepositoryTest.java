package com.ForoHub.ForoAPI.repositories;

import com.ForoHub.ForoAPI.domain.posts.Post;
import com.ForoHub.ForoAPI.domain.posts.PostData;
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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PostRepositoryTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Test
    @DisplayName("Return 0 when there's no posts with that topic")
    void findAllByTitleNothing() {
        String topic = "monkey";
        Pageable pagination = Pageable.unpaged();
        var posts = postRepository.findAllByTitle(topic, pagination);
        assertEquals(0, posts.getTotalElements());
    }

    @Test
    @DisplayName("Return true when they were got from db")
    void findAllByTitle() {
        String title = "test";
        LocalDateTime date = LocalDateTime.now();
        var topic = topicRepository.save(new Topic(new TopicData(title, "this is my test", "anom", "testing")));
        postRepository.save(new Post(new PostData("hello post",date.toString(),title, "gato"), topic));
        Pageable pagination = Pageable.unpaged();
        var posts = postRepository.findAllByTitle(title, pagination);
        assertTrue(posts.getTotalElements() > 0);
    }
}