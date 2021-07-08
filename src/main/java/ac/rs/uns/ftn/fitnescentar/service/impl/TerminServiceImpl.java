package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.repository.KorisnikRepository;
import ac.rs.uns.ftn.fitnescentar.repository.TerminRepository;
import ac.rs.uns.ftn.fitnescentar.repository.TreningRepository;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


@Service
public class TerminServiceImpl implements TerminService {

    private final TerminRepository terminRepository;
    private final KorisnikRepository korisnikRepository;
    private final TreningRepository treningRepository;

    @Autowired
    public TerminServiceImpl(TerminRepository terminRepository, KorisnikRepository korisnikRepository, TreningRepository treningRepository){
        this.terminRepository = terminRepository;
        this.korisnikRepository = korisnikRepository;
        this.treningRepository = treningRepository;
    }

    @Override
    public List<Termin> sviTreninzi(){
        List<Termin> termini = this.terminRepository.findAll();

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoNazivu(String naziv){
        List<Termin> termini = this.terminRepository.findAllByTreningterminNazivContaining(naziv);

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoTipuTreninga(TipTreninga tipTreninga){
        List<Termin> termini = this.terminRepository.findAllByTreningterminTipTreninga(tipTreninga);

        return  termini;
    }

    @Override
    public List<Termin> pronadjiPoOpisu(String opis){
        List<Termin> termini = this.terminRepository.findAllByTreningterminOpisContaining(opis);

        return termini;
    }




    @Override
    public List<Termin> sviPoCeniRastce(){
        List<Termin> termini = this.terminRepository.findAllByOrderByCenaAsc();

        return termini;
    }

    @Override
    public List<Termin> sviPoCeniOpadajuce(){
        List<Termin> termini = this.terminRepository.findAllByOrderByCenaDesc();

        return termini;
    }

    @Override
    public List<Termin> sviPoVremenuRastuce(){
        List<Termin> termini = this.terminRepository.findAllByOrderByVremeAsc();

        return termini;
    }

    @Override
    public List<Termin> sviPoVremenuOpadajuce(){
        List<Termin> termini = this.terminRepository.findAllByOrderByVremeDesc();

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoCeni(double cena){
        List<Termin> termini = this.terminRepository.findByCenaLessThanEqual(cena);

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoVremenuPosle(Date vreme){
        List<Termin> termini = this.terminRepository.findByVremeLessThanEqual(vreme);

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoCeniINazivu(double cena, String naziv){
        List<Termin> termini = this.terminRepository.findByCenaLessThanEqualAndTreningterminNazivContaining(cena, naziv);

        return termini;
    }

    @Override
    public List<Termin> sviPoID(){
        List<Termin> termini = this.terminRepository.findAllByOrderById();

        return termini;
    }

    @Override
    public List<Termin> pronadjiPrijavljeneTreninge(Long id){
        List<Termin> prijavljeniTermini = this.terminRepository.findAllByPrijavljeniKorisniciId(id);

        return prijavljeniTermini;
    }

    @Override
    public Termin findOne(Long id){
        Termin termin = this.terminRepository.getOne(id);

        return termin;
    }



}
