# 📚 Blog Pessoal - API REST com Spring Security e JWT

**Desenvolvido por: izgpbela**  
✨ Uma aplicação Spring Boot completa para gerenciamento de blog pessoal com autenticação segura via JWT

## 🚀 Visão Geral

Este projeto implementa uma API RESTful para um sistema de blog pessoal com:

- Autenticação segura via JWT
- CRUD completo de postagens
- Gerenciamento de usuários
- Documentação automática com Swagger
- Arquitetura em camadas seguindo boas práticas

## 🔧 Tecnologias Utilizadas

| Categoria         | Tecnologias                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| Backend           | Java 17, Spring Boot 3.x, Spring Security, JWT                             |
| Persistência      | Spring Data JPA, Hibernate, MySQL/PostgreSQL                               |
| Documentação      | Swagger/OpenAI                                                               |
| Testes            | Postman                                                                    | 
| Ferramentas       | Maven, Git                                                                 |

## ⚙️ Configuração Inicial

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/izgpbela/blog-pessoal.git
   cd blog-pessoal
   ```

2. **Configure o banco de dados** em `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_pessoal
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Execute a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

## 🔐 Endpoints Principais

### Autenticação (`/api/v1/auth`)
| Método | Endpoint       | Descrição                     |
|--------|----------------|-------------------------------|
| POST   | `/login`       | Autenticação e geração de JWT |
| POST   | `/register`    | Registro de novos usuários    |

### Postagens (`/api/v1/posts`)
| Método | Endpoint       | Descrição                     |
|--------|----------------|-------------------------------|
| GET    | `/`            | Lista todas as postagens      |
| POST   | `/`            | Cria nova postagem            |
| GET    | `/{id}`        | Busca postagem por ID         |
| PUT    | `/{id}`        | Atualiza postagem             |
| DELETE | `/{id}`        | Remove postagem               |


## 🔍 Documentação com Swagger

Acesse a documentação interativa em:
```
http://localhost:8080/swagger-ui.html
```

Recursos disponíveis:
- Visualização de todos os endpoints
- Teste direto na interface
- Exemplos de requisições/respostas
- Configuração para autenticação JWT

## 🛡️ Configuração de Segurança

- Autenticação via JWT (JSON Web Tokens)
- Senhas criptografadas com BCrypt
- Rotas protegidas por roles
- Filtro JWT personalizado
- Configuração CORS para desenvolvimento

## 💻 Como Testar

1. **Registre um usuário**:
   ```bash
   curl -X POST http://localhost:8080/api/v1/auth/register \
     -H "Content-Type: application/json" \
     -d '{"nome":"Teste","email":"teste@email.com","senha":"senha123"}'
   ```

2. **Faça login** para obter o token:
   ```bash
   curl -X POST http://localhost:8080/api/v1/auth/login \
     -H "Content-Type: application/json" \
     -d '{"email":"teste@email.com","senha":"senha123"}'
   ```

3. **Acesse recursos protegidos**:
   ```bash
   curl -X GET http://localhost:8080/api/v1/posts \
     -H "Authorization: Bearer SEU_TOKEN_JWT"
   ```

## 🏗️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/blogpessoal/
│   │   ├── config/        # Configurações (Security, Swagger)
│   │   ├── controller/    # Endpoints REST
│   │   ├── dto/           # Objetos de transferência
│   │   ├── model/         # Entidades JPA
│   │   ├── repository/    # Interfaces Spring Data
│   │   ├── security/      # Config JWT e filtros
│   │   └── service/       # Lógica de negócio
│   └── resources/
│       └── application.properties
└── test/                  # Testes unitários e integração
```


---

Desenvolvido com ❤️ por [izgpbela](https://github.com/izgpbela)  
Licença MIT © 2025 Blog Pessoal
