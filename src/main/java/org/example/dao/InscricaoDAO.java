package org.example.dao;

import org.example.model.Inscricao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscricaoDAO {
    private Connection conn;

    public InscricaoDAO(Connection conn) {
        this.conn = conn;
    }
    public void adicionarInscricao(Inscricao inscricao) throws SQLException {
        String sql = "INSERT INTO inscricoes (aluno_cpf, plano_id, data_inicio, numero_cartao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, inscricao.getAlunoCPF());
            stmt.setInt(2, inscricao.getPlanoId());
            stmt.setDate(3, java.sql.Date.valueOf(inscricao.getDataInicio()));
            stmt.setString(4, inscricao.getNumeroCartao());
            stmt.executeUpdate();
        }
    }
}

