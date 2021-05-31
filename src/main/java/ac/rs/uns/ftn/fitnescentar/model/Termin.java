package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Termin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int brojPrijavljenihClanova;

    @Column
    private String vremeOdrzavanja;

    @Column(nullable = false)
    private double cena;

    //terminska lista: TRENING - TERMIN - SALA
    @ManyToOne(fetch = FetchType.EAGER)
    private Trening trening_termin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Sala sala_termin;

    @OneToMany(mappedBy = "termin_ocena", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();

    @OneToMany(mappedBy = "odradjeniTrening_termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OdradjeniTrening> odradjeniTermini = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojPrijavljenihClanova() {
        return brojPrijavljenihClanova;
    }

    public void setBrojPrijavljenihClanova(int brojPrijavljenihClanova) {
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
    }

    public String getVremeOdrzavanja() {
        return vremeOdrzavanja;
    }

    public void setVremeOdrzavanja(String vremeOdrzavanja) {
        this.vremeOdrzavanja = vremeOdrzavanja;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Trening getTrening_termin() {
        return trening_termin;
    }

    public void setTrening_termin(Trening trening_termin) {
        this.trening_termin = trening_termin;
    }

    public Sala getSala_termin() {
        return sala_termin;
    }

    public void setSala_termin(Sala sala_termin) {
        this.sala_termin = sala_termin;
    }

    public Set<Ocena> getOcene() {
        return ocene;
    }

    public void setOcene(Set<Ocena> ocene) {
        this.ocene = ocene;
    }

    public Set<OdradjeniTrening> getOdradjeniTermini() {
        return odradjeniTermini;
    }

    public void setOdradjeniTermini(Set<OdradjeniTrening> odradjeniTermini) {
        this.odradjeniTermini = odradjeniTermini;
    }
}
