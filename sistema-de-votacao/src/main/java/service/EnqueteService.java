package service;

import dto.CriarEnqueteDTO;
import dto.ResultadoDTO;
import infrastructure.entity.Enquete;
import infrastructure.repository.EnqueteRepository;
import infrastructure.repository.OpcaoVotoRepository;
import infrastructure.repository.UsuarioRepository;
import infrastructure.repository.VotoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

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

    public Enquete criar(CriarEnqueteDTO dto) {
        return null;
    }

    public List<Enquete> listar() {
        return List.of();
    }

    public Enquete buscar(Long enqueteId) {
        return null;
    }

    public ResultadoDTO resultado(Long enqueteId) {
        return null;
    }
    public void encerrar(Long enqueteId) {
    }
}
