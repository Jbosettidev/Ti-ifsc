
# Requisitos

Antes de rodar o projeto, instale:

- Java 21
- Git
- PostgreSQL
- Postman
- IntelliJ

Também é necessário ter **um banco PostgreSQL já criado** para o projeto.

---

A aplicação irá iniciar em:

```
http://localhost:8080
```

---

# Testando a API com Postman

1. Abra o **Postman**
2. Crie uma nova requisição
3. Use a URL base:

```
http://localhost:8080
```

4. Escolha o método HTTP adequado:

- **GET** → buscar dados  
- **POST** → criar dados  
- **PUT** → atualizar dados  
- **DELETE** → remover dados  

5. Para requisições **POST ou PUT**, envie os dados em **JSON** no Body.

---

# Uso de Git e GitHub

## Atualizar o projeto

Antes de começar a trabalhar:

```bash
git pull
```

## Criar uma nova branch

```bash
git checkout -b nome-da-feature
```

## Salvar alterações

```bash
git add .
git commit -m "descrição da mudança"
```

## Enviar para o GitHub

```bash
git push origin nome-da-feature
```

Depois disso, abra um **Pull Request no GitHub**.

---

# Observações

- O PostgreSQL precisa estar **rodando antes de iniciar a aplicação**.
- A conexão com o banco é configurada no arquivo:

```
src/main/resources/application.properties
```
