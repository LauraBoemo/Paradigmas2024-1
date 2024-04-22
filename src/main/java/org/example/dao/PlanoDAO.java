package org.example.dao;

import org.example.model.Plano;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {
    private Connection conn;

    public PlanoDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarPlano(Plano plano) throws SQLException {
        String sql = "INSERT INTO planos (codigo, nome, valor_mensal) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, plano.getCodigo());
            stmt.setString(2, plano.getNome());
            stmt.setBigDecimal(3, plano.getValorMensal());
            stmt.executeUpdate();
        }
    }

    public void atualizarPlano(Plano plano) throws SQLException {
        String sql = "UPDATE planos SET nome = ?, valor_mensal = ? WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setBigDecimal(2, plano.getValorMensal());
            stmt.setInt(3, plano.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void excluirPlano(int codigo) throws SQLException {
        String sql = "DELETE FROM planos WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }

    public Plano buscarPlanoPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM planos WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    BigDecimal valorMensal = rs.getBigDecimal("valor_mensal");
                    return new Plano(codigo, nome, valorMensal);
                }
            }
        }
        return null;
    }

    public List<Plano> listarPlanos() throws SQLException {
        List<Plano> planos = new ArrayList<>();
        String sql = "SELECT codigo, nome, valor_mensal FROM planos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                BigDecimal valorMensal = rs.getBigDecimal("valor_mensal");
                planos.add(new Plano(codigo, nome, valorMensal));
            }
        }
        return planos;
    }
}
