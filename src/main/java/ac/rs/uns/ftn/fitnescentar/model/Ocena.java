package ac.rs.uns.ftn.fitnescentar.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ocena implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int ocena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik korisnik_ocena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Termin terminocena;

    public Ocena(){}

    public Long getId() {
        return id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik_ocena() {
        return korisnik_ocena;
    }

    public void setKorisnik_ocena(Korisnik korisnik_ocena) {
        this.korisnik_ocena = korisnik_ocena;
    }

    public Termin getTermin_ocena() {
        return terminocena;
    }

    public void setTermin_ocena(Termin terminocena) {
        this.terminocena = terminocena;
    }
}
