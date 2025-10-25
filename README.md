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


# 🧩 Migrando o Projeto para Spring Boot

Este projeto é uma API RESTful desenvolvida com **Spring Boot**, com o objetivo de realizar operações CRUD (Create, Read, Update, Delete) em uma entidade `Usuário`. Utiliza banco de dados em memória **H2**, **Spring Data JPA** para persistência, **Spring Web** para exposição dos endpoints e **Lombok** para reduzir a verbosidade do código.

## 🚀 Tecnologias e Dependências

- Java 21+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok

## 📦 Dependências no `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

🏗️ Arquitetura da Aplicação
Este projeto segue a arquitetura em camadas, que promove a separação de responsabilidades e facilita a manutenção e escalabilidade da aplicação.

📚 Camadas
Controller: Responsável por receber as requisições HTTP e encaminhá-las para a camada de negócio.
Business (Service): Contém a lógica de negócio e orquestra as operações entre controller e repository.
Infrastructure:
    Entity: Define os modelos de dados que representam as tabelas no banco de dados. Aqui criamos nossa Ent
    Repository: Interfaces que estendem JpaRepository para realizar operações de persistência.

Estrutura da Entidade Usuario
```java
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cadastro")
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nome")
	private String nome; 
	
	@Column(name = "altura")
	private float altura;
	
	@Column(name = "peso")
	private float peso;
	
	@Column(name = "imc")
	private float imc = peso / (altura * altura);
}
``` 

Estrutura Repository
```java
  public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
  }
```
Escolhemos trabalhar com essa interface, pois ela sera o nosso facilitador na construção dos metodos, passamos no parametro nome da entidade e o tipo de ID.

Estrutura Service
```java
	@Service
	public class ClienteService() {
		private final ClienteRepository repository;

		public ClienteService(ClienteRepository repository) {
			this.repository = repository;
		}

		public void salvarCliente(Cliente cliente) {
			repository.saveAndFlush(cliente);
		}
	}
```
Trabalhamos com a injeção de dependencia, porém poríamos ter utilizados outras duas maneiras:
1- Usando a anotação @Autowired do Spring;
2- Usando a anotação @RequiredArgsConstructor do Lombok;
