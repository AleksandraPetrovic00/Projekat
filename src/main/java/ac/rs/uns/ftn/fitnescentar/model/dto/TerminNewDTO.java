package ac.rs.uns.ftn.fitnescentar.model.dto;

import java.sql.Date;

public class TerminNewDTO {
    private Long id;
    private int brojPrijavljenihClanova;
    private double cena;
    private Date vreme;
    private Long salaTerminId;
    private Long treningTerminId;

    public TerminNewDTO(){}

    public TerminNewDTO(Long id, int brojPrijavljenihClanova, double cena, Date vreme, Long salaTerminId, Long treningTerminId) {
        this.id = id;
        this.brojPrijavljenihClanova = brojPrijavljenihClanova;
        this.cena = cena;
        this.vreme = vreme;
        this.salaTerminId = salaTerminId;
        this.treningTerminId = treningTerminId;
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public Long getSalaTerminId() {
        return salaTerminId;
    }

    public void setSalaTerminId(Long salaTerminId) {
        this.salaTerminId = salaTerminId;
    }

    public Long getTreningTerminId() {
        return treningTerminId;
    }

    public void setTreningTerminId(Long treningTerminId) {
        this.treningTerminId = treningTerminId;
    }

}


