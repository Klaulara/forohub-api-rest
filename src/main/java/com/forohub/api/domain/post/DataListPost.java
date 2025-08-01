package com.forohub.api.domain.post;

import java.time.LocalDateTime;

public record DataListPost(
        String id,
        String title,
        String content,
        boolean status,
        LocalDateTime createdAt
) {
    public DataListPost(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getStatus(), post.getCreatedAt());
    }
}
