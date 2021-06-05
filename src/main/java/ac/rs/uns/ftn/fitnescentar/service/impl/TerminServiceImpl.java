package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.repository.TerminRepository;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TerminServiceImpl implements TerminService {

    private final TerminRepository terminRepository;

    @Autowired
    public TerminServiceImpl(TerminRepository terminRepository){
        this.terminRepository = terminRepository;
    }

    @Override
    public List<Termin> pronadjiPoCeni(double cena){
        List<Termin> termini = this.terminRepository.findByCenaOrderByVreme(cena);

        return termini;
    }

    @Override
    public List<Termin> pronadjiPoVremenuPosle(double vreme){
        List<Termin> termini = this.terminRepository.findByVremeAfterOrderByVreme(vreme);

        return termini;
    }

    /*@Override
    public List<Termin> pronadjiPoCeniINazivu(double cena, String naziv){
        List<Termin> termini = this.terminRepository.findByCenaAndTreningterminNazivContaining(cena, naziv);

        return termini;
    }*/


}
