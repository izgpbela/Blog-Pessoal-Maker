package com.blogpessoal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;
    
    @NotBlank(message = "O usuário é obrigatório!")
    @Email(message = "O usuário deve ser um email válido!")
    private String usuario;
    
    @NotBlank(message = "A senha é obrigatória!")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;
    
    private String foto;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Postagem> postagens;
    
    
	    // Getters e Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
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

	    public String getFoto() {
	        return foto;
	    }

	    public void setFoto(String foto) {
	        this.foto = foto;
	    }

	    public List<Postagem> getPostagens() {
	        return postagens;
	    }

	    public void setPostagens(List<Postagem> postagens) {
	        this.postagens = postagens;
	    }
	    
	 // Métodos do UserDetails
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of();
	    }
	    
	    @Override
	    public String getPassword() {
	        return senha;
	    }
	    
	    @Override
	    public String getUsername() {
	        return usuario;
	    }
	    
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }
	    
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }
	    
	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	    
	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
}
