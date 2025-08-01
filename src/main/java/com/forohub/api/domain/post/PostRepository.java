package com.forohub.api.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findByTitleAndContent(String title, String content);
}
