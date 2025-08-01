package com.forohub.api.domain.post;

import com.forohub.api.domain.course.Course;
import com.forohub.api.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "posts")
@Entity(name = "Post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {
    @Id
    private String id;

    @PrePersist
    public void assignId() {
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
    private String title;
    private String content;
    private Boolean status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public void updatePost(@Valid DataEditPost dataPost) {
        if(dataPost.title() != null){
            this.title = dataPost.title();
        }
        if(dataPost.content() != null){
            this.content = dataPost.content();
        }
    }

    public void softDeletePost() {
        this.status = Boolean.FALSE;
    }
}
