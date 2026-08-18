package service;

import dto.CriarEnqueteDTO;
import dto.OpcaoResultadoDTO;
import dto.ResultadoDTO;
import infrastructure.entity.Enquete;
import infrastructure.entity.StatusEnquete;
import infrastructure.entity.Usuario;
import infrastructure.entity.OpcaoVoto;
import infrastructure.repository.EnqueteRepository;
import infrastructure.repository.OpcaoVotoRepository;
import infrastructure.repository.UsuarioRepository;
import infrastructure.repository.VotoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Service
public class EnqueteService {

    private final EnqueteRepository enqueteRepository;
    private final UsuarioRepository usuarioRepository;
    private final OpcaoVotoRepository opcaoVotoRepository;
    private final VotoRepository votoRepository;

    public EnqueteService(
            EnqueteRepository enqueteRepository,
            UsuarioRepository usuarioRepository,
            OpcaoVotoRepository opcaoVotoRepository,
            VotoRepository votoRepository) {

        this.enqueteRepository = enqueteRepository;
        this.usuarioRepository = usuarioRepository;
        this.opcaoVotoRepository = opcaoVotoRepository;
        this.votoRepository = votoRepository;
    }

    public void encerrar(Long enqueteId) {

        Enquete enquete = enqueteRepository.findById(enqueteId)
                .orElseThrow(() ->
                        new RuntimeException("Enquete não encontrada."));

        if (enquete.getStatus() != StatusEnquete.ABERTA) {
            throw new RuntimeException("A enquete não está aberta.");
        }

        enquete.setStatus(StatusEnquete.ENCERRADA);
        enquete.setDataEncerramento(LocalDateTime.now());

        enqueteRepository.save(enquete);
    }

    public Enquete criar(CriarEnqueteDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado."));

        Enquete enquete = new Enquete();

        enquete.setTitulo(dto.getTitulo());
        enquete.setPergunta(dto.getPergunta());
        enquete.setUsuario(usuario);
        enquete.setStatus(StatusEnquete.ABERTA);
        enquete.setDataCriacao(LocalDateTime.now());

        for (String textoOpcao : dto.getOpcoes()) {

            OpcaoVoto opcao = new OpcaoVoto();

            opcao.setTexto(textoOpcao);
            opcao.setQuantidadeVotos(0);
            opcao.setEnquete(enquete);

            enquete.getOpcoes().add(opcao);
        }

        return enqueteRepository.save(enquete);
    }

    public List<Enquete> listar() {
        return enqueteRepository.findAll();
    }

    public Enquete buscar(Long enqueteId) {
        return enqueteRepository.findById(enqueteId)
                .orElseThrow(()-> new RuntimeException("Enquete não encontrada"));
    }

    public ResultadoDTO resultado(Long enqueteId) {

        Enquete enquete = enqueteRepository.findById(enqueteId)
                .orElseThrow(() ->
                        new RuntimeException("Enquete não encontrada."));

        List<OpcaoVoto> opcoes = enquete.getOpcoes();

        int totalVotos = opcoes.stream()
                .mapToInt(OpcaoVoto::getQuantidadeVotos)
                .sum();

        List<OpcaoResultadoDTO> resultados = opcoes.stream()
                .map(opcao -> {

                    double percentual = totalVotos == 0
                            ? 0
                            : (opcao.getQuantidadeVotos() * 100.0) / totalVotos;

                    return new OpcaoResultadoDTO(
                            opcao.getTexto(),
                            opcao.getQuantidadeVotos(),
                            percentual
                    );
                })
                .sorted((a, b) ->
                        Integer.compare(
                                b.getQuantidadeVotos(),
                                a.getQuantidadeVotos()
                        )
                )
                .toList();

        OpcaoResultadoDTO vencedor = resultados.isEmpty()
                ? null
                : resultados.get(0);

        ResultadoDTO resultado = new ResultadoDTO();

        resultado.setEnqueteId(enquete.getId());
        resultado.setPergunta(enquete.getPergunta());
        resultado.setTotalVotos(totalVotos);
        resultado.setOpcoes(resultados);
        resultado.setVencedor(vencedor);

        return resultado;
    }
}
