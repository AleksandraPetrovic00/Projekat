package ac.rs.uns.ftn.fitnescentar.model.dto;

public class SalaFcDTO {
    private Long id;
    private int kapacitet;
    private String oznakaSale;
    private Long fitnesCentarId;

    public SalaFcDTO() {}

    public SalaFcDTO(Long id, int kapacitet, String oznakaSale, Long fitnesCentarId) {
        this.id = id;
        this.kapacitet = kapacitet;
        this.oznakaSale = oznakaSale;
        this.fitnesCentarId = fitnesCentarId;
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

    public Long getFitnesCentarId() {
        return fitnesCentarId;
    }

    public void setFitnesCentarId(Long fitnesCentarId) {
        this.fitnesCentarId = fitnesCentarId;
    }
}
