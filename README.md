# 🌱 Agrix - Sistema de Gestão e Monitoramento de Fazendas

## 📜 Descrição do Projeto

O **Agrix** é um sistema desenvolvido para a **AgroTech**, uma empresa focada em tecnologias que melhoram a eficiência no cultivo de plantações, reduzindo desperdícios e promovendo um uso responsável da terra. Este projeto foi desenvolvido em **fases**, permitindo a implementação gradual de funcionalidades.

## 🛠️ Tecnologias Utilizadas

- **Spring Boot**: Para o desenvolvimento da aplicação.
- **Spring Data JPA**: Para a persistência de dados.
- **Spring Security**: Para a implementação de autenticação e autorização.
- **Docker**: Para a criação de contêineres.
- **MySQL/PostgreSQL e H2**: Para o gerenciamento de banco de dados.
  
## 🚀 Fases do Projeto

### Fase A - Funcionalidades Iniciais

- Criação de uma API para gerenciamento de fazendas.
- Implementação das rotas:
  - `POST /farms`
  - `GET /farms`
  - `GET /farms/{id}`
  - `POST /farms/{farmId}/crops`
  - `GET /farms/{farmId}/crops`
  - `GET /crops`
  - `GET /crops/{id}`
- Configuração de um **Dockerfile** para a aplicação.

### Fase B - Expansão da Aplicação

- Migração do código da Fase A.
- Implementação de novas rotas:
  - `GET /crops/search`
  - `POST /fertilizers`
  - `GET /fertilizers`
  - `GET /fertilizers/{id}`
  - `POST /crops/{cropId}/fertilizers/{fertilizerId}`
  - `GET /crops/{cropId}/fertilizers`
- Criação de testes com cobertura mínima de 80%.

### Fase C - Implementação de Segurança

- Integração do código para controle de pessoas.
- Implementação de autenticação com JWT.
- Limitação de acesso às rotas `GET /farms`, `GET /crops`, e `GET /fertilizers` para usuários autenticados com o papel correto.

## 📝 Habilidades Desenvolvidas

- Criação de rotas na API usando Spring.
- Injeção de dependência para conectar diferentes camadas da aplicação.
- Implementação de testes de integração e unitários.
- Uso do Spring Security para autenticação e autorização.

## 🗄️ Modelo de Banco de Dados

O banco de dados é composto pelas seguintes tabelas:

- **farm**: Representa uma fazenda.
- **crop**: Representa uma plantação, relacionada a uma fazenda.
- **fertilizer**: Representa um fertilizante, com relação muitos-para-muitos com a tabela de plantações.

## ⚙️ Arquitetura da Aplicação

A aplicação foi estruturada seguindo boas práticas de organização e separação de pacotes. A arquitetura escolhida permite fácil manutenção e expansão no futuro.
