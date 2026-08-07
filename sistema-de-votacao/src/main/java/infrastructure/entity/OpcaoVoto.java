package infrastructure.entity;

import jakarta.persistence.*;

@Entity
public class OpcaoVoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private Integer quantidadeVotos = 0;

    @ManyToOne
    private Enquete enquete;
}
