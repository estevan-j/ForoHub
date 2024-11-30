package com.ForoHub.ForoAPI.repositories;

import com.ForoHub.ForoAPI.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("SELECT t FROM Topic t WHERE t.status <> 'INACTIVE'")
    Page<Topic> findAllActivesTopics(Pageable pagination);
}
