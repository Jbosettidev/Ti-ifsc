# SecureByte / TI

### Web app de cibersegurança para adolescentes

Aprenda o **básico que importa** na internet — senhas, golpes, phishing e privacidade — em um só lugar.

---

## Sobre o projeto

| Foco | O que o usuário leva |
| ---- | -------------------- |
| 🎯   | **Hábitos seguros** de forma simples e por **níveis** |
| 🛡️  | Reconhecer **riscos comuns** (mensagens falsas, urgência falsa, clonagem de perfil) |
| 🎮   | **Atividades práticas** (e-mail / quiz) + **progresso** e **conquistas** |

Projeto acadêmico (**Trabalho Integrador**). Linguagem acessível para jovens — o básico da segurança digital.

---

## Stack

- **Backend:** Java 21 + Spring Boot (Web MVC, JPA, validação) — API REST
- **Banco:** PostgreSQL (Supabase na nuvem **ou** container local via Docker)
- **Frontend:** HTML/CSS estático servido pelo Spring Boot

---

## Como rodar com Docker (recomendado)

> **Pré-requisito:** [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado e rodando.

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd Ti-ifsc
```

### 2. Configure as variáveis de ambiente

```bash
cp .env.example .env
```

Abra o `.env` e ajuste se quiser mudar usuário/senha do banco local:

```
DB_USERNAME=postgres
DB_PASSWORD=securebyte26.
```

### 3. Suba a aplicação

```bash
docker compose up --build
```

Aguarde as mensagens de build. Quando aparecer algo como `Started TiApplication`, acesse:

```
http://localhost:8080
```

### 4. Parar

```bash
docker compose down
```

Para parar **e apagar os dados do banco**:

```bash
docker compose down -v
```

---

## Usando o Supabase (banco na nuvem)

Se preferir manter o banco no Supabase ao invés de rodar o PostgreSQL local, edite o `docker-compose.yml` e substitua as variáveis do serviço `app`:

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres
  SPRING_DATASOURCE_USERNAME: postgres.ikkepkdqliynfgoztybo
  SPRING_DATASOURCE_PASSWORD: sua-senha-aqui
```

Depois remova o serviço `db` e o volume `postgres_data` do arquivo, e suba só a aplicação:

```bash
docker compose up --build app
```

> ⚠️ Projetos gratuitos no Supabase **pausam após inatividade**. Se a API não conectar, acesse o painel do Supabase e retome o projeto.

---

## Comandos úteis

| Comando | O que faz |
| ------- | --------- |
| `docker compose up --build` | Compila e sobe tudo |
| `docker compose up -d` | Sobe em background (sem travar o terminal) |
| `docker compose down` | Para os containers |
| `docker compose down -v` | Para e apaga o volume do banco |
| `docker compose logs -f app` | Acompanha os logs da aplicação em tempo real |
| `docker compose logs -f db` | Acompanha os logs do banco |
| `docker compose ps` | Lista os containers rodando |

---

## Estrutura do projeto

```
Ti-ifsc/
├── Dockerfile              ← build da imagem da aplicação
├── docker-compose.yml      ← orquestração app + banco
├── .env.example            ← modelo de variáveis de ambiente
├── pom.xml
└── src/
    └── main/
        ├── java/com/example/demo/
        │   ├── TiApplication.java
        │   ├── user/           ← cadastro e autenticação
        │   ├── fase/           ← fases e progresso
        │   ├── quiz/           ← quizzes e questões
        │   └── excessoes/      ← tratamento de erros
        └── resources/
            ├── application.properties
            └── static/         ← frontend HTML/CSS
                ├── index.html
                ├── TelasIniciais/   ← login, cadastro, senha
                ├── Jogo1-Email/     ← simulação de phishing
                └── perfil-usuario/  ← perfil e configurações
```

---

## Telas principais

| URL | Descrição |
| --- | --------- |
| `http://localhost:8080/` | Hub da Unidade 1, níveis 1–6 |
| `http://localhost:8080/TelasIniciais/login.html` | Login |
| `http://localhost:8080/TelasIniciais/cadastro.html` | Cadastro |
| `http://localhost:8080/Jogo1-Email/email1.html` | Simulação de phishing |
| `http://localhost:8080/perfil-usuario/configuracoes.html` | Perfil do usuário |

---

## Conteúdo pedagógico

| Tema | Por que importa |
| ---- | --------------- |
| 🔑 Senhas fortes e únicas | Menos contas comprometidas em cadeia |
| 🪝 Phishing e links suspeitos | Vetor muito comum em e-mail e redes |
| ⏱️ Urgência e "golpe do parente" | Engenharia social que mira jovens |
| 🔒 Privacidade e o que não postar | Menos exposição a abuso online |
| 📲 Apps oficiais e atualizações | Menos malware e apps falsos |
