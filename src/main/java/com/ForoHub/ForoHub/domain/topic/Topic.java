package com.ForoHub.ForoHub.domain.topic;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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
    private LocalDate creationDate;
//    private String status;
    private String author;
    private String course;

    public Topic(TopicData topicData) {
        this.title = topicData.title();
        this.message = topicData.message();
        this.creationDate = LocalDate.now();
        this.author = topicData.author();
        this.course = topicData.course();
    }
}
