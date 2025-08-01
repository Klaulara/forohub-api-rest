package com.forohub.api.domain.post;

import jakarta.validation.constraints.NotBlank;

public record DataEditPost(
        @NotBlank String title,
        @NotBlank String content
) {
}
