package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;

import java.util.List;

public interface KorisnikService {

    Korisnik findOne(Long id);

    List<Korisnik> findAll();

    //Korisnik prijava(Korisnik korisnik);

    Korisnik create(Korisnik korisnik) throws Exception;

    void delete(Long id);

}
