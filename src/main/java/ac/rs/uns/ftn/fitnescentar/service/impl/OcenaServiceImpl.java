package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.Ocena;
import ac.rs.uns.ftn.fitnescentar.repository.KorisnikRepository;
import ac.rs.uns.ftn.fitnescentar.repository.OcenaRepository;
import ac.rs.uns.ftn.fitnescentar.repository.TerminRepository;
import ac.rs.uns.ftn.fitnescentar.service.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcenaServiceImpl implements OcenaService {

    private final OcenaRepository ocenaRepository;

    private final KorisnikRepository korisnikRepository;

    private final TerminRepository terminRepository;

    @Autowired
    public OcenaServiceImpl(OcenaRepository ocenaRepository, KorisnikRepository korisnikRepository, TerminRepository terminRepository){
        this.ocenaRepository = ocenaRepository;
        this.korisnikRepository = korisnikRepository;
        this.terminRepository = terminRepository;
    }

    @Override
    public Ocena findOne(Long id){
        Ocena ocena = this.ocenaRepository.getOne(id);
        return ocena;
    }

    @Override
    public List<Ocena> findAll(){
        List<Ocena> ocene = this.ocenaRepository.findAll();
        return ocene;
    }

    @Override
    public Ocena create(Ocena ocena) throws Exception{
        if(ocena.getId()!=null){
            throw new Exception("ID mora biti null");
        }
        Ocena newOcena = this.ocenaRepository.save(ocena);
        return newOcena;
    }

    @Override
    public Ocena update(Ocena ocena) throws Exception{
        Ocena ocenaToUpdate = this.ocenaRepository.getOne(ocena.getId());
        if(ocenaToUpdate == null){
            throw new Exception("Ocena ne postoji");
        }
            ocenaToUpdate.setOcena(ocena.getOcena());

        Ocena savedOcena = this.ocenaRepository.save(ocena);
        return savedOcena;
    }

    @Override
    public void delete(Long id){
        this.ocenaRepository.deleteById(id);
    }

    @Override
    public Ocena pronadjiPoTerminu(Long id){
        Ocena ocena = this.ocenaRepository.findByTerminocenaId(id);

        return ocena;
    }


}
