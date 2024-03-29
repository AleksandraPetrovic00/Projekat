package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    private TipTreninga tipTreninga;

    @Column(nullable = false)
    private int trajanje;

    public Trening() {}

    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik korisniktrening;

    //terminska lista
    @OneToMany(mappedBy = "treningtermin", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Termin> terminiTreninga = new HashSet<>();

    public Trening(String naziv, String opis, TipTreninga tipTreninga, int trajanje) {
        this.naziv = naziv;
        this.opis = opis;
        this.tipTreninga = tipTreninga;
        this.trajanje = trajanje;
    }

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

    public TipTreninga getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(TipTreninga tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Korisnik getKorisniktrening() {
        return korisniktrening;
    }

    public void setKorisniktrening(Korisnik korisnik_trening) {
        this.korisniktrening = korisnik_trening;
    }

    public Set<Termin> getTerminiTreninga() {
        return terminiTreninga;
    }

    public void setTerminiTreninga(Set<Termin> terminiTreninga) {
        this.terminiTreninga = terminiTreninga;
    }

    @Override
    public String toString() {
        return "Trening{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", tipTreninga=" + tipTreninga +
                ", trajanje=" + trajanje +
                ", korisnik_trening=" + korisniktrening +
                ", terminiTreninga=" + terminiTreninga +
                '}';
    }
}
