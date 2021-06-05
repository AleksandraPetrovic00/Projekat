package ac.rs.uns.ftn.fitnescentar.repository;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {

    List<Termin> findByVremeAfterOrderByVreme(double vreme);

    List<Termin> findByCenaOrderByVreme(double cena);

    List<Termin> findByCenaAndTreningterminNazivContaining(double cena, String naziv);

}
