package com.blogpessoal.dto.response;

import java.time.LocalDateTime;

public record PostResponse(
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String theme,
        String name,
        String role
) {
}
