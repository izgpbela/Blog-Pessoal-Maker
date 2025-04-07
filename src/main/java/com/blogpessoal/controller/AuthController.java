package com.blogpessoal.controller;

import com.blogpessoal.dto.UsuarioLoginDTO;
import com.blogpessoal.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            usuarioLoginDTO.getUsuario(), 
            usuarioLoginDTO.getSenha()
        );
        
        authentication = authenticationManager.authenticate(authentication);
        
        String token = jwtService.gerarToken(authentication);
        return ResponseEntity.ok(token);
    }
}
