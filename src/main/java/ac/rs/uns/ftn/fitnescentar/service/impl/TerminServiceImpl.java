package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.repository.TerminRepository;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;


@Service
public class TerminServiceImpl implements TerminService {

    private final TerminRepository terminRepository;

    @Autowired
    public TerminServiceImpl(TerminRepository terminRepository){
        this.terminRepository = terminRepository;
    }

    @Override
    public List<Termin> findAll(){
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
        List<Termin> termini = this.terminRepository.findAllByTreningterminTipTreningaContaining(tipTreninga);

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
        List<Termin> termini = this.terminRepository.findByCenaLessThanEqualOrderByVremeAsc(cena);

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoVremenuPosle(Time vreme){
        List<Termin> termini = this.terminRepository.findByVremeAfterOrderByVremeAsc(vreme);

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoCeniINazivu(double cena, String naziv){
        List<Termin> termini = this.terminRepository.findByCenaLessThanEqualAndTreningterminNazivContaining(cena, naziv);

        return termini;
    }


}
