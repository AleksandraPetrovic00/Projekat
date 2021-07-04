package ac.rs.uns.ftn.fitnescentar.model.dto;

public class SalaDTO {
    Long id;
    int kapacitet;
    String oznakaSale;

    public SalaDTO() {}

    public SalaDTO(Long id, int kapacitet, String oznakaSale) {
        this.id = id;
        this.kapacitet = kapacitet;
        this.oznakaSale = oznakaSale;
    }

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
