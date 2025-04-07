package com.blogpessoal.service;

import com.blogpessoal.model.Postagem;
import com.blogpessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {
    
    @Autowired
    private PostagemRepository postagemRepository;
    
    public List<Postagem> findAll() {
        return postagemRepository.findAll();
    }
    
    public Optional<Postagem> findById(Long id) {
        return postagemRepository.findById(id);
    }
    
    public List<Postagem> findByTitulo(String titulo) {
        return postagemRepository.findAllByTituloContainingIgnoreCase(titulo);
    }
    
    public Postagem save(Postagem postagem) {
        return postagemRepository.save(postagem);
    }
    
    public void deleteById(Long id) {
        postagemRepository.deleteById(id);
    }
}
