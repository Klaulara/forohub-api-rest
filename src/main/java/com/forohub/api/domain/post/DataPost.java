package com.forohub.api.domain.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataPost(
        @NotBlank String title,
        @NotBlank String content,
        @NotNull Boolean status,
        @NotNull @Valid String idUser,
        @NotNull @Valid int idCourse
        ) {
}
