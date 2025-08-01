package com.forohub.api.domain.course;

import jakarta.validation.constraints.NotBlank;

public record DataCourse(
        @NotBlank String name,
        @NotBlank String description
) {
}
