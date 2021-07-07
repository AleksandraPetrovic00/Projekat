package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.dto.KorisnikPrijavaDTO;
import ac.rs.uns.ftn.fitnescentar.model.dto.TerminTrDTO;

import java.util.List;

public interface KorisnikService {

    Korisnik findOne(Long id);

    List<Korisnik> findAll();

    Korisnik prijava(KorisnikPrijavaDTO korisnikPrijavaDTO);

    Korisnik update(Korisnik korisnik) throws Exception;

    Korisnik create(Korisnik korisnik) throws Exception;

    void delete(Long id);

}
