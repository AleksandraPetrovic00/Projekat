package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.*;
import ac.rs.uns.ftn.fitnescentar.repository.*;
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
    private final SalaRepository salaRepository;

    @Autowired
    public TerminServiceImpl(TerminRepository terminRepository, KorisnikRepository korisnikRepository, TreningRepository treningRepository, SalaRepository salaRepository){
        this.terminRepository = terminRepository;
        this.korisnikRepository = korisnikRepository;
        this.treningRepository = treningRepository;
        this.salaRepository = salaRepository;
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

    @Override
    public Termin create(Termin termin) throws Exception{
        if(termin.getId()!=null){
            throw new Exception("ID mora biti null!");
        }
        Termin newTermin = this.terminRepository.save(termin);
        return newTermin;
    }

    @Override
    public Termin update(Termin termin) throws Exception{
        Termin terminToUpdate = this.terminRepository.getOne(termin.getId());
        if (terminToUpdate == null) {
            throw new Exception("Termin ne postoji");
        }
        terminToUpdate.setVreme(termin.getVreme());
        terminToUpdate.setCena(termin.getCena());
        terminToUpdate.setSala_termin(termin.getSala_termin());

        Termin savedTermin = this.terminRepository.save(terminToUpdate);
        return savedTermin;
    }

    @Override
    public List<Termin> sviZaTrenera(Long id){
        List<Termin> termini = this.terminRepository.findAllByTreningterminKorisniktreningId(id);

        return termini;
    }

    @Override
    public List<Termin> pretragaPoNazivuOpisuCeniVremenu(String naziv, String opis, double cena, Date vreme){
        List<Termin> termini = this.terminRepository.findAllByTreningterminNazivContainingAndTreningterminOpisContainingAndCenaLessThanEqualAndVremeGreaterThan(naziv, opis, cena, vreme);

        return termini;
    }

    @Override
    public List<Termin> pretragaPoNazivuOpisuCeniVremenuTipu(String naziv, String opis, double cena, Date vreme, TipTreninga tipTreninga){
        List<Termin> termini = this.terminRepository.findAllByTreningterminNazivContainingAndTreningterminOpisContainingAndCenaLessThanEqualAndVremeGreaterThanAndTreningterminTipTreninga(naziv, opis, cena, vreme, tipTreninga);

        return termini;
    }






}
