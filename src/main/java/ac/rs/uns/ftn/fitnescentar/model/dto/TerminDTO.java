package ac.rs.uns.ftn.fitnescentar.model.dto;

public class TerminDTO {

    private Long id;
    private int brojPrijavljenihClanova;
    private double vreme;
    private double cena;

    public TerminDTO() {}

    public TerminDTO(Long id, int brojPrijavljenihClanova, double vreme, double cena) {
        this.id = id;
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
        this.vreme = vreme;
        this.cena = cena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojPrijavljenihClanova() {
        return brojPrijavljenihClanova;
    }

    public void setBrojPrijavljenihClanova(int brojPrijavljenihClanova) {
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
    }

    public double getVreme() {
        return vreme;
    }

    public void setVreme(double vreme) {
        this.vreme = vreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
