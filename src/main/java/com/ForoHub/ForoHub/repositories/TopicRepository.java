package com.ForoHub.ForoHub.repositories;

import com.ForoHub.ForoHub.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
