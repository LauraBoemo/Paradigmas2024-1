package org.example.model;

import java.math.BigDecimal;

public class Plano {
    private int codigo;
    private String nome;
    private BigDecimal valorMensal;

    public Plano(int codigo, String nome, BigDecimal valorMensal) {
        this.codigo = codigo;
        this.nome = nome;
        this.valorMensal = valorMensal;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorMensal() {
        return valorMensal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValorMensal(BigDecimal valorMensal) {
        this.valorMensal = valorMensal;
    }

    @Override
    public String toString() {
        return "Plano{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", valorMensal=" + valorMensal +
                '}';
    }
}
