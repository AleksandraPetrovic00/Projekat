package ac.rs.uns.ftn.fitnescentar.repository;

import ac.rs.uns.ftn.fitnescentar.model.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena, Long> {

    Ocena findByTerminocenaId(Long id);

}
