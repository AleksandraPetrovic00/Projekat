package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.Trening;

import java.util.List;

public interface TreningService {

    Trening findOne(Long id);

    List<Trening> findAll();

    List<Trening> findByNaziv(String naziv);

    List<Trening> findByTipTreninga(TipTreninga tipTreninga);

    List<Trening> findByOpis(String opis);

    Trening create(Trening trening) throws Exception;

    Trening update(Trening trening) throws Exception;

    void delete(Long id);


}
