package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OdradjeniTrening implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Termin odradjeniTrening_termin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik odradjeniTrening_clan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Termin getOdradjeniTrening_termin() {
        return odradjeniTrening_termin;
    }

    public void setOdradjeniTrening_termin(Termin odradjeniTrening_termin) {
        this.odradjeniTrening_termin = odradjeniTrening_termin;
    }

    public Korisnik getOdradjeniTrening_clan() {
        return odradjeniTrening_clan;
    }

    public void setOdradjeniTrening_clan(Korisnik odradjeniTrening_clan) {
        this.odradjeniTrening_clan = odradjeniTrening_clan;
    }
}
