package ac.rs.uns.ftn.fitnescentar.repository;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.dto.TerminTrDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {

    List<Termin> findAll();

    List<Termin> findAllByOrderById();

    List<Termin> findAllByTreningterminKorisniktreningId(Long id);

    List<Termin> findAllByTreningterminNazivContaining(String naziv);

    List<Termin> findAllByTreningterminTipTreninga(TipTreninga tipTreninga);

    List<Termin> findAllByTreningterminOpisContaining(String opis);

    List<Termin> findAllByOrderByCenaAsc();

    List<Termin> findAllByOrderByCenaDesc();

    List<Termin> findAllByOrderByVremeAsc();

    List<Termin> findAllByOrderByVremeDesc();

    List<Termin> findByVremeLessThanEqual(Date vreme);

    List<Termin> findByCenaLessThanEqual(double cena);

    List<Termin> findByCenaLessThanEqualAndTreningterminNazivContaining(double cena, String naziv);

    List<Termin> findAllByPrijavljeniKorisniciId(Long id);

}
