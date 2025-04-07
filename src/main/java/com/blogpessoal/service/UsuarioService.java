package com.blogpessoal.service;
import io.micrometer.common.util.StringUtils;
import com.blogpessoal.exception.UserAlreadyExistException;
import com.blogpessoal.exception.UserNotFoundException;
import com.blogpessoal.model.Role;
import com.blogpessoal.model.Usuario;
import com.blogpessoal.dto.request.CreateUserRequest;
import com.blogpessoal.dto.request.UpdateUserRequest;
import com.blogpessoal.dto.response.UserResponse;
import com.blogpessoal.repository.UsuarioRepository;
import com.blogpessoal.service.maps.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

	 private final UsuarioRepository repository;
	    private final UserMapper mapper;
	    private static final String USER_NOT_FOUND = "User not found with id: ";

	   public UsuarioService(UsuarioRepository repository, UserMapper mapper) {
	        this.repository = repository;
	        this.mapper = mapper;
	   }

	    public Usuario createUser(CreateUserRequest request, Role role) {
	       var userEmailExists = repository.existsByEmail(request.email());

	        if(Boolean.TRUE.equals(userEmailExists))
	            throw new UserAlreadyExistException("User already exist in database");

	        return repository.save(mapper.toUser(request, role));
	    }

	    private void setUser(Usuario usuario, UpdateUserRequest request) {
	        mergeUser(usuario, request);
	        repository.save(usuario);
	    }

	    private void mergeUser(Usuario usuario, UpdateUserRequest request) {
	        if(StringUtils.isNotBlank(request.name())) usuario.setName(request.name());

	        if(StringUtils.isNotBlank(request.password())) usuario.setPassword(request.password());

	        if(StringUtils.isNotBlank(request.email())) usuario.setEmail(request.email());

	        if(StringUtils.isNotBlank(request.foto())) usuario.setPhoto(request.foto());
	    }

	    public UserResponse updateUser(UUID userId, UpdateUserRequest request) {
	        var user = repository
	                .findById(userId)
	                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + userId));

	        setUser(user, request);
	        return mapper.fromUser(user);
	    }

	    public UserResponse getUserById(UUID userId) {
	        return repository
	                .findById(userId)
	                .map(this.mapper::fromUser)
	                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + userId));
	    }

	    public List<UserResponse> findAllUsers() {
	        return this.repository.findAll()
	                .stream()
	                .map(this.mapper::fromUser)
	                .toList();
	    }

	    public void deleteUser(UUID userId) {
	        if(!repository.existsById(userId))
	            throw new UserNotFoundException(String.format("User with id: %s not found", userId));

	        repository.deleteById(userId);
	    }
}