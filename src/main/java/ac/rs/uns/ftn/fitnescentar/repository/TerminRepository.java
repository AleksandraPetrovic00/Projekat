package ac.rs.uns.ftn.fitnescentar.repository;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {

    List<Termin> findAllByTreningterminNazivContaining(String naziv);

    List<Termin> findAllByTreningterminTipTreningaContaining(TipTreninga tipTreninga);

    List<Termin> findAllByTreningterminOpisContaining(String opis);

    List<Termin> findAllByOrderByCenaAsc();

    List<Termin> findAllByOrderByCenaDesc();

    List<Termin> findAllByOrderByVremeAsc();

    List<Termin> findAllByOrderByVremeDesc();

    List<Termin> findByVremeAfterOrderByVremeAsc(Time vreme);

    List<Termin> findByCenaLessThanEqualOrderByVremeAsc(double cena);

    List<Termin> findByCenaLessThanEqualAndTreningterminNazivContaining(double cena, String naziv);

}
