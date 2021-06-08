package ac.rs.uns.ftn.fitnescentar.model.dto;

public class KorisnikPrijavaDTO {

    private String korisnickoIme;
    private String lozinka;

    public KorisnikPrijavaDTO() {}

    public KorisnikPrijavaDTO(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
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

}
