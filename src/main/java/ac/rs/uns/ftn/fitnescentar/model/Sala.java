package ac.rs.uns.ftn.fitnescentar.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int kapacitet;

    @Column(unique = true)
    private String oznakaSale;

    @ManyToOne(fetch = FetchType.EAGER)
    private FitnesCentar fitnescentarsala;

    //terminska lista
    @OneToMany(mappedBy = "sala_termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> terminiSala = new HashSet<>();

    public Sala(int kapacitet, String oznakaSale) {
        this.kapacitet=kapacitet;
        this.oznakaSale=oznakaSale;
    }

    public Sala() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public String getOznakaSale() {
        return oznakaSale;
    }

    public void setOznakaSale(String oznakaSale) {
        this.oznakaSale = oznakaSale;
    }

    public FitnesCentar getFitnescentarsala() {
        return fitnescentarsala;
    }

    public void setFitnescentarsala(FitnesCentar fitnescentarsala) {
        this.fitnescentarsala = fitnescentarsala;
    }

    public Set<Termin> getTerminiSala() {
        return terminiSala;
    }

    public void setTerminiSala(Set<Termin> terminiSala) {
        this.terminiSala = terminiSala;
    }
}
