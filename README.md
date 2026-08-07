# API-Sistema-de-votacao

🗳️ Sistema de Votação — API REST em Java

API REST desenvolvida em Java com Spring Boot para gerenciamento de usuários, enquetes, opções de voto e registro de votos.

🚧 Status: Em desenvolvimento.

📌 Sobre o projeto

O sistema permite criar enquetes com várias opções e registrar votos respeitando regras de negócio. Cada enquete pode estar ABERTA, ENCERRADA ou CANCELADA.

Fluxo principal

Usuário → Escolhe enquete → Escolhe opção → API valida regras
→ Voto salvo → Contador atualizado → Resultado consultado

🎯 Regras de negócio

1. Um usuário pode criar uma enquete.

2. Uma enquete possui uma pergunta e várias opções.

3. A enquete pode estar ABERTA, ENCERRADA ou CANCELADA.

4. Só é possível votar em enquete ABERTA.

5. O usuário só pode votar em uma opção pertencente à enquete.

6. Cada usuário pode votar apenas uma vez na mesma enquete.

7. O total de votos é atualizado após o voto.

8. O resultado apresenta total, votos por opção, percentual e vencedora.

Enquetes encerradas não aceitam novos votos.

O ranking é ordenado pela maior quantidade de votos.

🧩 Modelo de dados

>Usuario

>id
>nome
>email

Relacionamentos:

Usuario 1 ─── N Enquete
Usuario 1 ─── N Voto

Enquete

id
titulo
pergunta
status
dataCriacao
dataEncerramento
usuario

Relacionamentos:

Enquete N ─── 1 Usuario
Enquete 1 ─── N OpcaoVoto
Enquete 1 ─── N Voto

OpcaoVoto

id
texto
quantidadeVotos
enquete

Voto

id
usuario
enquete
opcao
dataVoto

A combinação usuario_id + enquete_id deve ser única para impedir votos duplicados.

🏗️ Arquitetura

src/main/java
│
├── controller       # Endpoints REST
├── dto              # Objetos de entrada e saída
├── service          # Regras de negócio
└── infrastructure
    ├── entity       # Entidades JPA
    └── repository   # Acesso aos dados

Responsabilidades

Controller: recebe requisições HTTP e retorna respostas.

DTO: define os dados de entrada e saída da API.

Service: concentra as regras de negócio.

Entity: representa as entidades persistidas.

Repository: realiza o acesso ao banco através do Spring Data JPA.

🔌 Endpoints

Usuários

POST /usuarios

Exemplo:

{
  "nome": "João da Silva",
  "email": "joao@email.com"
}

Enquetes

POST /enquetes
GET /enquetes
GET /enquetes/{enqueteId}
PATCH /enquetes/{enqueteId}/encerrar

Votação

POST /enquetes/{enqueteId}/votos

Payload:

{
  "usuarioId": 1,
  "opcaoId": 2
}

Resultado

GET /enquetes/{enqueteId}/resultado

Exemplo de resposta:

{
  "totalVotos": 100,
  "opcoes": [
    {
      "texto": "Opção A",
      "votos": 60,
      "percentual": 60.0
    },
    {
      "texto": "Opção B",
      "votos": 40,
      "percentual": 40.0
    }
  ],
  "opcaoVencedora": "Opção A"
}

🛡️ Validação do voto

Ao receber um voto, o serviço deve:

1. Buscar a enquete
2. Verificar se existe
3. Verificar se está ABERTA
4. Buscar o usuário
5. Verificar se já votou
6. Buscar a opção
7. Verificar se a opção pertence à enquete
8. Registrar o voto
9. Atualizar a quantidade de votos

Se alguma regra for violada, o voto não deve ser registrado.

🛠️ Tecnologias

Java 21+

Spring Boot

Spring Web

Spring Data JPA

Hibernate

PostgreSQL

Flyway

Bean Validation

WebSocket — evolução planejada

Maven

Lombok

Git/GitHub

IntelliJ IDEA

🗄️ Banco de dados

O projeto utiliza PostgreSQL.

Crie o banco:

CREATE DATABASE sistema_votacao;

Configure src/main/resources/application.properties:

spring.application.name=sistema-de-votacao

spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_votacao
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

⚠️ Nunca publique sua senha real no GitHub. Em projetos reais, utilize variáveis de ambiente ou outro mecanismo seguro.

▶️ Como executar

Pré-requisitos

JDK

Maven ou Maven Wrapper

PostgreSQL

IntelliJ IDEA ou outra IDE Java

Executar pelo IntelliJ

Execute:

SistemaDeVotacaoApplication

Executar pelo Maven

Linux/macOS:

./mvnw spring-boot:run

Windows:

mvnw.cmd spring-boot:run

A aplicação ficará disponível, por padrão, em:

http://localhost:8080

🧪 Testando a API

A API pode ser testada com:

Postman

Insomnia

curl

IntelliJ HTTP Client

Exemplo:

POST http://localhost:8080/usuarios
Content-Type: application/json

{
  "nome": "Maria",
  "email": "maria@email.com"
}

📊 Resultado e ranking

O resultado deve calcular:

percentual = (votosDaOpcao / totalDeVotos) × 100

A opção vencedora é aquela com maior quantidade de votos.

O ranking deve apresentar as opções em ordem decrescente de votos.

🚀 Evoluções planejadas

Atualização dos resultados em tempo real com WebSocket.

Dashboard com gráficos e filtros.

Autenticação e perfis de usuários.

JWT e autorização.

Encerramento automático por data.

Testes unitários e de integração.

Melhor tratamento de exceções e respostas HTTP.

🎓 Objetivo de aprendizado

O projeto foi desenvolvido para praticar:

Java e orientação a objetos;

APIs REST;

Spring Boot;

arquitetura em camadas;

DTOs;

Services;

Repositories;

JPA/Hibernate;

relacionamentos entre entidades;

regras de negócio;

PostgreSQL;

Git e GitHub.

👨‍💻 Autor

Vitório Santos

Projeto desenvolvido para estudo e prática de desenvolvimento de APIs com Java e Spring Boot.

📄 Licença

Projeto destinado principalmente a estudo e aprendizado.
