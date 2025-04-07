package com.blogpessoal.dto.request;

import com.blogpessoal.model.Usuario;

public record UpdateUserRequest(
        String name,
        String email,
        String password,
        String foto
) {
    public UpdateUserRequest(Usuario usuario) {
        this(usuario.getName(), usuario.getEmail(), usuario.getPassword(), usuario.getfoto());
    }
}