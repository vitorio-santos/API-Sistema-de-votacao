CREATE TABLE usuario (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(255),
                         email VARCHAR(255) UNIQUE
);

CREATE TABLE enquete (
                         id BIGSERIAL PRIMARY KEY,
                         titulo VARCHAR(255),
                         pergunta VARCHAR(255),
                         status VARCHAR(255),
                         data_criacao TIMESTAMP,
                         data_encerramento TIMESTAMP,
                         usuario_id BIGINT,

                         CONSTRAINT fk_enquete_usuario
                             FOREIGN KEY (usuario_id)
                                 REFERENCES usuario(id)
);

CREATE TABLE opcao_voto (
                            id BIGSERIAL PRIMARY KEY,
                            texto VARCHAR(255),
                            quantidade_votos INTEGER,
                            enquete_id BIGINT NOT NULL,

                            CONSTRAINT fk_opcao_voto_enquete
                                FOREIGN KEY (enquete_id)
                                    REFERENCES enquete(id)
                                    ON DELETE CASCADE
);

CREATE TABLE voto (
                      id BIGSERIAL PRIMARY KEY,
                      usuario_id BIGINT,
                      enquete_id BIGINT,
                      opcao_id BIGINT,
                      data_voto TIMESTAMP,

                      CONSTRAINT fk_voto_usuario
                          FOREIGN KEY (usuario_id)
                              REFERENCES usuario(id),

                      CONSTRAINT fk_voto_enquete
                          FOREIGN KEY (enquete_id)
                              REFERENCES enquete(id),

                      CONSTRAINT fk_voto_opcao
                          FOREIGN KEY (opcao_id)
                              REFERENCES opcao_voto(id),

                      CONSTRAINT uk_voto_usuario_enquete
                          UNIQUE (usuario_id, enquete_id)
);