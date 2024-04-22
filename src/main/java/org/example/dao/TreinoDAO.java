package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TreinoDAO {
    private Connection conn;

    public TreinoDAO(Connection conn) {
        this.conn = conn;
    }


    public int criarTreino(LocalDate dataInicio) throws SQLException {
        String sql = "INSERT INTO treinos (data_inicio) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(dataInicio)); // Converte LocalDate para java.sql.Date
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // Retorna o ID do novo treino criado
            }
            throw new SQLException("Falha ao criar treino.");
        }
    }

    public void adicionarExercicioAoTreino(int treinoId, int exercicioId, int numeroSeries, int repMin, int repMax, double cargaKgs, int tempoDescanso) throws SQLException {
        String sql = "INSERT INTO treino_exercicios (treino_id, exercicio_id, numero_series, rep_min, rep_max, carga_kgs, tempo_descanso_min) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, treinoId);
            stmt.setInt(2, exercicioId);
            stmt.setInt(3, numeroSeries);
            stmt.setInt(4, repMin);
            stmt.setInt(5, repMax);
            stmt.setDouble(6, cargaKgs);
            stmt.setInt(7, tempoDescanso);
            stmt.executeUpdate();
        }
    }

    public void removerExercicioDeTreino(int treinoId, int exercicioId) throws SQLException {
        String sql = "DELETE FROM treino_exercicios WHERE treino_id = ? AND exercicio_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, treinoId);
            stmt.setInt(2, exercicioId);
            stmt.executeUpdate();
        }
    }

    public void excluirTreino(int treinoId) throws SQLException {
        String sql = "DELETE FROM treino_exercicios WHERE treino_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, treinoId);
            stmt.executeUpdate();
        }

        sql = "DELETE FROM treinos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, treinoId);
            stmt.executeUpdate();
        }
    }

    public List<Integer> listarTreinos() throws SQLException {
        List<Integer> treinoIds = new ArrayList<>();
        String sql = "SELECT id FROM treinos";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                treinoIds.add(rs.getInt("id"));
            }
        }
        return treinoIds;
    }

}
