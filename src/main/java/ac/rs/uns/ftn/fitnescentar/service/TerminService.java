package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.Trening;


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface TerminService {

    List<Termin> sviTreninzi();

    List<Termin> sviPoID();

    Termin findOne(Long id);

    List<Termin> sviZaTrenera(Long id);

    List<Termin> sviPoCeniRastce();

    List<Termin> sviPoCeniOpadajuce();

    List<Termin> sviPoVremenuRastuce();

    List<Termin> sviPoVremenuOpadajuce();

    List<Termin> pronadjiPoNazivu(String naziv);

    List<Termin> pronadjiPoTipuTreninga(TipTreninga tipTreninga);

    List<Termin> pronadjiPoOpisu(String opis);

    List<Termin> pronadjiPoCeni(double cena);

    List<Termin> pronadjiPoVremenuPosle(Date vreme);

    List<Termin> pronadjiPoCeniINazivu(double cena, String naziv);

    List<Termin> pronadjiPrijavljeneTreninge(Long id);

    Termin create(Termin termin) throws Exception;

    Termin update(Termin termin) throws Exception;

    List<Termin> pretragaPoNazivuOpisuCeniVremenu(String naziv, String opis, double cena, Date vreme);

    List<Termin> pretragaPoNazivuOpisuCeniVremenuTipu(String naziv, String opis, double cena, Date vreme, TipTreninga tipTreninga);


}
