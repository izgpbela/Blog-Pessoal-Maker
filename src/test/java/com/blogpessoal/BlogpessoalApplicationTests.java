package com.blogpessoal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BlogpessoalApplicationTests {

    @Test
    void contextLoads() {
        // Teste simples para verificar se o contexto da aplicação é carregado corretamente
        assertTrue(true, "O contexto da aplicação deve carregar sem erros");
    }

    @Test
    void mainMethodRuns() {
        // Teste adicional para verificar se o método main executa sem erros
        BlogpessoalApplication.main(new String[] {});
    }

}
