package ac.rs.uns.ftn.fitnescentar.repository;

import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.Trening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreningRepository extends JpaRepository<Trening, Long> {

    List<Trening> findAllByNaziv(String naziv);

    List<Trening> findAllByTipTreninga(TipTreninga tipTreninga);

    List<Trening> findAllByOpis(String opis);

    List<Trening> findAllByKorisniktreningId(Long id);



}
