package ac.rs.uns.ftn.fitnescentar.model.dto;

import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;

public class TreningDTO {

    private Long id;
    private String naziv;
    private String opis;
    private TipTreninga tipTreninga;
    private int trajanje;

    public TreningDTO() {}

    public TreningDTO(Long id, String naziv, String opis, TipTreninga tipTreninga, int trajanje) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.tipTreninga = tipTreninga;
        this.trajanje = trajanje;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipTreninga getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(TipTreninga tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }
}
