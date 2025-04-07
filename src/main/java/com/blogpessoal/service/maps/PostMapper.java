package com.blogpessoal.service.maps;

import com.blogpessoal.model.Post;
import com.blogpessoal.model.Tema;
import com.blogpessoal.model.Usuario;
import com.blogpessoal.dto.request.CreatePostRequest;
import com.blogpessoal.dto.response.PostResponse;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {

    public Post toPost(CreatePostRequest request, Usuario usuario, Tema tema) {
        return Post.builder()
                .id(null)
                .title(request.title())
                .content(request.content())
                .tema(tema)
                .usuario(usuario)
                .build();
    }

    public PostResponse fromPost(Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getTema() != null ? post.getTema().getDescription() : null,
                post.getUsuario().getName(),
                post.getUsuario().getRole().name()
        );
    }
}
