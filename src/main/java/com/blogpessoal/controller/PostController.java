package com.blogpessoal.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.model.Post;

import jakarta.validation.constraints.NotBlank;

@Repository
public class PostControll extends JpaRepository<Post, Long> {
	
    boolean existsByTitle(@NotBlank(message = "{required.title}") String title);

    boolean existsByContent(@NotBlank(message = "{required.content}") String content);

    List<Post> findByUserIdAndTemaId(UUID usuarioId, Long temaId);

    List<Post> findByUserId(UUID usuarioId);

    List<Post> findByThemeId(Long temaId);
}
