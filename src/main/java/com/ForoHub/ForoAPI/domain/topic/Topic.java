package com.ForoHub.ForoAPI.domain.topic;


import com.ForoHub.ForoAPI.domain.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Topic")
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String author;
    private String course;

    public Topic(TopicData topicData) {
        this.title = topicData.title();
        this.message = topicData.message();
        this.createdAt = LocalDate.now();
        this.author = topicData.author();
        this.status = Status.ACTIVE;
        this.course = topicData.course();
    }
}
