# Paradigmas 2024/1
## Trabalho 01: Controle de Academia

Em Java, crie um programa para controle de uma academia que atenda aos seguintes requisitos mínimos:

- [x] Os dados de alunos e de exercícios devem ser armazenados em uma base de dados relacional (postgres, mysql, sqlserver etc.). Os dados de outras entidades podem ser armazenados em listas em memória (quem desejar, pode armazená-los no banco também).
- [x] Deve ser possível cadastrar alunos: incluir, alterar, excluir, listar, buscar pelo CPF, e pelo nome. Cada aluno deve ter: CPF, nome, data de nascimento.
- [x] Deve ser possível cadastrar planos. Cada plano deve ter: código, nome, valor mensal.
- [x] Deve ser possível cadastrar exercícios. Cada exercício deve conter: número, nome, músculos ativados.
- [x] Para alunos cadastrados, deve ser possível ao instrutor:
  - [x] Cadastrar um plano, contendo: data de início do plano, dados do cartão de crédito
  - [x] Cadastrar uma ou mais opções de treino, onde cada opção de treino contém uma lista de exercícios.
  - [x] Para cada exercício, informar: o número de séries, o número mínimo e máximo de repetições, a carga utilizada (em kgs) e o tempo de descanso (em minutos)
  - [x] Alterar ou excluir opções de treino e os dados dos exercícios cadastrados.
- [ ] Deve ser possível ao aluno, em determinada data, iniciar um treino:
  - [ ] Escolher um treino dentre as opções disponíveis.
  - [ ] Consultar os exercícios a serem feitos, mostrando os dados cadastrados.
  - [ ] Marcar exercícios do treino que foram concluídos.
  - [ ] Alterar a carga de um determinado exercício.
  - [ ] Encerrar um treino.
- [ ] Relatórios
  - [ ] Mostrar as datas em que o aluno compareceu à academia em um intervalo de datas.
  - [ ] Para um determinado exercício, mostrar a evolução de carga ao longo do tempo.

