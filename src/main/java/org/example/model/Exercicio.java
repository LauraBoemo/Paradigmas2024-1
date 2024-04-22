package org.example.model;

public class Exercicio {
    private int numero;
    private String nome;
    private String musculosAtivados;

    public Exercicio(int numero, String nome, String musculosAtivados) {
        this.numero = numero;
        this.nome = nome;
        this.musculosAtivados = musculosAtivados;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getMusculosAtivados() {
        return musculosAtivados;
    }

    @Override
    public String toString() {
        return "Exercicio{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", musculosAtivados='" + musculosAtivados + '\'' +
                '}';
    }
}
