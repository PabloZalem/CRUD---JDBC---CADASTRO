package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	private static final String URL = "jdbc:mysql://localhost:3306/cadastro";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Pablo1764@";
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		while (true) {
			System.out.println("Selecione uma opcao: ");
			System.out.println("1- Criar cadastro de cliente: ");
			System.out.println("2- Visualizar cadastro de cliente: ");
			System.out.println("3- Atualizar cadastro de cliente: ");
			System.out.println("4- Deletar cadastro de cliente: ");
			System.out.println("5- Sair do cadastro de cliente: ");
			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				criarCadastro();
				break;
			case 2:
				visualizarCadastro();
				break;
			case 3:
				atualizarCadastro();
				break;
			case 4:
				deletarCadastro();
				break;
			case 5:
				System.out.println("Saindo...");
				System.exit(0);
				break;
			default:
				System.out.println("Opcao invalida, selecione uma opcao valida");
				break;
			}
		}
	}

	private static void deletarCadastro() throws SQLException {
		System.out.println("Me informe o id que é para ser deletado: ");
		int id = scanner.nextInt();
		
		// Query
		String sql = "DELETE FROM cadastro WHERE id=?";
		
		// Conexao
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		
		// Execucao
		int rows = preparedStatement.executeUpdate();
		
		if (rows > 0) {
			System.out.println("Cadastro deletado com sucesso");
		} else {
			System.out.println("Nao foi possivel deletar o cadastro");
		}
	}

	private static void atualizarCadastro() throws SQLException {
		// Entrada
		System.out.println("Selecione o ID: ");
		int id = scanner.nextInt();
		
		System.out.println("Informe o nome que queira atualizar: ");
		String nome = scanner.next();
		
		System.out.println("Informe a idade: ");
		int idade = scanner.nextInt();
		
		System.out.println("Informe a altura em metros: ");
		float altura = scanner.nextFloat();
		
		System.out.println("Informe o peso em kg: ");
		float peso = scanner.nextFloat();
		
		float imc = peso / (altura * altura);
		System.out.printf("Seu novo IMC: %.2f", imc);
		System.out.println();
		
		// Query
		String sql = "UPDATE cadastro SET nome=?, idade=?, altura=?, peso=?, IMC=? WHERE id=?";
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nome);
		preparedStatement.setInt(2, idade);
		preparedStatement.setFloat(3, altura);
		preparedStatement.setFloat(4, peso);
		preparedStatement.setFloat(5, imc);
		preparedStatement.setInt(6, id);
		
		// Executar
		int rows = preparedStatement.executeUpdate();
		
		if (rows > 0) {
			System.out.println("Atualizacao realizada com sucesso");
		} else {
			System.out.println("Nao foi possivel realizar atualizacao com sucesso");
		}
		preparedStatement.close();
		connection.close();
		System.out.println();
	}

	private static void visualizarCadastro() throws SQLException {
		//Query
		String sql = "SELECT * FROM cadastro";
		
		// Conexao
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Statement statement = connection.createStatement();
		
		// Execucao
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("id") + " " + resultSet.getString("Nome") + " " + resultSet.getInt("idade") + " " + resultSet.getFloat("altura") + " " + resultSet.getString("peso") + " " + resultSet.getString("IMC"));
		}
		statement.close();
		connection.close();
		System.out.println();
	}

	private static void criarCadastro() throws SQLException {
		// Criar usuario
		System.out.print("Insira o nome do cadastrado: ");
		String nome = scanner.next();
		
		System.out.print("Insira a idade do cadastrado: ");
		int idade = scanner.nextInt();
		
		System.out.print("Insira a altura do cadastrado em metros: ");
		float altura = scanner.nextFloat();
		
		System.out.print("Insira o peso do cadastrado em kg: ");
		float peso = scanner.nextFloat();
		
		float imc = peso / (altura * altura);
		System.out.printf("O seu IMC é: %.2f", imc);
		System.out.println();
		
		// QUERY
		String sql = "INSERT INTO cadastro (nome, idade, altura, peso, IMC) "
				+ "VALUES (?,?,?,?,?)";
		
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nome);
		preparedStatement.setInt(2, idade);
		preparedStatement.setFloat(3, altura);
		preparedStatement.setFloat(4, peso);
		preparedStatement.setFloat(5, imc);
		
		// EXECUTAR
		int rows = preparedStatement.executeUpdate();
		
		if (rows > 0) {
			System.out.println("Cadastro realizado com sucesso");
		} else {
			System.out.println("Nao foi possivel cadastrar com sucesso.");
		}
	}
}
