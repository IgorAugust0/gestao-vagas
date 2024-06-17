# Aplicação de Gerenciamento de Vagas de RH

Esta é uma aplicação Spring Boot que permite o gerenciamento de vagas de Recursos Humanos (RH).

## Requisitos

- Java 17 ou superior
- Maven
- Banco de dados (Postgres)

## Configuração

1. Clone o repositório:

   ```shell
   git clone https://github.com/IgorAugust0/gestao-vagas.git
   ```

2. Configure o banco de dados criando um arquivo `.env` no diretório [resources](src\main\resources), seguindo o exemplo do arquivo [.env.example](src\main\resources.env.example):

   ```properties
   DB_URL=jdbc:postgresql://localhost:5432/mydatabase
   DB_DRIVER_CLASS_NAME=org.postgresql.Driver
   DB_USERNAME=myusername
   DB_PASSWORD=mypassword
   JWT_SECRET_KEY=mysecretkey
   ```

3. Construa a aplicação:

   ```shell
   mvn clean install
   ```

4. Inicie a aplicação

   ```shell
   mvn spring-boot:run
   ```

A aplicação agora deve estar em execução em <http://localhost:8080>.

## Endpoints

Os endpoints estão disponíveis no arquivo [endpoints.http](./endpoints.http). Há exemplos de requisições para cada endpoint. Em algumas rotas, como a de criação de vagas, é necessário informar o token de autenticação, que é gerado ao fazer login, isto é, o endpoint de login deve ser chamado antes de criar uma vaga, garantindo assim, que o usuário está autenticado por meio do **JWT** (Json Web Token).

## Teste dos Endpoints/API

Fiz uso do **_Httpie_** para testar os endpoints da API, mas fique a vontade para fazer uso de qualquer outro serviço, como _Postman_, _Apidog_, entre outros. Além disso, você pode instalar o Httpie [Desktop](https://httpie.io/docs/desktop/getting-started) ou [terminal](https://httpie.io/docs/cli) (CLI) para testar os endpoints.
