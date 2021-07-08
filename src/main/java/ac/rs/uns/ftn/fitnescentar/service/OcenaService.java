package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Ocena;

import java.util.List;

public interface OcenaService {

    Ocena findOne(Long id);

    List<Ocena> findAll();

    Ocena pronadjiPoTerminu(Long id);

    Ocena create(Ocena ocena) throws Exception;

    Ocena update(Ocena ocena) throws Exception;

    void delete(Long id);
}
