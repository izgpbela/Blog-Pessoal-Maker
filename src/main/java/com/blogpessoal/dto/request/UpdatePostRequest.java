package com.blogpessoal.dto.request;

import com.blogpessoal.model.Post;

public record UpdatePostRequest(
        String title,
        String content,
        Long themeId
) {
    public UpdatePostRequest(Post post) {
        this(post.getTitle(), post.getContent(), post.getTheme() != null ? post.getTheme().getId() : null);
    }
}