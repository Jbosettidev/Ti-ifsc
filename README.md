# 👥 Guia Rápido – Como Rodar o Projeto 

Este guia é apenas para configurar e executar localmente.

Stack:

* Java 25
* IntelliJ
* Spring Boot (Web)
* Lombok
* DevTools

---

# ✅ 1️⃣ Pré-requisitos

Cada membro precisa ter instalado:

* Java 25
* Git
* IntelliJ
* Postman (opcional pra testar api)

Verificar no terminal:

```bash
java -version
git --version
```

Se o Java não for 25, ajustar antes de continuar.

---

# 📥 2️⃣ Clonar o Repositório

No terminal:

```bash
git clone https://github.com/SEU-USUARIO/Ti-ifsc/

entra na pasta
```

---

# 🧠 3️⃣ Abrir no IntelliJ

1. Open
2. Selecionar a pasta do projeto
3. Se pedir para habilitar Lombok → aceitar

Se Lombok não funcionar:

* Ir em Settings → Plugins
* Garantir que Lombok está instalado
* Settings → Build → Annotation Processors → Enable

---

# ▶️ 4️⃣ Rodar o Projeto

Opção 1 (pela IDE):

* Abrir 
* Clicar em Run

Opção 2 (terminal):

```bash
mvn spring-boot:run
```

Se aparecer:

```
Tomcat started on port 8080
```

Está funcionando.

---

# 🧪 5️⃣ Testar no Postman

Exemplo:

```
GET http://localhost:8080/api/ping
```

Se retornar resposta da API, está tudo certo.

---

# 🔁 6️⃣ Como Trabalhar Sem Quebrar o Projeto

Antes de começar qualquer tarefa:

```bash
git pull origin main
```

Criar branch própria:

```bash
git checkout -b feature/nome-da-feature
```

Fazer alterações.

Commit:

```bash
git add .
git commit -m "feat: descrição clara da alteração"
```

Enviar:

```bash
git push origin feature/nome-da-feature
```

Abrir Pull Request no GitHub.

Nunca trabalhar direto na main.

---

# 🚨 Se Der Problema

Projeto não sobe?

* Verificar versão do Java
* Verificar se Maven está funcionando
* Rodar `mvn clean install`

Erro de porta ocupada?

* Verificar se já existe outra aplicação rodando na 8080

---

# 📌 Regra Final

Se todos:

* Atualizarem antes de começar
* Usarem branch própria
* Não mexerem direto na main

O projeto permanece estável.
