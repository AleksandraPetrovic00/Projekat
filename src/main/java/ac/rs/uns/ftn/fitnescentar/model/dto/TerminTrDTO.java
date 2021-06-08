package ac.rs.uns.ftn.fitnescentar.model.dto;

import java.sql.Time;

public class TerminTrDTO {

    private Long id;
    private String naziv;
    private int brojPrijavljenihClanova;
    private Time vreme;
    private double cena;
    private String opis;
    private String sala;

    public TerminTrDTO() {}

    public TerminTrDTO(Long id, String naziv, int brojPrijavljenihClanova, Time vreme, double cena, String opis, String sala) {
        this.id = id;
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
        this.vreme = vreme;
        this.cena = cena;
        this.naziv = naziv;
        this.opis = opis;
        this.sala = sala;
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

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
