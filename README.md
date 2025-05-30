
# 📖 FilmesAPI

API de Filmes baseada no aplicativo Letterboxd, com frontend e backend separados.

---

## 🗂️ Estrutura do Projeto

```
/
├── aula-estrutura-projetoapi   # Backend (Spring Boot)
├── frontend                    # Frontend (HTML, JS, Bootstrap)
├── LICENSE
├── README.md
└── .gitattributes
```

---

## 🚀 Tecnologias Utilizadas

### ✅ Backend
- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Banco de Dados**: H2 (ou outro, conforme configuração)
- **JWT** para autenticação

### ✅ Frontend
- **HTML5**
- **JavaScript**
- **Bootstrap 5**
- **Fetch API** para requisições

---

## ⚙️ Como rodar o projeto

### ▶️ Backend:

**Pré-requisitos:**
- Java **21** instalado
- Maven ou Gradle
- IDE (recomendado: IntelliJ ou VS Code com extensões Java)

**Passos:**

1. Navegue até a pasta do backend:
   ```bash
   cd aula-estrutura-projetoapi
   ```

2. Compile e rode o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
   ou
   ```bash
   ./gradlew bootRun
   ```

3. A API estará disponível em:
   ```
   http://localhost:8080
   ```

4. A documentação da API pode ser acessada via Postman ou outra ferramenta de testes, conforme os endpoints criados.

---

### ▶️ Frontend:

**Pré-requisitos:**
- Navegador web moderno (Chrome, Firefox, Edge...)
- **Backend rodando** para funcionar corretamente

**Passos:**

1. Navegue até a pasta do frontend:
   ```bash
   cd frontend
   ```

2. Abra o `index.html` diretamente no navegador ou com uma extensão de servidor local (recomendado: Live Server no VS Code).

3. Utilize as páginas:
   - `index.html`: Página inicial
   - `listagem.html`: Lista de filmes
   - `adicionar.html`: Formulário de adicionar filmes
   - `editar.html`: Formulário de edição

---

## ✅ Importante:

- O frontend realiza chamadas para a API utilizando o `fetch()` e requer que o **backend esteja rodando**.
- As requisições incluem autenticação via token JWT, armazenado no `localStorage`.

---

## 🔑 Variáveis importantes:

Se desejar rodar em ambientes diferentes, configure:
- **Porta do backend** (`application.properties` no Spring Boot).
- URLs no frontend (endpoints da API).

---

## 📝 Licença

Este projeto está licenciado sob a **MIT License**.

---

## 💡 Sugestões:

- Pode adicionar no futuro um arquivo `.env` para configuração dinâmica de URL da API.
- Criar um script `start.sh` ou `Makefile` para automatizar a execução de backend e frontend juntos.
