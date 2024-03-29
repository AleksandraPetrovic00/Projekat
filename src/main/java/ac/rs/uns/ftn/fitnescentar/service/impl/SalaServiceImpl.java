package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.Sala;
import ac.rs.uns.ftn.fitnescentar.repository.FitnesCentarRepository;
import ac.rs.uns.ftn.fitnescentar.repository.KorisnikRepository;
import ac.rs.uns.ftn.fitnescentar.repository.SalaRepository;
import ac.rs.uns.ftn.fitnescentar.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;
    private final FitnesCentarRepository fitnesCentarRepository;
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public SalaServiceImpl(SalaRepository salaRepository, FitnesCentarRepository fitnesCentarRepository, KorisnikRepository korisnikRepository){
        this.salaRepository = salaRepository;
        this.fitnesCentarRepository = fitnesCentarRepository;
        this.korisnikRepository = korisnikRepository;
    }
    @Override
    public Sala findOne(Long id) {
        Sala sala = this.salaRepository.getOne(id);
        return sala;
    }

    @Override
    public List<Sala> findAll() {
        List<Sala> fitnesCentri = this.salaRepository.findAll();
        return fitnesCentri;
    }

    @Override
    public List<Sala> pronadjiSveZaFitnesCentar(Long fitnesCentarId){
        List<Sala> fitnesCentri = this.salaRepository.findAllByFitnescentarsalaId(fitnesCentarId);

        return fitnesCentri;
    }

    @Override
    public Sala create(Sala sala) throws Exception {
        if(sala.getId() != null){
            throw new Exception("ID mora biti null!");
        }
        Sala newSala = this.salaRepository.save(sala);
        return newSala;
    }

    @Override
    public Sala update(Sala sala) throws Exception {
        Sala salaToUpdate = this.salaRepository.getOne(sala.getId());
        if (salaToUpdate == null) {
            throw new Exception("Sala ne postoji");
        }
        salaToUpdate.setKapacitet(sala.getKapacitet());
        salaToUpdate.setOznakaSale(sala.getOznakaSale());

        Sala savedSala = this.salaRepository.save(salaToUpdate);
        return savedSala;
    }

    @Override
    public void delete(Long id) {
        this.salaRepository.deleteById(id);
    }


}
