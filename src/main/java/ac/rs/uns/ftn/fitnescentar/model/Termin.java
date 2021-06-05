package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
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
    private Time vreme;

    @Column(nullable = false)
    private double cena;

    //terminska lista: TRENING - TERMIN - SALA
    @ManyToOne(fetch = FetchType.EAGER)
    private Trening treningtermin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Sala sala_termin;

    @OneToMany(mappedBy = "termin_ocena", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();

    @ManyToMany(mappedBy = "odradjeniTermini")
    private Set<Korisnik> korisnici = new HashSet<>();

    @ManyToMany(mappedBy = "prijavljeniTermini")
    private Set<Korisnik> prijavljeniKorisnici = new HashSet<>();

    public Termin() {}

    public Termin(Long id, int brojPrijavljenihClanova, Time vreme, double cena) {
        this.id = id;
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
        this.vreme = vreme;
        this.cena = cena;
    }

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

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Trening getTreningtermin() {
        return treningtermin;
    }

    public void setTreningtermin(Trening treningtermin) {
        this.treningtermin = treningtermin;
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

    public Set<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(Set<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Set<Korisnik> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public void setPrijavljeniKorisnici(Set<Korisnik> prijavljeniKorisnici) {
        this.prijavljeniKorisnici = prijavljeniKorisnici;
    }
}
