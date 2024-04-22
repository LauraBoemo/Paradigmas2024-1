package org.example.dao;

import org.example.model.Exercicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExercicioDAO {
    private Connection conn;

    public ExercicioDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarExercicio(Exercicio exercicio) throws SQLException {
        String sql = "INSERT INTO exercicios (numero, nome, musculos_ativados) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, exercicio.getNumero());
            stmt.setString(2, exercicio.getNome());
            stmt.setString(3, exercicio.getMusculosAtivados());
            stmt.executeUpdate();
        }
    }

    public List<Exercicio> listarExercicios() throws SQLException {
        List<Exercicio> exercicios = new ArrayList<>();
        String sql = "SELECT numero, nome, musculos_ativados FROM exercicios";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int numero = rs.getInt("numero");
                String nome = rs.getString("nome");
                String musculosAtivados = rs.getString("musculos_ativados");
                exercicios.add(new Exercicio(numero, nome, musculosAtivados));
            }
        }
        return exercicios;
    }
}
