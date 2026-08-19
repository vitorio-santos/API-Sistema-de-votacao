package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpcaoResultadoDTO {

    private String texto;
    private int quantidadeVotos;
    private double percentual;

    public OpcaoResultadoDTO(
            String texto,
            int quantidadeVotos,
            double percentual) {

        this.texto = texto;
        this.quantidadeVotos = quantidadeVotos;
        this.percentual = percentual;
    }
}
