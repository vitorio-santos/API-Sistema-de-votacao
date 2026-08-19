package infrastructure.repository;

import infrastructure.entity.Enquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnqueteRepository extends JpaRepository<Enquete, Long> {
}
