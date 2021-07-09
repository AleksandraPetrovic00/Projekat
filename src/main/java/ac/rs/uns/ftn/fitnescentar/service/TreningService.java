package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.Trening;

import java.util.List;

public interface TreningService {

    Trening findOne(Long id);

    List<Trening> findAll();

    List<Trening> pretragaPoNazivu(String naziv);

    List<Trening> pretragaPoTipu(TipTreninga tipTreninga);

    List<Trening> pretragaPoOpisu(String opis);

    List<Trening> pretragaPoIdTrenera(Long id);

    Trening create(Trening trening) throws Exception;

    Trening update(Trening trening) throws Exception;

    void delete(Long id);


}
