package com.blogpessoal.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório!")
    @Size(min = 5, max = 100, message = "O título deve conter entre 5 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "O texto é obrigatório!")
    @Size(min = 10, max = 1000, message = "O texto deve conter entre 10 e 1000 caracteres")
    private String texto;

    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JsonIgnoreProperties("postagens") // Evita loop quando serializa Usuario
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JsonIgnoreProperties("postagens") // Evita loop quando serializa Tema
    @JoinColumn(name = "tema_id")
    private Tema tema;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
