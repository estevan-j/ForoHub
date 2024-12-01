package com.ForoHub.ForoAPI.domain.posts;

import com.ForoHub.ForoAPI.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity(name = "Post")
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private String author;

    public Post(PostData postData, Topic topic) {
        this.message = postData.message();
        this.createdAt = LocalDateTime.now();
        this.author = postData.author();
        this.topic = topic;
    }

    @PrePersist
    public void prePersist(){
        if (this.author == null){
            this.author = "Anonymous";
        }
    }

    public void updatePost(PostUpdateData postData) {
        this.message = postData.message();
        this.createdAt = LocalDateTime.now();
    }
}
