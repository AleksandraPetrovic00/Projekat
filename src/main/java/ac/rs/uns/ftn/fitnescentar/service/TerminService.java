package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;

import java.sql.Time;
import java.util.List;

public interface TerminService {

    List<Termin> findAll();

    List<Termin> sviPoCeniRastce();

    List<Termin> sviPoCeniOpadajuce();

    List<Termin> sviPoVremenuRastuce();

    List<Termin> sviPoVremenuOpadajuce();

    List<Termin> pronadjiPoNazivu(String naziv);

    List<Termin> pronadjiPoTipuTreninga(TipTreninga tipTreninga);

    List<Termin> pronadjiPoOpisu(String opis);

    List<Termin> pronadjiPoCeni(double cena);

    List<Termin> pronadjiPoVremenuPosle(Time vreme);

    List<Termin> pronadjiPoCeniINazivu(double cena, String naziv);

}
