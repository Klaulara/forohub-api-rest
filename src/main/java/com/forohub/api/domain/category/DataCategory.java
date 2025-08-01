package com.forohub.api.domain.category;

import jakarta.validation.constraints.NotBlank;

public record DataCategory(
        @NotBlank String name,
        @NotBlank String description
) {
}
