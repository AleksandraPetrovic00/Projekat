package ac.rs.uns.ftn.fitnescentar.model.dto;

import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;

import java.sql.Time;

public class TerminTrDTO {

    private Long id;
    private String naziv;
    private TipTreninga tipTreninga;
    private String Opis;
    private Time vreme;
    private String oznakaSale;
    private int trajanje;
    private double cena;
    private int brojPrijavljenihClanova;

    public TerminTrDTO() {}

    public TerminTrDTO(Long id, String naziv, TipTreninga tipTreninga, String opis, Time vreme, String oznakaSale, int trajanje, double cena, int brojPrijavljenihClanova) {
        this.id = id;
        this.naziv = naziv;
        this.tipTreninga = tipTreninga;
        Opis = opis;
        this.vreme = vreme;
        this.oznakaSale = oznakaSale;
        this.trajanje = trajanje;
        this.cena = cena;
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
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

    public TipTreninga getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(TipTreninga tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public String getOznakaSale() {
        return oznakaSale;
    }

    public void setOznakaSale(String oznakaSale) {
        this.oznakaSale = oznakaSale;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getBrojPrijavljenihClanova() {
        return brojPrijavljenihClanova;
    }

    public void setBrojPrijavljenihClanova(int brojPrijavljenihClanova) {
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
    }
}
