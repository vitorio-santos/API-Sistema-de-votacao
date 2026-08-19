package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultadoDTO {

    private String titulo;
    private Integer totalVotos;
    private String vencedora;
    private List<OpcaoResultadoDTO> opcoes;

    public ResultadoDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(Integer totalVotos) {
        this.totalVotos = totalVotos;
    }

    public String getVencedora() {
        return vencedora;
    }

    public void setVencedora(String vencedora) {
        this.vencedora = vencedora;
    }

    public List<OpcaoResultadoDTO> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<OpcaoResultadoDTO> opcoes) {
        this.opcoes = opcoes;
    }

    public void setEnqueteId(Long id) {
    }

    public void setPergunta(String pergunta) {
    }

    public void setVencedor(OpcaoResultadoDTO vencedor) {
    }
}
