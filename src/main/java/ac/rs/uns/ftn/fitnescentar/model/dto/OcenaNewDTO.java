package ac.rs.uns.ftn.fitnescentar.model.dto;

public class OcenaNewDTO {
    private Long id;
    private int ocena;
    private Long idKorisnik;
    private Long idTermin;

    public OcenaNewDTO(){}

    public OcenaNewDTO(Long id, int ocena, Long idKorisnik, Long idTermin) {
        this.id = id;
        this.ocena = ocena;
        this.idKorisnik = idKorisnik;
        this.idTermin = idTermin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Long getIdTermin() {
        return idTermin;
    }

    public void setIdTermin(Long idTermin) {
        this.idTermin = idTermin;
    }
}
