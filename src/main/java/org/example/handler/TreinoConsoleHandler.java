package org.example.handler;

import org.example.dao.TreinoDAO;
import org.example.dao.TreinoExerciciosDAO;

import java.sql.Connection;
import java.util.Scanner;
import java.time.LocalDate;

public class TreinoConsoleHandler implements ConsoleHandler {
    private final Scanner scanner;
    private final TreinoDAO treinoDAO;

    public TreinoConsoleHandler(Connection conn, Scanner scanner) {
        this.scanner = scanner;
        this.treinoDAO = new TreinoDAO(conn);
    }

    @Override
    public void handle() {
        String opcao;
        do {
            System.out.println("\n-- Gerenciamento de Treinos --");
            System.out.println("1 - Criar Novo Treino");
            System.out.println("2 - Adicionar Exercício a Treino");
            System.out.println("3 - Remover Exercício de Treino");
            System.out.println("4 - Listar Treinos");
            System.out.println("5 - Excluir Treino");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1":
                        criarTreino();
                        break;
                    case "2":
                        adicionarExercicioATreino();
                        break;
                    case "3":
                        removerExercicioDeTreino();
                        break;
                    case "4":
                        listarTreinos();
                        break;
                    case "5":
                        excluirTreino();
                        break;
                    case "0":
                        System.out.println("Retornando ao menu principal...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!opcao.equals("0"));
    }

    private void criarTreino() throws Exception {
        System.out.println("Digite a data de início do treino (formato AAAA-MM-DD):");
        String dataStr = scanner.nextLine();
        LocalDate dataInicio = LocalDate.parse(dataStr);

        int treinoId = treinoDAO.criarTreino(dataInicio);
        System.out.println("Treino criado com sucesso! ID do Treino: " + treinoId);
    }

    private void adicionarExercicioATreino() throws Exception {
        System.out.println("Digite o ID do Treino:");
        int treinoId = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o ID do Exercício:");
        int exercicioId = Integer.parseInt(scanner.nextLine());
        System.out.println("Número de séries:");
        int series = Integer.parseInt(scanner.nextLine());
        System.out.println("Repetições mínimas:");
        int repMin = Integer.parseInt(scanner.nextLine());
        System.out.println("Repetições máximas:");
        int repMax = Integer.parseInt(scanner.nextLine());
        System.out.println("Carga em kgs:");
        double carga = Double.parseDouble(scanner.nextLine());
        System.out.println("Tempo de descanso em minutos:");
        int descanso = Integer.parseInt(scanner.nextLine());

        treinoDAO.adicionarExercicioAoTreino(treinoId, exercicioId, series, repMin, repMax, carga, descanso);
        System.out.println("Exercício adicionado ao treino com sucesso!");
    }

    private void removerExercicioDeTreino() throws Exception {
        System.out.println("Digite o ID do Treino:");
        int treinoId = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o ID do Exercício:");
        int exercicioId = Integer.parseInt(scanner.nextLine());

        treinoDAO.removerExercicioDeTreino(treinoId, exercicioId);
        System.out.println("Exercício removido do treino com sucesso!");
    }

    private void listarTreinos() throws Exception {
        System.out.println("Listando todos os treinos:");
        for (Integer treinoId : treinoDAO.listarTreinos()) {
            System.out.println("ID do Treino: " + treinoId);
        }
    }

    private void excluirTreino() throws Exception {
        System.out.println("Digite o ID do Treino que deseja excluir:");
        int treinoId = Integer.parseInt(scanner.nextLine());
        treinoDAO.excluirTreino(treinoId);
        System.out.println("Treino excluído com sucesso!");
    }
}
