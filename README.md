**🗳️ API - SISTEMA DE VOTAÇÃO EM TEMPO REAL**

API REST para criação de enquetes, cadastro de usuários e registro de votos.

O projeto foi desenvolvido com foco em uma arquitetura organizada em camadas, separando a exposição dos endpoints, as regras de negócio, os objetos de transferência de dados e a persistência no banco de dados.

**🎯 OBJETIVO DO PROJETO**

O Sistema de Votação permite que usuários sejam cadastrados e participem de enquetes.

O fluxo principal da aplicação é:

1. 👤 Um usuário é cadastrado no sistema.

2. 📝 Uma enquete é criada com título, pergunta e opções de resposta.

3. 🔎 As enquetes disponíveis podem ser consultadas.

4. 🗳️ Um usuário escolhe uma opção e registra seu voto.

5. 🚫 O sistema valida se aquele usuário já votou na enquete.

6. 📊 O resultado pode ser consultado.

7. 🔒 A enquete pode ser encerrada, impedindo novas votações conforme as regras da aplicação.

8. 🛠️ Tecnologias utilizadas

As tecnologias abaixo foram escolhidas para estruturar a API, facilitar o desenvolvimento e organizar a persistência dos dados.

**☕ Java 21**

Linguagem principal utilizada no desenvolvimento da aplicação.

O Java fornece a base para a implementação das entidades, serviços, controladores, regras de negócio e demais componentes da aplicação.

🔗 Documentação oficial do Java — Oracle

**🌱 Spring Boot 4.1.0**

Framework utilizado como base da aplicação.

O Spring Boot facilita a criação de aplicações Java independentes e fornece recursos para configuração, execução e desenvolvimento de APIs.

🔗 Documentação oficial do Spring Boot

🔗 Guia oficial para criação de aplicações Spring Boot

**🌐 Spring Web MVC**

Utilizado para disponibilizar os endpoints HTTP da API e receber as requisições realizadas pelo cliente.

É por meio dessa camada que operações como criar usuário, consultar enquetes e registrar votos ficam disponíveis para consumo.

🔗 Documentação oficial do Spring Web MVC

**🗄️ Spring Data JPA**

Responsável por facilitar a comunicação entre a aplicação e o banco de dados através do padrão JPA.

Os repositórios da aplicação utilizam essa tecnologia para consultar e persistir as informações das entidades.

🔗 Documentação oficial do Spring Data JPA

**🐘 PostgreSQL**

Banco de dados relacional utilizado para armazenar os dados da aplicação.

Entre as informações persistidas estão usuários, enquetes, opções e votos.

🔗 Documentação oficial do PostgreSQL

🔗 Tutorial oficial do PostgreSQL

**🔄 Flyway**

Ferramenta utilizada para controle e versionamento das alterações do banco de dados.

As migrations permitem que a estrutura do banco seja criada e evolua de maneira organizada junto com o código da aplicação.

🔗 Documentação oficial do Flyway

**🧩 Lombok**

Biblioteca utilizada para reduzir código repetitivo em classes Java, principalmente na criação de métodos e estruturas comuns.

🔗 Documentação oficial do Lombok

**📦 Maven**

Ferramenta utilizada para gerenciamento do projeto, dependências, compilação e execução de testes.

O projeto possui Maven Wrapper, permitindo executar os comandos sem depender de uma instalação global do Maven.

🔗 Documentação oficial do Maven

**🏗️ CONSTRUÇÃO DO PROJETO**

A aplicação foi organizada seguindo uma separação de responsabilidades entre as principais camadas.

De forma simplificada:

Cliente
   │
   ▼
🎮 Controller
   │
   ▼
⚙️ Service
   │
   ▼
📚 Repository
   │
   ▼
🗄️ PostgreSQL

🎮 Controller

É a porta de entrada da API.

Os controllers recebem as requisições HTTP, extraem os dados enviados pelo cliente e direcionam a operação para a camada de serviço.

Exemplos:

.Criar usuário

.Criar enquete

.Buscar enquete

.Registrar voto

.Consultar resultado

.Enquete

**⚙️ Service**

Concentra as regras de negócio da aplicação.

Essa camada evita que regras importantes fiquem diretamente nos controllers.

Entre as responsabilidades estão:

Validar a existência de usuários e enquetes.

Verificar se uma opção pertence à enquete.

Impedir votos duplicados do mesmo usuário.

Verificar as condições necessárias para votação.

Processar o resultado da enquete.

Controlar o encerramento das enquetes.

**📚 Repository**

Responsável pelo acesso aos dados persistidos.

Os repositories utilizam Spring Data JPA para realizar operações de consulta, criação, alteração e relacionamento entre os dados.

**🗄️ Entity**

Representa os dados persistidos no banco de dados.

As entidades fazem a ligação entre os objetos Java e as tabelas utilizadas pelo PostgreSQL.

**📦 DTO**

Os DTOs são utilizados para transportar dados entre a API e o cliente.

Isso permite separar os objetos utilizados nas requisições e respostas da estrutura interna das entidades do banco.

**⚠️ Exception**

A camada de exceções concentra os erros específicos da aplicação e o tratamento das situações que não podem ser processadas normalmente.

**👤 INTERAÇÃO DO USUÁRIO**

O usuário interage com o sistema através dos endpoints disponibilizados pela API.

A interação pode ser dividida em três momentos principais:

**1. 👤 CRIAÇÃO DO USUÁRIO**

Antes de participar de uma votação, o usuário pode ser cadastrado informando seus dados.

Exemplo:

POST /usuarios

{
  "nome": "João",
  "email": "joao@email.com"
}

Depois do cadastro, o usuário passa a possuir um identificador que pode ser utilizado nas operações relacionadas às enquetes.

**🔎 BUSCA E CONSULTA DE ENQUETES**

Depois que existem enquetes cadastradas, o usuário pode consultar as opções disponíveis.

**📋 LISTAR ENQUETES**

GET /enquetes

Esse endpoint permite obter as enquetes cadastradas.

**🔍 CONSULTAR UMA ENQUETE**

GET /enquetes/{id}

A consulta individual permite obter os detalhes de uma enquete específica, utilizando seu identificador.

**📊 CONSULTAR RESULTADO**

GET /enquetes/{id}/resultado

Esse endpoint permite visualizar o resultado da votação de uma enquete.

**🗳️ PROCESSO DE VOTAÇÃO**

O processo de votação acontece quando o usuário escolhe uma opção pertencente a uma enquete.

A operação é realizada através de:

POST /enquetes/{id}/votos

O sistema recebe os dados necessários para identificar o usuário e a opção escolhida.

Exemplo:

{
  "usuarioId": 1,
  "opcaoId": 2
}

Durante esse processo, a camada de serviço realiza as validações necessárias antes de registrar o voto.

**🔐 VALIDAÇÕES**

Entre as principais verificações estão:

1. 👤 O usuário precisa existir.

2. 📋 A enquete precisa existir.

3. 📝 A opção escolhida precisa pertencer à enquete.

4. 🚫 O usuário não pode votar duas vezes na mesma enquete.

5. 🔒 A enquete precisa estar disponível para votação.

Somente depois que as regras forem atendidas o voto é persistido no banco de dados.

**📝 CRIAÇÃO DE UMA ENQUETE**

Uma enquete pode ser criada informando seu título, pergunta, usuário responsável e opções de resposta.

Endpoint:

POST /enquetes

Exemplo:

{
  
  "titulo": "Eleição",
  
  "pergunta": "Qual opção você prefere?",
  
  "usuarioId": 1,
  
  "opcoes": [
    
"Opção A",
"Opção B",
"Opção C"
  
  ]

}

A partir dessa operação, a aplicação cria a estrutura necessária para que a enquete possa ser consultada e receber votos.

**🔒 ENCERRAMENTO DE UMA ENQUETE**

Uma enquete pode ser encerrada através do endpoint:

PATCH /enquetes/{id}/encerrar

O encerramento altera o estado da enquete e permite que o sistema controle se novas votações podem ser realizadas.

**🔗 PRINCIPAIS ENDPOINTS**

**👤 Usuários**

Método

Endpoint

Descrição

POST

/usuarios

**➕ Cria um usuário**

GET

/usuarios

**📋 Lista os usuários**

**📋 Enquetes**

Método

Endpoint

Descrição

POST

/enquetes

**➕ Cria uma enquete**

GET

/enquetes

**📋 Lista as enquetes**

GET

/enquetes/{id}

**🔎 Consulta uma enquete**

GET

/enquetes/{id}/resultado

**📊 Consulta o resultado**

PATCH

/enquetes/{id}/encerrar

**🔒 Encerra uma enquete**

**🗳️ Votos**

Método

Endpoint

Descrição

POST

/enquetes/{id}/votos

**🗳️ Registra um voto**

**🗂️ Estrutura do projeto**

src/main/java/

├── 🎮 controller/

│   └── Endpoints da API



├── 📦 dto/

│   └── Objetos de entrada e saída

│

├── ⚠️ exception/

│   └── Exceções e tratamento de erros

│

├── 🏗️ infrastructure/

│   ├── 🗄️ entity/

│   │   └── Entidades do banco

│

│   └── 📚 repository/

└── Repositórios JPA

│

⚙️ service/
    └── Regras de negócio

**🗄️ CONFIGURAÇÃO DO BANCO DE DADOS**

Crie um banco PostgreSQL chamado:

sistema_votacao

As configurações atuais estão em:

src/main/resources/application.properties

Configuração utilizada no projeto:

spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_votacao
spring.datasource.username=postgres
spring.datasource.password=238759

**⚠️ Importante: em ambientes reais, não é recomendado manter senhas diretamente no código ou no arquivo de configuração versionado. Prefira variáveis de ambiente ou um mecanismo de gerenciamento de secrets.**

As migrations do Flyway são executadas para manter a estrutura do banco sincronizada com a aplicação.

**📋 Pré-requisitos**

Antes de executar o projeto, tenha instalado:

.☕ Java 21

.🐘 PostgreSQL

.📦 Maven, caso não utilize o Maven Wrapper

.💻 Uma IDE ou editor de código de sua preferência

.▶️ Como executar

Clone ou extraia o projeto e entre na pasta principal.

Linux / macOS

./mvnw spring-boot:run

Windows

mvnw.cmd spring-boot:run

**🚀 A aplicação será iniciada na porta padrão:**

8080

A API poderá então ser acessada através de:

http://localhost:8080

**🧪 Executando os testes**

Para executar os testes automatizados:

Linux / macOS

./mvnw test

Windows

mvnw.cmd test

Os testes ajudam a verificar se as funcionalidades implementadas continuam funcionando conforme esperado.

**🔄 Fluxo completo de utilização**

Um fluxo típico de utilização do sistema pode seguir esta sequência:

1. 👤 Criar usuário
      │
      ▼
2. 📝 Criar enquete
      │
      ▼
3. 🔎 Buscar enquete
      │
      ▼
4. 📋 Visualizar opções
      │
      ▼
5. 🗳️ Registrar voto
      │
      ▼
6. 📊 Consultar resultado
      │
      ▼
7. 🔒 Encerrar enquete

Esse fluxo representa a interação principal entre o usuário e a API, desde o cadastro até o encerramento de uma votação.

**💡 Conceitos utilizados**

O projeto também serve como exemplo prático de alguns conceitos importantes no desenvolvimento de APIs:

.🌐 API REST

.🏗️ Arquitetura em camadas

.🎯 Separação de responsabilidades

.📦 DTOs

.🗄️ Persistência relacional

.🔗 Relacionamentos entre entidades

.⚙️ Regras de negócio

.⚠️ Tratamento de exceções

. Versionamento de banco de dados

.🧪 Testes automatizados

.📡 Comunicação através de HTTP

.📚 Documentação oficial das tecnologias

Para quem não conhece alguma das tecnologias utilizadas, os links abaixo direcionam para suas respectivas documentações oficiais:

.☕ Java — Oracle

.🌱 Spring Boot — Spring

.🌐 Spring Framework / Web MVC

.🗄️ Spring Data JPA

.🐘 PostgreSQL

.🔄 Flyway

.🧩 Lombok

.📦 Maven

.⭐ Sobre o projeto

O Sistema de Votação foi desenvolvido como uma aplicação backend para praticar e demonstrar conceitos de desenvolvimento de APIs REST utilizando Java e Spring Boot, com persistência em PostgreSQL e organização do código em diferentes camadas.

A estrutura permite evoluir o projeto futuramente com funcionalidades como autenticação, autorização, paginação, documentação OpenAPI/Swagger, frontend e outras regras de votação.

**👨‍💻 DESENVOLVIMENTO**

Projeto desenvolvido para fins de estudo e prática de desenvolvimento de APIs REST com Java e Spring Boot.
