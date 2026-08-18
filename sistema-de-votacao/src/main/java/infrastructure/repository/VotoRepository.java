package infrastructure.repository;

import infrastructure.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsByUsuarioIdAndEnqueteId(Long usuarioId,
                                          Long enqueteId);
}
