# Cadastro de Clientes com Java e MySQL
 Este projeto é um CRUD (Create, Read, Update, Delete) simples desenvolvido em Java, utilizando JDBC para integração com um banco de dados MySQL. O sistema permite gerenciar cadastros de clientes, incluindo cálculo automático do IMC (Índice de Massa Corporal).

## Funcionalidades
  Criar cadastro de cliente
  Visualizar todos os cadastros
  Atualizar dados de um cliente
  Deletar cadastro pelo ID
  Cálculo automático do IMC com base na altura e peso

## Tecnologias Utilizadas
  Java (JDK 8+)
  JDBC (Java Database Connectivity)
  MySQL
  Scanner (entrada via terminal)

## Estrutura da Tabela MySQL
  Antes de executar o projeto, certifique-se de que o banco de dados cadastro e a tabela cadastro estejam criados:
```MySQL
  CREATE DATABASE cadastro;
  USE cadastro;
  CREATE TABLE cadastro (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  idade INT,
  altura FLOAT,
  peso FLOAT,
  IMC FLOAT
  );
```

## Configuração
  Clone o repositório:
  ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
 ```

    Configure os dados de acesso ao banco de dados no arquivo Main.java:
 ```java
  private static final String URL = "jdbc:mysql://localhost:3306/cadastro";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "sua_senha";
  ```

  Compile e execute o projeto:
    javac Main.java
    java application.Main

## Exemplo de Uso
  Selecione uma opcao:
    1- Criar cadastro de cliente
    2- Visualizar cadastro de cliente
    3- Atualizar cadastro de cliente
    4- Deletar cadastro de cliente
    5- Sair do cadastro de cliente

O projeto roda via terminal e não possui interface gráfica.
O cálculo do IMC é feito automaticamente após o cadastro ou atualização.
Certifique-se de que o MySQL esteja rodando localmente e que o usuário tenha permissões adequadas.
