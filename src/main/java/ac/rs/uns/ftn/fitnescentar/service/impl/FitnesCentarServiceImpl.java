package ac.rs.uns.ftn.fitnescentar.service.impl;

import ac.rs.uns.ftn.fitnescentar.model.FitnesCentar;
import ac.rs.uns.ftn.fitnescentar.repository.FitnesCentarRepository;
import ac.rs.uns.ftn.fitnescentar.service.FitnesCentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnesCentarServiceImpl implements FitnesCentarService {

    private final FitnesCentarRepository fitnesCentarRepository;

    @Autowired
    public FitnesCentarServiceImpl(FitnesCentarRepository fitnesCentarRepository){
        this.fitnesCentarRepository = fitnesCentarRepository;
    }

    @Override
    public FitnesCentar findOne(Long id) {
        FitnesCentar fitnesCentar = this.fitnesCentarRepository.getOne(id);
        return fitnesCentar;
    }

    @Override
    public List<FitnesCentar> findAll() {
        List<FitnesCentar> fitnesCentri = this.fitnesCentarRepository.findAll();
        return fitnesCentri;
    }

    @Override
    public FitnesCentar create(FitnesCentar fitnesCentar) throws Exception {
        if(fitnesCentar.getId() != null){
            throw new Exception("ID mora biti null!");
        }
        FitnesCentar newFitnesCentar = this.fitnesCentarRepository.save(fitnesCentar);
        return newFitnesCentar;
    }

    @Override
    public FitnesCentar update(FitnesCentar fitnesCentar) throws Exception {
        FitnesCentar fitnesCentarToUpdate = this.fitnesCentarRepository.getOne(fitnesCentar.getId());
        if (fitnesCentarToUpdate == null) {
            throw new Exception("Fitnes centar ne postoji");
        }
        fitnesCentarToUpdate.setNaziv(fitnesCentar.getNaziv());
        fitnesCentarToUpdate.setAdresa(fitnesCentar.getAdresa());
        fitnesCentarToUpdate.setBrojTelefonaCentrale(fitnesCentar.getBrojTelefonaCentrale());
        fitnesCentarToUpdate.setEmail(fitnesCentar.getEmail());

        FitnesCentar savedFitnesCentar = this.fitnesCentarRepository.save(fitnesCentarToUpdate);
        return savedFitnesCentar;
    }

    @Override
    public void delete(Long id) {
        this.fitnesCentarRepository.deleteById(id);
    }

}
