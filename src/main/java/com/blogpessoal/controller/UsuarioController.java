package com.blogpessoal.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.blogpessoal.dto.request.UpdateUserRequest;
import com.blogpessoal.dto.response.UserResponse;
import com.blogpessoal.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

	private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PutMapping("/{usuarioId}")
    @Transactional
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID userId,
                                                   @Valid @RequestBody UpdateUserRequest request) {
        UserResponse updatedUser = usuarioService.updateUser(userId, request);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = usuarioService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId) {
        UserResponse user = usuarioService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable UUID usuarioId) {
        UsuarioController usuarioService;
		usuarioService.deleteUser(usuarioId);
        return ResponseEntity.noContent().build();
    }
} 
