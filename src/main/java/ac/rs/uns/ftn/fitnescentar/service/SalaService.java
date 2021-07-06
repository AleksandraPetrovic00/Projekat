package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Sala;

import java.util.List;

public interface SalaService {
    Sala findOne(Long id);

    List<Sala> findAll();

    List<Sala> pronadjiSveZaFitnesCentar(Long fitnesCentarId);

    Sala create(Sala sala) throws Exception;

    Sala update(Sala sala) throws Exception;

    void delete(Long id);
}
