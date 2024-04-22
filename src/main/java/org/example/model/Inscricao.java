package org.example.model;

import java.time.LocalDate;

public class Inscricao {
    private String alunoCPF;
    private int planoId;
    private LocalDate dataInicio;
    private String numeroCartao;

    public Inscricao(String alunoCPF, int planoId, LocalDate dataInicio, String numeroCartao) {
        this.alunoCPF = alunoCPF;
        this.planoId = planoId;
        this.dataInicio = dataInicio;
        this.numeroCartao = numeroCartao;
    }


    public String getAlunoCPF() {
        return alunoCPF;
    }

    public int getPlanoId() {
        return planoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
