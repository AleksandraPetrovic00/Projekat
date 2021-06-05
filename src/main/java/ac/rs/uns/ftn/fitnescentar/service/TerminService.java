package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Termin;

import java.sql.Time;
import java.util.List;

public interface TerminService {

    List<Termin> sviPoCeniRastce();

    List<Termin> sviPoCeniOpadajuce();

    List<Termin> sviPoVremenuRastuce();

    List<Termin> sviPoVremenuOpadajuce();

    List<Termin> pronadjiPoCeni(double cena);

    List<Termin> pronadjiPoVremenuPosle(Time vreme);

    List<Termin> pronadjiPoCeniINazivu(double cena, String naziv);

}
