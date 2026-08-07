package dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter

public class CriarEnqueteDTO {
    private String titulo;
    private String pergunta;
    private Long usuarioId;
    private List<String> opcoes;
}
