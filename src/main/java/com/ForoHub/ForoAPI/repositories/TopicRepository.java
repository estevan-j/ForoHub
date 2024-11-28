package com.ForoHub.ForoAPI.repositories;

import com.ForoHub.ForoAPI.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
