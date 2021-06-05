package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.Trening;
import ac.rs.uns.ftn.fitnescentar.repository.TreningRepository;
import ac.rs.uns.ftn.fitnescentar.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreningServiceImpl implements TreningService {

    private final TreningRepository treningRepository;

    @Autowired
    public TreningServiceImpl(TreningRepository treningRepository){
        this.treningRepository = treningRepository;
    }

    @Override
    public Trening findOne(Long id){
        Trening trening = this.treningRepository.getOne(id);

        return trening;
    }

    @Override
    public List<Trening> findAll() {
        List<Trening> treninzi = this.treningRepository.findAll();
        return treninzi;
    }

    @Override
    public List<Trening> pretragaPoNazivu(String naziv){

        List<Trening> treninzi = this.treningRepository.findAllByNaziv(naziv);

        return treninzi;
    }

    @Override
    public List<Trening> pretragaPoTipu(TipTreninga tipTreninga){
        List<Trening> treninzi = this.treningRepository.findAllByTipTreninga(tipTreninga);

        return treninzi;
    }

    @Override
    public List<Trening> pretragaPoOpisu(String opis) {
        List<Trening> treninzi = this.treningRepository.findAllByOpis(opis);

        return treninzi;
    }

    @Override
    public Trening create(Trening trening) throws Exception {
        if (trening.getId() != null) {
            throw new Exception("ID mora biti null!");
        }
        Trening newTrening = this.treningRepository.save(trening);

        return newTrening;
    }

    @Override
    public void delete(Long id) {
        this.treningRepository.deleteById(id);
    }




}
