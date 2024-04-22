package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TreinoExerciciosDAO {
    private Connection conn;

    public TreinoExerciciosDAO(Connection conn) {
        this.conn = conn;
    }

    // Add an exercise to a training session
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

    // Update details of an exercise in a training session
    public void atualizarExercicioTreino(int treinoId, int exercicioId, int numeroSeries, int repMin, int repMax, double cargaKgs, int tempoDescanso) throws SQLException {
        String sql = "UPDATE treino_exercicios SET numero_series = ?, rep_min = ?, rep_max = ?, carga_kgs = ?, tempo_descanso_min = ? WHERE treino_id = ? AND exercicio_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroSeries);
            stmt.setInt(2, repMin);
            stmt.setInt(3, repMax);
            stmt.setDouble(4, cargaKgs);
            stmt.setInt(5, tempoDescanso);
            stmt.setInt(6, treinoId);
            stmt.setInt(7, exercicioId);
            stmt.executeUpdate();
        }
    }

    // Remove an exercise from a training session
    public void removerExercicioDeTreino(int treinoId, int exercicioId) throws SQLException {
        String sql = "DELETE FROM treino_exercicios WHERE treino_id = ? AND exercicio_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, treinoId);
            stmt.setInt(2, exercicioId);
            stmt.executeUpdate();
        }
    }
}