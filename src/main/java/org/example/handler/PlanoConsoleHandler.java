package org.example.handler;

import org.example.dao.InscricaoDAO;
import org.example.dao.PlanoDAO;
import org.example.model.Inscricao;
import org.example.model.Plano;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PlanoConsoleHandler implements ConsoleHandler {
    private final Scanner scanner;
    private final PlanoDAO planoDAO;
    private final Connection conn;

    public PlanoConsoleHandler(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
        this.planoDAO = new PlanoDAO(conn);
    }

    @Override
    public void handle() {
        String opcao;
        do {
            System.out.println("\n-- Gerenciamento de Planos --");
            System.out.println("1 - Cadastrar Plano");
            System.out.println("2 - Listar Planos");
            System.out.println("3 - Atualizar Plano");
            System.out.println("4 - Deletar Plano");
            System.out.println("5 - Inscrever Aluno em Plano");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1":
                        cadastrarPlano();
                        break;
                    case "2":
                        listarPlanos();
                        break;
                    case "3":
                        atualizarPlano();
                        break;
                    case "4":
                        excluirPlano();
                        break;
                    case "5":
                        inscreverAlunoEmPlano();
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
                System.out.println("Erro de entrada: " + e.getMessage());
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarPlano() throws SQLException {
        try {
            System.out.println("Digite o código do plano:");
            int codigo = Integer.parseInt(scanner.nextLine());
            System.out.println("Digite o nome do plano:");
            String nome = scanner.nextLine();
            System.out.println("Digite o valor mensal do plano (exemplo: 99.90):");
            BigDecimal valorMensal = new BigDecimal(scanner.nextLine());

            Plano novoPlano = new Plano(codigo, nome, valorMensal);
            planoDAO.adicionarPlano(novoPlano);
            System.out.println("Plano cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida para número.");
        }
    }

    private void listarPlanos() throws SQLException {
        System.out.println("Lista de planos cadastrados:");
        planoDAO.listarPlanos().forEach(System.out::println);
    }

    private void atualizarPlano() throws SQLException {
        try {
            System.out.println("Digite o código do plano para atualizar:");
            int codigo = Integer.parseInt(scanner.nextLine());
            Plano plano = planoDAO.buscarPlanoPorCodigo(codigo);
            if (plano == null) {
                System.out.println("Plano não encontrado.");
                return;
            }
            System.out.println("Digite o novo nome do plano:");
            plano.setNome(scanner.nextLine());
            System.out.println("Digite o novo valor mensal do plano (exemplo: 99.90):");
            plano.setValorMensal(new BigDecimal(scanner.nextLine()));
            planoDAO.atualizarPlano(plano);
            System.out.println("Plano atualizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida para número.");
        }
    }

    private void excluirPlano() throws SQLException {
        try {
            System.out.println("Digite o código do plano para excluir:");
            int codigo = Integer.parseInt(scanner.nextLine());
            planoDAO.excluirPlano(codigo);
            System.out.println("Plano excluído com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida para número.");
        }
    }

    private void inscreverAlunoEmPlano() throws SQLException {
        System.out.println("Digite o CPF do Aluno:");
        String alunoCPF = scanner.nextLine(); // Collecting CPF as a String
        System.out.println("Digite o ID do Plano:");
        int planoId = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite a Data de Início do Plano (formato: DD-MM-AAAA):");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.println("Digite o Número do Cartão de Crédito:");
        String numeroCartao = scanner.nextLine();

        Inscricao inscricao = new Inscricao(alunoCPF, planoId, dataInicio, numeroCartao);
        InscricaoDAO inscricaoDAO = new InscricaoDAO(conn);
        inscricaoDAO.adicionarInscricao(inscricao);
        System.out.println("Plano registrado com sucesso para o aluno!");
    }

}
