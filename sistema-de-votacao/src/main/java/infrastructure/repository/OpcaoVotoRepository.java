package infrastructure.repository;

import infrastructure.entity.OpcaoVoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcaoVotoRepository extends JpaRepository<OpcaoVoto, Long> {
}
