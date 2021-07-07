package ac.rs.uns.ftn.fitnescentar.model.dto;

public class PrijavljeniDTO {
    private Long id;
    private Long terminId;

    public PrijavljeniDTO() {}

    public PrijavljeniDTO(Long id, Long terminId) {
        this.id = id;
        this.terminId = terminId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTerminId() {
        return terminId;
    }

    public void setTerminId(Long terminId) {
        this.terminId = terminId;
    }
}
