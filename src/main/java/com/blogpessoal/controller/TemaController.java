package com.blogpessoal.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.acelera.blogmaker.model.dto.request.CreateThemeRequest;
import org.acelera.blogmaker.model.dto.response.ThemeResponse;
import org.acelera.blogmaker.services.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/themes")
public class TemaController {
    
	private final TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createTheme(@Valid @RequestBody CreateThemeRequest request) {
        Long themeId = temaService.createTheme(request);
        return ResponseEntity.created(URI.create("/api/v1/themes/" + themeId)).build();
    }

    @GetMapping("/{themeId}")
    public ResponseEntity<ThemeResponse> getTheme(@PathVariable Long themeId) {
        TemaResponse theme = temaService.getThemeById(themeId);
        return ResponseEntity.ok(theme);
    }

    @GetMapping
    public ResponseEntity<List<TemaResponse>> getAllThemes() {
        List<TemaResponse> themes = temaService.getAllThemes();
        return ResponseEntity.ok(themes);
    }

    @PutMapping("/{themeId}")
    @Transactional
    public ResponseEntity<TemaResponse> updateTheme(@PathVariable Long themeId,
                                                     @Valid @RequestBody CreateThemeRequest request) {
        TemaResponse updatedTheme = temaService.updateTheme(themeId, request);
        return ResponseEntity.ok(updatedTheme);
    }

    @DeleteMapping("/{themeId}")
    @Transactional
    public ResponseEntity<Void> deleteTheme(@PathVariable Long themeId) {
        temaService.deleteTheme(themeId);
        return ResponseEntity.noContent().build();
    }

}
