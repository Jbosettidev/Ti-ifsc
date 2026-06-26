# SecureByte 

### Web app de cibersegurança para adolescentes

Aprenda o **básico que importa** na internet — senhas, golpes, phishing e privacidade — em um só lugar.

---

## Projeto acadêmico (**Trabalho Integrador**). Linguagem acessível para jovens — o básico da segurança digital.


| Foco | O que o usuário leva                                                                |
| ---- |-------------------------------------------------------------------------------------|
| 🎯   | **Hábitos seguros** de forma simples e por **níveis**                               |
| 🛡️  | Reconhecer **riscos comuns** (mensagens falsas, urgência falsa, clonagem de perfil) |
| 🎮   | **Atividades práticas** + **progresso** e **conquistas**                            |
---
| Tema | Por que importa |
| ---- | --------------- |
| 🔑 Senhas fortes e únicas | Menos contas comprometidas em cadeia |
| 🪝 Phishing e links suspeitos | Vetor muito comum em e-mail e redes |
| ⏱️ Urgência e "golpe do parente" | Engenharia social que mira jovens |
| 🔒 Privacidade e o que não postar | Menos exposição a abuso online |
| 📲 Apps oficiais e atualizações | Menos malware e apps falsos |
---

## Stack

- **Backend:** Java 21 + Spring Boot (Web MVC, JPA, validação) — API REST
- **Banco:** PostgreSQL (Supabase na nuvem **ou** container local via Docker)
- **Frontend:** HTML/CSS estático servido pelo Spring Boot

---

## Como rodar com Docker

> **Pré-requisito:** [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado e rodando.

### 1. Suba a aplicação

```bash
docker compose up --build
```

Aguarde as mensagens de build.

### 2. Parar

```bash
docker compose down
```

---

## Supabase (banco)

> ⚠️ Projetos gratuitos no Supabase **pausam após inatividade**. Se a API não conectar, acesse o painel do Supabase e retome o projeto.

---
## Links uteis
> **documentacao do sprintboot**:  [Doc](https://docs.spring.io/spring-boot/index.html/)