# Projeto de Automação de Testes de API

## 📌 Visão Geral do Projeto

Este projeto é um framework de automação de testes de API construído utilizando **Java**, **RestAssured** e **JUnit 5**. Ele foi projetado para validar a funcionalidade de uma API que gerencia produtos eletrônicos. A automação cobre autenticação, recuperação de produtos e validação de respostas da API.

## 🚀 Como Executar os Testes

### Pré-requisitos

Certifique-se de ter os seguintes requisitos instalados:

- **Java 17**
- **Maven**
- **Git**

### Passos para Execução

1. Clone o repositório:
   ```sh
   git clone <repository-url>
   cd api-test-project
   ```
2. Execute os testes com Maven:
   ```sh
   mvn test
   ```
3. Gere os relatórios Allure (se habilitado):
   ```sh
   mvn allure:report
   ```
4. Visualize o relatório Allure no navegador:
   ```sh
   mvn allure:serve
   ```

## 📝 Plano de Testes

### Escopo

O plano de testes inclui:

- Testes de autenticação (validação de login)
- Testes de recuperação de produtos (obter todos os produtos, obter produto por ID)
- Testes de disponibilidade da API
- Validação de endpoints protegidos por autorização

### Cenários de Teste

| Caso de Teste               | Descrição                                            | Resultado Esperado                      |
| --------------------------- | ---------------------------------------------------- | --------------------------------------- |
| Verificação de Saúde da API | Verificar se a API está operacional                  | Retorna 200 OK                          |
| Autenticação de Usuário     | Validar login com credenciais corretas               | Retorna token de acesso                 |
| Recuperar Todos os Produtos | Buscar todos os produtos da API                      | Retorna uma lista de produtos           |
| Obter Produto por ID        | Buscar um produto específico por ID                  | Retorna os detalhes corretos do produto |
| Acesso Não Autorizado       | Tentar acessar endpoints protegidos sem autenticação | Retorna 400                             |
| Produto não existente       | Tentar acessar endpoint de produto com id invalido   | Retorna 400                             |

## ✅ Testes Realizados

Os seguintes casos de teste foram implementados e executados:

- **Verificação de Saúde da API** (validação do endpoint `/test`)
- **Teste de Login** (garantindo que a autenticação funciona corretamente)
- **Busca de Todos os Produtos** (validação do endpoint `/products`)
- **Busca de Produto Específico por ID** (utilizando ID dinâmico)
- **Teste de Adição de Produto** (verificando a criação de produto via API)
- **Validação de Autorização** (garantindo que endpoints protegidos requerem autenticação)

### Usuários
- **Buscar todos os usuários** (`testGetUsers`)
- **Login com um usuário da API** (`testLogin`)
- **Tentar login com usuário inexistente** (`testNonExistentUserLogin`)

### Produtos
- **Buscar todos os produtos e armazenar um ID** (`allProducts`)
- **Buscar um produto específico pelo ID** (`testProductWithId`)
- **Adicionar um produto** (`testAddProduct`)
- **Buscar produtos autenticado** (`testGetAuthProducts`)
- **Tentar buscar um produto com token inválido** (`testGetProductWithInvalidToken`)
- **Tentar buscar um produto inexistente** (`testGetNonExistentProduct`)

### Resumo da Execução dos Testes

- Todos os testes foram executados com sucesso ✅
- Relatórios Allure disponíveis em `target/site/allure-maven-plugin/`

---


