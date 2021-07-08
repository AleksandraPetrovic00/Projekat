package ac.rs.uns.ftn.fitnescentar.model.dto;

import ac.rs.uns.ftn.fitnescentar.model.Uloge;

public class PristupDTO {
    Long id;
    Uloge uloga;

    public PristupDTO(){}

    public PristupDTO(Long id, Uloge uloga) {
        this.id = id;
        this.uloga = uloga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Uloge getUloga() {
        return uloga;
    }

    public void setUloga(Uloge uloga) {
        this.uloga = uloga;
    }
}
