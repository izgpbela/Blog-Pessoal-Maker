package com.blogpessoal.controller;

import com.blogpessoal.model.Postagem;
import com.blogpessoal.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
    
    @Autowired
    private PostagemService postagemService;
    
    @GetMapping
    public ResponseEntity<List<Postagem>> getAll() {
        return ResponseEntity.ok(postagemService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable Long id) {
        return postagemService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemService.findByTitulo(titulo));
    }
    
    @PostMapping
    public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(postagemService.save(postagem));
    }
    
    @PutMapping
    public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
        return postagemService.findById(postagem.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                .body(postagemService.save(postagem)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Postagem> postagem = postagemService.findById(id);
        
        if(postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        postagemService.deleteById(id);
    }

}
