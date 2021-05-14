package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

enum Tip{HIIT, CORE, YOGA}

@Entity
public class Trening implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @Column(nullable = false)
    private String opis;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tip tipTreninga;

    @Column(nullable = false)
    private int trajanje;

    @Column(nullable = false)
    private double cena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik korisnik_trening;

    @OneToMany(mappedBy = "trening_ocena", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> oceneTreninga = new HashSet<>();

    //terminska lista
    @OneToMany(mappedBy = "trening_termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> terminiTreninga = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Tip getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(Tip tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Korisnik getKorisnik_trening() {
        return korisnik_trening;
    }

    public void setKorisnik_trening(Korisnik korisnik_trening) {
        this.korisnik_trening = korisnik_trening;
    }

    public Set<Ocena> getOceneTreninga() {
        return oceneTreninga;
    }

    public void setOceneTreninga(Set<Ocena> oceneTreninga) {
        this.oceneTreninga = oceneTreninga;
    }

    public Set<Termin> getTerminiTreninga() {
        return terminiTreninga;
    }

    public void setTerminiTreninga(Set<Termin> terminiTreninga) {
        this.terminiTreninga = terminiTreninga;
    }
}
