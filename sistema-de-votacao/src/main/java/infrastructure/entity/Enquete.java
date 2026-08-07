package infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Enquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String pergunta;

    @Enumerated(EnumType.STRING)
    private StatusEnquete.statusEnquete status;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataEncerramento;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "enquete")
    private List<OpcaoVoto> opcoes;

    public boolean getStatus() {
        return false;
    }
}
