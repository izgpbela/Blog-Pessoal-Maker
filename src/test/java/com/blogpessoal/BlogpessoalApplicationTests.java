package com.blogpessoal;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BlogpessoalApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        // Verifica se o contexto Spring é carregado corretamente
        assertTrue(context != null, "O contexto da aplicação deve ser carregado");
    }

    @Test
    void mainMethodRuns() {
        // Testa se a aplicação inicia corretamente
        BlogpessoalApplication.main(new String[] {});
        assertTrue(true, "A aplicação deve iniciar sem erros");
    }
}