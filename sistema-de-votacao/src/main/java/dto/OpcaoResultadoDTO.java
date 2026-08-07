package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpcaoResultadoDTO {

    private String texto;
    private Integer votos;
    private Double percentual;

    public OpcaoResultadoDTO() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }
}
