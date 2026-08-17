package service;

import infrastructure.entity.*;
import infrastructure.repository.EnqueteRepository;
import infrastructure.repository.OpcaoVotoRepository;
import infrastructure.repository.UsuarioRepository;
import infrastructure.repository.VotoRepository;
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

    public void votar(Long enqueteId,
                      Long usuarioID,
                      Long opcaoId) {

        Enquete enquete = enqueteRepository
                .findById(enqueteId)
                .orElseThrow(() ->
                        new RuntimeException("Enquete não encontrada."));

        if (enquete.getStatus() != StatusEnquete.ABERTA) {
            throw new RuntimeException("Enquete encerrada ou cancelada");
        }

        Usuario usuario = usuarioRepository
                .findById(usuarioID)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado."));

        if(votoRepository.existsByUsuarioIdAndEnqueteId(usuarioID, enqueteId)) {
            throw new RuntimeException("Usuário já votou nesta enquete.");
        }

        OpcaoVoto opcao = opcaoVotoRepository
                .findById(opcaoId)
                .orElseThrow(() ->
                        new RuntimeException("Opção não encontrada."));

        if (!opcao.getEnquete().getId().equals(enqueteId)) {
            throw new RuntimeException(
                    "A opção não pertence a esta enquete."
            );
        }

        Voto voto = new Voto();
        votoRepository.save(voto);
    }
}
