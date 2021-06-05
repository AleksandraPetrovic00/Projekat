package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Termin;

import java.util.List;

public interface TerminService {

    List<Termin> pronadjiPoCeni(double cena);

    List<Termin> pronadjiPoVremenuPosle(double vreme);

    //List<Termin> pronadjiPoCeniINazivu(double cena, String naziv);

}
