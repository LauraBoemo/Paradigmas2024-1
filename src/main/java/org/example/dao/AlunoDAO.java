package org.example.dao;

import org.example.model.Aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection conn;

    public AlunoDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO alunos (cpf, nome, data_nascimento) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.executeUpdate();
        }
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        String sql = "UPDATE alunos SET nome = ?, data_nascimento = ? WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(3, aluno.getCpf());
            stmt.executeUpdate();
        }
    }

    public void excluirAluno(String cpf) throws SQLException {
        String sql = "DELETE FROM alunos WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    public List<Aluno> listarAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                alunos.add(new Aluno(cpf, nome, dataNascimento));
            }
        }
        return alunos;
    }

    public Aluno buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM alunos WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                    return new Aluno(cpf, nome, dataNascimento);
                }
            }
        }
        return null;
    }

    public void associarTreinoAAluno(String cpfAluno, int treinoId) throws SQLException {
        String sql = "INSERT INTO alunos_treinos (cpf, treino_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfAluno);
            stmt.setInt(2, treinoId);
            stmt.executeUpdate();
        }
    }
}
