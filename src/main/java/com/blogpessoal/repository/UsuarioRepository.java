package com.blogpessoal.repository;

import io.micrometer.common.lang.NonNull;
import org.acelera.blogmaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Boolean existsByEmail(@NonNull String email);

    Optional<Usuario> findByEmail(String email);
}
