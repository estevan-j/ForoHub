package com.ForoHub.ForoAPI.domain.topic;

import com.ForoHub.ForoAPI.domain.Status;

import java.time.LocalDate;

public record TopicResponse(
        Long id,
        String title,
        String message,
        String author,
        String course,
        Status status,
        LocalDate creationData
) {
}
