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

    @Column(nullable = false, unique = true)
    private String oznakaSale;

    @ManyToOne(fetch = FetchType.EAGER)
    private FitnesCentar fitnescentar_sala;

    //terminska lista
    @OneToMany(mappedBy = "sala_termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> terminiSala = new HashSet<>();

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
}
