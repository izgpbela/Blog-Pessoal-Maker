package com.blogpessoal.controller;


import com.blogpessoal.model.Tema;
import com.blogpessoal.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
    
    @Autowired
    private TemaService temaService;
    
    @GetMapping
    public ResponseEntity<List<Tema>> getAll() {
        return ResponseEntity.ok(temaService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable Long id) {
        return temaService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(temaService.findByDescricao(descricao));
    }
    
    @PostMapping
    public ResponseEntity<Tema> post(@RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(temaService.save(tema));
    }
    
    @PutMapping
    public ResponseEntity<Tema> put(@RequestBody Tema tema) {
        return temaService.findById(tema.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                .body(temaService.save(tema)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Tema> tema = temaService.findById(id);
        
        if(tema.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        temaService.deleteById(id);
    }

}
