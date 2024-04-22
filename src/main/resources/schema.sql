CREATE TABLE alunos_treinos (
                                cpf VARCHAR(11),
                                treino_id INTEGER,
                                PRIMARY KEY (cpf, treino_id),
                                FOREIGN KEY (cpf) REFERENCES alunos(cpf) ON DELETE CASCADE,
                                FOREIGN KEY (treino_id) REFERENCES treinos(id) ON DELETE CASCADE
);
