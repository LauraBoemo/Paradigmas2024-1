package org.example;

import org.example.util.Conexao;
import org.example.util.GestaoAcademiaConsole;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Conexao.getConnection()) {
            GestaoAcademiaConsole console = new GestaoAcademiaConsole(conn);
            console.start();
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
