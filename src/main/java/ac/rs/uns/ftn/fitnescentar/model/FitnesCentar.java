package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FitnesCentar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @Column(nullable = false, unique = true)
    private String adresa;

    @Column(nullable = false)
    private int brojTelefonaCentrale;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "fitnescentarsala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sala> sale= new HashSet<>();

    @OneToMany(mappedBy = "fitnescentar_korisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Korisnik> treneri = new HashSet<>();

    public FitnesCentar() {}

    public FitnesCentar(String naziv, String adresa, int brojTelefonaCentrale, String email) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.brojTelefonaCentrale = brojTelefonaCentrale;
        this.email = email;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getBrojTelefonaCentrale() {
        return brojTelefonaCentrale;
    }

    public void setBrojTelefonaCentrale(int brojTelefonaCentrale) {
        this.brojTelefonaCentrale = brojTelefonaCentrale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Sala> getSale() {
        return sale;
    }

    public void setSale(Set<Sala> sale) {
        this.sale = sale;
    }

    public Set<Korisnik> getTreneri() {
        return treneri;
    }

    public void setTreneri(Set<Korisnik> treneri) {
        this.treneri = treneri;
    }

}
