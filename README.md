# 🏠 API de Gestão de Aluguel de Imóveis

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de **imóveis, inquilinos e contratos de aluguel**.

A aplicação permite cadastrar imóveis e inquilinos, além de registrar contratos de aluguel garantindo que apenas **imóveis disponíveis possam ser alugados**, aplicando validações de regras de negócio no backend.

Este projeto demonstra a construção de uma **API RESTful utilizando Spring Boot, JPA e arquitetura em camadas**, com foco em boas práticas de desenvolvimento backend.

---

# Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Maven
* Banco de dados relacional

---

# Arquitetura do Projeto

A aplicação segue uma estrutura comum em projetos backend com **Spring Boot**, separando responsabilidades em camadas.

```
src/main/java
├── controllers
│   ├── AluguelController
│   ├── ImovelController
│   └── InquilinoController
│
├── models
│   ├── Aluguel
│   ├── Imovel
│   ├── Casa
│   ├── Apartamento
│   └── Inquilino
│
├── repository
│   ├── AluguelRepository
│   ├── ImovelRepository
│   └── InquilinoRepository
│
└── exceptions
    └── ResourceNotFoundException
```

---

# Modelo de Domínio

A API é baseada em três entidades principais:

## Imóvel

Representa um imóvel disponível para aluguel.

Tipos suportados:

* Casa
* Apartamento

Principais atributos:

* id
* endereço
* disponibilidade

---

## Inquilino

Representa a pessoa responsável pelo contrato de aluguel.

Principais atributos:

* id
* nome
* dados de contato

---

## Aluguel

Representa o contrato entre **inquilino** e **imóvel**.

Principais atributos:

* id
* dataInicio
* dataFim
* imóvel
* inquilino

---

# Regras de Negócio Implementadas

A API implementa algumas validações importantes:

* Um **imóvel só pode ser alugado se estiver disponível**
* Um **aluguel deve possuir um inquilino válido**
* Um **aluguel deve possuir um imóvel válido**
* Ao registrar um aluguel, o **imóvel passa automaticamente para indisponível**

Essas validações garantem a consistência das operações realizadas pela API.

---

# Principais Endpoints

## Imóveis

Listar imóveis

```
GET /api/imoveis
```

Buscar imóvel por ID

```
GET /api/imoveis/{id}
```

Cadastrar imóvel

```
POST /api/imoveis
```

---

## Inquilinos

Listar inquilinos

```
GET /api/inquilinos
```

Buscar inquilino por ID

```
GET /api/inquilinos/{id}
```

Cadastrar inquilino

```
POST /api/inquilinos
```

---

## Aluguéis

Registrar novo aluguel

```
POST /api/aluguel
```

Fluxo da operação:

1. Verifica se o **inquilino existe**
2. Verifica se o **imóvel existe**
3. Verifica se o **imóvel está disponível**
4. Registra o contrato de aluguel
5. Atualiza o imóvel para **indisponível**

---

# ▶️ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/stephanievitoria/api-aluguel-imoveis.git
```

### 2️⃣ Entrar na pasta do projeto

```bash
cd api-aluguel-imoveis
```

### 3️⃣ Executar a aplicação

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```
http://localhost:8080
```

---

# 🎓 Contexto Acadêmico

Este projeto foi desenvolvido durante o **3º semestre do curso de Sistemas de Informação**, na disciplina de **Programação de Sistemas II**.

O objetivo da atividade foi aplicar conceitos de desenvolvimento backend utilizando **Java, Spring Boot, APIs REST e persistência de dados com JPA**, além de praticar modelagem de entidades e organização de projetos em camadas.

---

# 👩‍💻 Autora

**Stephanie Vitoria Soares da Cruz**

Estudante de **Sistemas de Informação** com foco em desenvolvimento backend.
