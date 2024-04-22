package org.example.handler;

import org.example.dao.AlunoDAO;
import org.example.dao.TreinoDAO;
import org.example.model.Aluno;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AlunoConsoleHandler implements ConsoleHandler {
    private final Scanner scanner;
    private final AlunoDAO alunoDAO;
    private final TreinoDAO treinoDAO;
    private final DateTimeFormatter dateFormatter;

    public AlunoConsoleHandler(Connection conn, Scanner scanner) {
        this.scanner = scanner;
        this.alunoDAO = new AlunoDAO(conn);
        this.treinoDAO = new TreinoDAO(conn);
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Define the date format
    }

    @Override
    public void handle() {
        String opcao;
        do {
            System.out.println("\n-- Gerenciamento de Alunos --");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("3 - Buscar Aluno por CPF");
            System.out.println("4 - Atualizar Aluno");
            System.out.println("5 - Deletar Aluno");
            System.out.println("6 - Associar Treino a Aluno");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1":
                        cadastrarAluno();
                        break;
                    case "2":
                        listarAlunos();
                        break;
                    case "3":
                        buscarAlunoPorCPF();
                        break;
                    case "4":
                        atualizarAluno();
                        break;
                    case "5":
                        excluirAluno();
                        break;
                    case "6":
                        associarTreinoAAluno();
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Erro de formato de data, por favor use o formato DD-MM-AAAA.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarAluno() throws SQLException {
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o nome do aluno:");
        String nome = scanner.nextLine();
        System.out.println("Digite a data de nascimento (formato DD-MM-AAAA):");
        String data = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(data, dateFormatter);

        Aluno novoAluno = new Aluno(cpf, nome, dataNascimento);
        alunoDAO.adicionarAluno(novoAluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void listarAlunos() throws SQLException {
        System.out.println("Lista de alunos cadastrados:");
        alunoDAO.listarAlunos().forEach(System.out::println);
    }

    private void buscarAlunoPorCPF() throws SQLException {
        System.out.println("Digite o CPF do aluno para busca:");
        String cpf = scanner.nextLine();
        Aluno aluno = alunoDAO.buscarPorCPF(cpf);
        if (aluno != null) {
            System.out.println(aluno);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private void atualizarAluno() throws SQLException {
        System.out.println("Digite o CPF do aluno para atualizar:");
        String cpf = scanner.nextLine();
        Aluno aluno = alunoDAO.buscarPorCPF(cpf);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }
        System.out.println("Digite o novo nome do aluno:");
        aluno.setNome(scanner.nextLine());
        System.out.println("Digite a nova data de nascimento (formato DD-MM-AAAA):");
        aluno.setDataNascimento(LocalDate.parse(scanner.nextLine(), dateFormatter));
        alunoDAO.atualizarAluno(aluno);
        System.out.println("Aluno atualizado com sucesso!");
    }

    private void excluirAluno() throws SQLException {
        System.out.println("Digite o CPF do aluno para excluir:");
        String cpf = scanner.nextLine();
        alunoDAO.excluirAluno(cpf);
        System.out.println("Aluno excluído com sucesso.");
    }

    private void associarTreinoAAluno() throws SQLException {
        System.out.println("Digite o CPF do aluno:");
        String cpfAluno = scanner.nextLine();
        Aluno aluno = alunoDAO.buscarPorCPF(cpfAluno);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        List<Integer> treinosDisponiveis = treinoDAO.listarTreinos();
        if (treinosDisponiveis.isEmpty()) {
            System.out.println("Não existem treinos disponíveis para associação.");
            return;
        }

        System.out.println("Treinos disponíveis:");
        for (int treinoId : treinosDisponiveis) {
            System.out.println("ID do Treino: " + treinoId);
        }

        System.out.println("Digite o ID do treino para associar ao aluno:");
        int treinoId = Integer.parseInt(scanner.nextLine());

        if (!treinosDisponiveis.contains(treinoId)) {
            System.out.println("ID de treino inválido ou não existente.");
            return;
        }

        alunoDAO.associarTreinoAAluno(cpfAluno, treinoId);
        System.out.println("Treino associado ao aluno com sucesso.");
    }
}
