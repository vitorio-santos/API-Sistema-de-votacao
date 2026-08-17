package service;

import exception.BusinessException;
import exception.ResourceNotFoundException;
import infrastructure.entity.*;
import infrastructure.repository.EnqueteRepository;
import infrastructure.repository.OpcaoVotoRepository;
import infrastructure.repository.UsuarioRepository;
import infrastructure.repository.VotoRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@Service
public class VotoService {

    private final UsuarioRepository usuarioRepository;
    private final EnqueteRepository enqueteRepository;
    private final OpcaoVotoRepository opcaoVotoRepository;
    private final VotoRepository votoRepository;

    public VotoService(UsuarioRepository usuarioRepository,
                       EnqueteRepository enqueteRepository,
                       OpcaoVotoRepository opcaoVotoRepository,
                       VotoRepository votoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enqueteRepository = enqueteRepository;
        this.opcaoVotoRepository = opcaoVotoRepository;
        this.votoRepository = votoRepository;
    }

    @Transactional
    public void votar(Long enqueteId,
                      Long usuarioID,
                      Long opcaoId) {

        Enquete enquete = enqueteRepository
                .findById(enqueteId)
                .orElseThrow(() ->
                        new RuntimeException("Enquete não encontrada."));

        if (enquete.getStatus() != StatusEnquete.ABERTA) {
            throw new RuntimeException("A enquete não está aberta");
        }

        enquete.setStatus(StatusEnquete.ENCERRADA);
        enquete.setDataEncerramento(LocalDateTime.now());

        enqueteRepository.save(enquete);

        Usuario usuario = usuarioRepository
                .findById(usuarioID)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado."));

        if (votoRepository.existsByUsuarioIdAndEnqueteId(usuarioID, enqueteId)) {
            throw new BusinessException("Usuário já votou nesta enquete.");
        }

        OpcaoVoto opcao = opcaoVotoRepository
                .findById(opcaoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Opção não encontrada."));

        if (!opcao.getEnquete().getId().equals(enqueteId)) {
            throw new RuntimeException("A opção não pertence a esta enquete.");
        }
        Voto voto = new Voto();
        voto.setUsuario(usuario);
        voto.setEnquete(enquete);
        voto.setOpcao(opcao);
        voto.setDataVoto(LocalDateTime.now());

        votoRepository.save(voto);

        opcao.setQuantidadeVotos(opcao.getQuantidadeVotos() + 1);
        opcaoVotoRepository.save(opcao);
    }
}
