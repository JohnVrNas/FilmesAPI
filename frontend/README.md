🎬 Projeto Sistema de Cadastro de Filmes
Este projeto é um sistema simples para cadastro de usuários e filmes, utilizando:

✅ Spring Boot para o backend
✅ H2 como banco de dados em memória
✅ Bootstrap + HTML/CSS/JS para o frontend separado

🚀 Como rodar o projeto
✅ Pré-requisitos:
Java 17 ou superior

Maven

Navegador web

Postman (opcional, para testar API)

⚙️ Configuração do Backend
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
Execute o projeto com Maven:

bash
Copiar
Editar
mvn spring-boot:run
O backend estará rodando em:
👉 http://localhost:8080

Acesse o console do banco de dados H2:
👉 http://localhost:8080/h2-console

Configurações:

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (deixe em branco)

🛠️ Configurações importantes no application.properties
properties
Copiar
Editar
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
🌐 Configuração do Frontend
O frontend está na pasta /frontend.

Para rodar, basta abrir o arquivo index.html com o navegador.

Dica: Pode usar uma extensão de Live Server no VS Code para facilitar.

🧪 Teste das Rotas com Postman
Cadastrar usuário: POST /user/novouser

Listar usuários: GET /user/usercadastrados

Criar filme: POST /filmes/criarfilme

Listar filmes: GET /filmes/listafilme

Atualizar filme: PUT /filmes/atualizarfilme/{id}

Deletar filme: DELETE /filmes/deletarfilme/{id}

Autenticação:
Basic Auth com username e senha do usuário cadastrado.

✅ Tecnologias Utilizadas
Java 17

Spring Boot

Spring Data JPA

H2 Database

Bootstrap

HTML/CSS/JS

💡 Próximos passos
Evoluir para autenticação via JWT

Melhorar segurança

Adicionar paginação na listagem

Deploy

🤝 Contribuições
Sinta-se livre para abrir issues ou pull requests!

📄 Licença
Este projeto está licenciado sob a MIT License.