package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.FitnesCentar;
import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.dto.KorisnikPrijavaDTO;
import ac.rs.uns.ftn.fitnescentar.repository.KorisnikRepository;
import ac.rs.uns.ftn.fitnescentar.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikServiceImpl(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public Korisnik findOne(Long id) {
        Korisnik korisnik = this.korisnikRepository.getOne(id);
        return korisnik;
    }

    @Override
    public List<Korisnik> findAll() {
        List<Korisnik> korisnici = this.korisnikRepository.findAll();
        return korisnici;
    }

    @Override
    public Korisnik create(Korisnik korisnik) throws Exception {
        if(korisnik.getId() != null){
            throw new Exception("ID mora biti null!");
        }
        Korisnik newKorisnik = this.korisnikRepository.save(korisnik);
        return newKorisnik;
    }

    @Override
    public Korisnik update(Korisnik korisnik) throws Exception {
        Korisnik korisnikToUpdate = this.korisnikRepository.getOne(korisnik.getId());
        if (korisnikToUpdate == null) {
            throw new Exception("Korisnik ne postoji");
        }

        korisnikToUpdate.setKorisnickoIme(korisnik.getKorisnickoIme());
        korisnikToUpdate.setLozinka(korisnik.getLozinka());
        korisnikToUpdate.setIme(korisnik.getIme());
        korisnikToUpdate.setPrezime(korisnik.getPrezime());
        korisnikToUpdate.setKontaktTelefon(korisnik.getKontaktTelefon());
        korisnikToUpdate.setEmailAdresa(korisnik.getEmailAdresa());
        korisnikToUpdate.setDatumRodjenja(korisnik.getDatumRodjenja());
        korisnikToUpdate.setUloga(korisnik.getUloga());
        korisnikToUpdate.setAktivan(korisnik.isAktivan());

        Korisnik savedKorisnik = this.korisnikRepository.save(korisnikToUpdate);
        return savedKorisnik;
    }

    @Override
    public Korisnik prijava(KorisnikPrijavaDTO korisnikPrijavaDTO){

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnikPrijavaDTO.getKorisnickoIme());
        if(korisnik==null || !korisnik.getLozinka().equals(korisnikPrijavaDTO.getLozinka())){
            return null;
        }else{
            return korisnik;
        }

    }

    @Override
    public void delete(Long id) {
        this.korisnikRepository.deleteById(id);
    }

}
