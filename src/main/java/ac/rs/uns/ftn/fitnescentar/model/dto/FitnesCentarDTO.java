package ac.rs.uns.ftn.fitnescentar.model.dto;

public class FitnesCentarDTO {

    private Long id;
    private String naziv;
    private String adresa;
    private int brojTelefonaCentrale;
    private String email;

    public FitnesCentarDTO() {
    }

    public FitnesCentarDTO(Long id, String naziv, String adresa, int brojTelefonaCentrale, String email) {
        this.id=id;
        this.naziv=naziv;
        this.adresa=adresa;
        this.brojTelefonaCentrale=brojTelefonaCentrale;
        this.email=email;
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
}
