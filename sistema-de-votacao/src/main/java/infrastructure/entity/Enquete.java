package infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Enquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String pergunta;

    @Enumerated(EnumType.STRING)
    private StatusEnquete status;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataEncerramento;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "enquete")
    private List<OpcaoVoto> opcoes;
}
