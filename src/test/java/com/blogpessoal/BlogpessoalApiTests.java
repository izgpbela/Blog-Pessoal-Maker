package com.blogpessoal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blogpessoal.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BlogpessoalApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build();
    }

    @Test
    void testGetAllPosts() throws Exception {
        mockMvc.perform(get("/api/v1/posts"))
               .andExpect(status().isOk());
    }

    @Test
    void testCreatePost() throws Exception {
        Post newPost = new Post();
        newPost.setTitle("Título do Teste");
        newPost.setContent("Conteúdo do Teste");

        mockMvc.perform(post("/api/v1/posts")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(newPost)))
               .andExpect(status().isCreated());
    }

    @Test
    void testGetPostById() throws Exception {
        // Primeiro cria um post para depois buscar
        Post post = new Post();
        post.setTitle("Post para Busca");
        post.setContent("Conteúdo para teste de busca");

        String response = mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Post createdPost = objectMapper.readValue(response, Post.class);

        // Agora testa a busca
        mockMvc.perform(get("/api/v1/posts/" + createdPost.getId()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.titulo").value("Post para Busca"));
    }
}