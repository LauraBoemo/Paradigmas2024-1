package org.example.util;

import org.example.handler.AlunoConsoleHandler;
import org.example.handler.ExercicioConsoleHandler;
import org.example.handler.PlanoConsoleHandler;
import org.example.handler.TreinoConsoleHandler;

import java.sql.Connection;
import java.util.Scanner;

public class GestaoAcademiaConsole {
    private final Scanner scanner;
    private final AlunoConsoleHandler alunoHandler;
    private final PlanoConsoleHandler planoHandler;
    private final TreinoConsoleHandler treinoHandler;
    private final ExercicioConsoleHandler exercicioHandler;  // Novo manipulador de exercícios

    public GestaoAcademiaConsole(Connection conn) {
        this.scanner = new Scanner(System.in);
        this.alunoHandler = new AlunoConsoleHandler(conn, scanner);
        this.planoHandler = new PlanoConsoleHandler(conn, scanner);
        this.treinoHandler = new TreinoConsoleHandler(conn, scanner);
        this.exercicioHandler = new ExercicioConsoleHandler(conn, scanner);  // Inicializar o manipulador de exercícios
    }

    public void start() {
        String opcao;
        do {
            showMenu();
            opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1":
                        alunoHandler.handle();
                        break;
                    case "2":
                        planoHandler.handle();
                        break;
                    case "3":
                        treinoHandler.handle();
                        break;
                    case "4":
                        exercicioHandler.handle();  // Adicionar o manipulador de exercícios ao menu
                        break;
                    case "0":
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!opcao.equals("0"));
        scanner.close(); // Ensure scanner is closed on exit
    }

    private void showMenu() {
        System.out.println("\nEscolha o módulo para gerenciar:");
        System.out.println("1 - Gerenciar Alunos");
        System.out.println("2 - Gerenciar Planos");
        System.out.println("3 - Gerenciar Treinos");
        System.out.println("4 - Gerenciar Exercícios");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }
}
