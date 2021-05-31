package ac.rs.uns.ftn.fitnescentar.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ocena implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int ocena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik korisnik_ocena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Trening termin_ocena;

    public Long getId() {
        return id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
