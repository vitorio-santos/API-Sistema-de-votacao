package com.VitorioSantos.sistema_de_votacao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "votos",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_voto_usuario_enquete",
                        columnNames = {"usuario_id", "enquete_id"}
                )
        }
)
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "enquete_id", nullable = false)
    private Enquete enquete;

    @ManyToOne
    @JoinColumn(name = "opcao_id", nullable = false)
    private OpcaoVoto opcao;

    @Column(name = "data_voto", nullable = false)
    private LocalDateTime dataVoto;
}
