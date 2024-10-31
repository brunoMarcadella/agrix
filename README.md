# 🌱 Agrix - Sistema de Gestão e Monitoramento de Fazendas

## 📜 Descrição do Projeto

O Agrix é um sistema desenvolvido para a AgroTech, uma empresa focada em tecnologias que melhoram a eficiência no cultivo de plantações, reduzindo desperdícios e promovendo um uso responsável da terra. Este projeto foi desenvolvido em fases, permitindo a implementação gradual de funcionalidades.

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
  - `POST /farms`: Cadastra uma nova fazenda, exigindo `name` (string) e `size` (inteiro) na requisição.
  - `GET /farms`: Retorna todas as fazendas cadastradas.
  - `GET /farms/{id}`: Retorna a fazenda correspondente ao ID. Caso não seja encontrada, retorna "Fazenda não encontrada!" com status HTTP 404.
  - `POST /farms/{farmId}/crops`: Cria uma plantação em uma fazenda específica, exigindo `name` (string), `plantedArea` (inteiro), `plantedDate` e `harvestDate` (formato "YYYY-MM-DD"). Retorna "Fazenda não encontrada!" caso o ID da fazenda não exista.
  - `GET /farms/{farmId}/crops`: Retorna todas as plantações da fazenda correspondente ao ID. Caso a fazenda não exista, retorna "Fazenda não encontrada!".
  - `GET /crops`: Retorna todas as plantações cadastradas.
  - `GET /crops/{id}`: Retorna a plantação com o ID correspondente ou "Plantação não encontrada!" com status HTTP 404.

- Configuração de um Dockerfile para a aplicação.

### Fase B - Expansão da Aplicação
- Migração do código da Fase A.
- Implementação de novas rotas:
  - `GET /crops/search`: Permite busca de plantações pela data de colheita com parâmetros "start" e "end" via query string, retornando plantações dentro do intervalo.
  - `POST /fertilizers`: Cria um novo fertilizante, exigindo `name` (string), `brand` (string) e `composition` (string) na requisição.
  - `GET /fertilizers`: Retorna uma lista com todos os fertilizantes cadastrados.
  - `GET /fertilizers/{id}`: Retorna o fertilizante correspondente ao ID, ou "Fertilizante não encontrado!" com status HTTP 404.
  - `POST /crops/{cropId}/fertilizers/{fertilizerId}`: Associa uma plantação ao fertilizante. Retorna "Fertilizante e plantação associados com sucesso!" com status HTTP 201 ou, em caso de erro, status HTTP 404 com mensagens "Plantação não encontrada!" ou "Fertilizante não encontrado!".
  - `GET /crops/{cropId}/fertilizers`: Retorna todos os fertilizantes associados à plantação com ID correspondente, ou "Plantação não encontrada!" com status HTTP 404.

- Criação de testes com cobertura mínima de 80%.

### Fase C - Implementação de Segurança
- Integração do código para controle de pessoas.
- Implementação de autenticação com JWT.
- Limitação de acesso às rotas com base em permissões de usuário.

## 🔑 Acesso e Autenticação

Para acessar a aplicação, siga as instruções:

1. **Criação de Conta**: Envie uma requisição contendo `username`, `password`, e `role` para criar uma conta. Os papéis disponíveis são:
   - `USER`: Acesso básico.
   - `MANAGER`: Acesso a funcionalidades de gerenciamento de plantações.
   - `ADMIN`: Acesso completo à aplicação, incluindo gerenciamento de fertilizantes.

2. **Autenticação**:
   - Faça login na rota `/auth/login` enviando `username` e `password`.
   - Receberá um token JWT para autenticação.
   - Use esse token no header `Authorization` em todas as requisições subsequentes.

3. **Autorização**:
   - `GET /farms`: Acessível por todos os papéis.
   - `GET /crops`: Acessível apenas por `MANAGER` e `ADMIN`.
   - `GET /fertilizers`: Acessível apenas por `ADMIN`.

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