package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String korisnickoIme;

    @Column(nullable = false)
    private String lozinka;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @Column(nullable = false)
    private String kontaktTelefon;

    @Column(nullable = false, unique = true)
    private String emailAdresa;

    @Column(nullable = false)
    private String datumRodjenja;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Uloge uloga;

    @Column(nullable = false)
    private boolean aktivan;

    public Korisnik() {}

    public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTelefon, String emailAdresa, String datumRodjenja, Uloge uloga, boolean aktivan) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTelefon = kontaktTelefon;
        this.emailAdresa = emailAdresa;
        this.datumRodjenja = datumRodjenja;
        this.uloga = uloga;
        this.aktivan = aktivan;
    }

    @OneToMany(mappedBy = "korisniktrening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Trening> treninzi = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private FitnesCentar fitnescentar_korisnik;

    @OneToMany(mappedBy = "korisnik_ocena", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();


    //odradjeni treninzi i prijavljeni treninzi
    @ManyToMany
    @JoinTable(name = "odradjeniTreninzi",
            joinColumns = @JoinColumn(name = "korisnik_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "termin_id", referencedColumnName = "id"))
    private Set<Termin> odradjeniTermini = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "prijavljeniTreninzi",
            joinColumns = @JoinColumn(name = "korisnik_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "termin_id", referencedColumnName = "id"))
    private Set<Termin> prijavljeniTermini = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getEmailAdresa() {
        return emailAdresa;
    }

    public void setEmailAdresa(String emailAdresa) {
        this.emailAdresa = emailAdresa;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Uloge getUloga() {
        return uloga;
    }

    public void setUloga(Uloge uloga) {
        this.uloga = uloga;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public Set<Trening> getTreninzi() {
        return treninzi;
    }

    public void setTreninzi(Set<Trening> treninzi) {
        this.treninzi = treninzi;
    }

    public FitnesCentar getFitnescentar_korisnik() {
        return fitnescentar_korisnik;
    }

    public void setFitnescentar_korisnik(FitnesCentar fitnescentar_korisnik) {
        this.fitnescentar_korisnik = fitnescentar_korisnik;
    }

    public Set<Ocena> getOcene() {
        return ocene;
    }

    public void setOcene(Set<Ocena> ocene) {
        this.ocene = ocene;
    }

    public Set<Termin> getOdradjeniTermini() {
        return odradjeniTermini;
    }

    public void setOdradjeniTermini(Set<Termin> odradjeniTermini) {
        this.odradjeniTermini = odradjeniTermini;
    }

    public Set<Termin> getPrijavljeniTermini() {
        return prijavljeniTermini;
    }

    public void setPrijavljeniTermini(Set<Termin> prijavljeniTermini) {
        this.prijavljeniTermini = prijavljeniTermini;
    }
}