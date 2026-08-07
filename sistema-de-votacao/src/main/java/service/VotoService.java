package service;

import infrastructure.repository.EnqueteRepository;
import infrastructure.repository.OpcaoVotoRepository;
import infrastructure.repository.UsuarioRepository;
import infrastructure.repository.VotoRepository;
import infrastructure.entity.Enquete;
import infrastructure.entity.StatusEnquete;
import infrastructure.entity.Usuario;
import org.springframework.stereotype.Service;

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

        if(enquete.getStatus() != StatusEnquete.ABERTA) {
            throw new RuntimeException("Enquete encerrada ou cancelada.");
        }

        Usuario usuario = usuarioRepository
                .findById(usuarioID)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado."));



        if(votoRepository.existsByUsuarioIdAndEnqueteId(usuarioID, enqueteId)) {
            throw new RuntimeException("Usuário já votou nesta enquete.");
        }

    }
}
