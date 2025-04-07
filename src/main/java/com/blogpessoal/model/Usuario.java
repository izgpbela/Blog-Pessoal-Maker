package com.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(unique = true, nullable = false)
	    private String usuario;  // Nome de usuário para login
	    
	    @Column(nullable = false)
	    private String senha;
	    
	    private String tipo = "USER"; // Padrão: USER (pode ser ADMIN)

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario") // Evita loop infinito
    private List<Postagem> postagens;

    // Getters e Setters
 // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Métodos úteis (opcionais)
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", usuario=" + usuario + ", tipo=" + tipo + "]";
    }
}
