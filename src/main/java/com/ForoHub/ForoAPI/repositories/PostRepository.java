package com.ForoHub.ForoAPI.repositories;


import com.ForoHub.ForoAPI.domain.posts.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p JOIN p.topic t where t.title = :topic")
    Page<Post> findAllByTitle(String topic, Pageable pagination);
}
