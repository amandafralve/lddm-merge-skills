
# Projeto KMP + Ktor + PostgreSQL

## Pré-requisitos

- JDK 21
- IntelliJ IDEA
- Docker Desktop

---

## 1. Como inicializar o projeto

Ao abrir o projeto no IntelliJ, confira estas configurações:

### Java / Gradle
- **Project SDK**: JDK 21
- **Gradle JVM**: jbr-21 ou JDK 21

---

## 2. Como subir o banco com Docker

Na raiz do projeto, abra o terminal e rode:

### PowerShell
```powershell
docker compose down -v
docker compose up -d
```

#### O que isso faz

- remove containers e volumes antigos
- sobe o PostgreSQL novamente
- cria o banco configurado no `docker-compose.yml`

---

## 3. Como rodar o backend no IntelliJ

No IntelliJ:

1. abra o arquivo `Application.kt`
2. clique no botão de **Run** ao lado da função `main()`

O backend deverá subir na porta:

```
http://localhost:8080
```

Se estiver tudo certo, você poderá acessar:

```
http://localhost:8080/plants
http://localhost:8080/species
http://localhost:8080/swagger
```

---

## 4. Como rodar o backend pelo terminal

Na raiz do projeto:

### PowerShell

```powershell
.\gradlew :server:build -x test
```

Depois, para executar, rode pelo IntelliJ. Se quiser apenas compilar:

```powershell
.\gradlew :server:classes
```

---

## 5. Como rodar o app

Depois que o backend estiver rodando, execute o módulo do app no IntelliJ.

Se o app estiver sendo testado em **celular físico**, no arquivo da API use o IP do computador, por exemplo:

```kotlin
.get("http://192.168.1.12:8080/plants")
```




---

## 6. Swagger

A documentação Swagger fica disponível em:

```
http://localhost:8080/swagger
```


---

## 7. Se o banco não for criado automaticamente

Se o banco não subir sozinho pelo Docker, crie manualmente no PostgreSQL.

### Banco esperado

- **database**: `plants-db`
- **user**: `postgres`
- **password**: `postgres`

### Criando manualmente no pgAdmin ou psql

```sql
CREATE DATABASE "plants-db";
```

Como o nome tem hífen, precisa usar aspas duplas.

---

## 8. Se der erro de autenticação no banco

Verifique se os dados do `docker-compose.yml` e do `DatabaseFactory.kt` estão iguais.

Exemplo:

### `docker-compose.yml`

```yaml
environment:
  POSTGRES_USER: postgres
  POSTGRES_PASSWORD: postgres
  POSTGRES_DB: plants-db
```

### `DatabaseFactory.kt`

```kotlin
val dbUrl = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/plants-db"
val dbUser = System.getenv("DB_USER") ?: "postgres"
val dbPassword = System.getenv("DB_PASSWORD") ?: "postgres"
```

Se mudar usuário, senha ou nome do banco, recrie o container:

```powershell
docker compose down -v
docker compose up -d
```


---

## 9. Ordem correta para rodar o projeto

### Passo a passo

1. abrir o projeto no IntelliJ
2. conferir JDK 21 / Gradle JVM
3. rodar:

```powershell
docker compose down -v
docker compose up -d
```

1. subir o backend pelo `Application.kt`
2. testar no navegador:

```
http://localhost:8080/plants
```

1. abrir o Swagger:

```
http://localhost:8080/swagger
```

1. rodar o app

---

## 10. Endpoints principais

### Plants

- `GET /plants`
- `POST /plants`
- `PUT /plants/{id}`
- `DELETE /plants/{id}`

### Species

- `GET /species`
- `POST /species`
- `PUT /species/{id}`
- `DELETE /species/{id}`

