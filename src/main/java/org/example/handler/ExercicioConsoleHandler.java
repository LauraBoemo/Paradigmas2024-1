package org.example.handler;

import org.example.dao.ExercicioDAO;
import org.example.model.Exercicio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ExercicioConsoleHandler implements ConsoleHandler {
    private final Scanner scanner;
    private final ExercicioDAO exercicioDAO;

    public ExercicioConsoleHandler(Connection conn, Scanner scanner) {
        this.scanner = scanner;
        this.exercicioDAO = new ExercicioDAO(conn);
    }

    @Override
    public void handle() {
        String opcao;
        do {
            System.out.println("\n-- Gerenciamento de Exercícios --");
            System.out.println("1 - Cadastrar Exercício");
            System.out.println("2 - Listar Exercícios");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1":
                        cadastrarExercicio();
                        break;
                    case "2":
                        listarExercicios();
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarExercicio() throws SQLException {
        System.out.println("Digite o número do exercício:");
        int numero = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o nome do exercício:");
        String nome = scanner.nextLine();
        System.out.println("Digite os músculos ativados:");
        String musculosAtivados = scanner.nextLine();

        Exercicio exercicio = new Exercicio(numero, nome, musculosAtivados);
        exercicioDAO.adicionarExercicio(exercicio);
        System.out.println("Exercício cadastrado com sucesso!");
    }

    private void listarExercicios() throws SQLException {
        System.out.println("Lista de exercícios cadastrados:");
        exercicioDAO.listarExercicios().forEach(System.out::println);
    }
}
