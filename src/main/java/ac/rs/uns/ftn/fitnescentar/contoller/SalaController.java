package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Sala;
import ac.rs.uns.ftn.fitnescentar.model.dto.SalaDTO;
import ac.rs.uns.ftn.fitnescentar.model.dto.SalaFcDTO;
import ac.rs.uns.ftn.fitnescentar.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/sale")
public class SalaController {

    private final SalaService salaService;

    @Autowired
    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalaDTO> getSala(@PathVariable("id") Long id) {

        Sala sala = this.salaService.findOne(id);

        SalaDTO salaDTO =  new SalaDTO();
        salaDTO.setId(sala.getId());
        salaDTO.setKapacitet(sala.getKapacitet());
        salaDTO.setOznakaSale(sala.getOznakaSale());

        return new ResponseEntity<>(salaDTO, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalaDTO>> getSale() {

        List<Sala> salaList = this.salaService.findAll();

        List<SalaDTO> salaDTOS = new ArrayList<>();

        for(Sala sala : salaList) {
            SalaDTO salaDTO =  new SalaDTO(sala.getId(), sala.getKapacitet(), sala.getOznakaSale());
            salaDTOS.add(salaDTO);
        }

        return new ResponseEntity<>(salaDTOS, HttpStatus.OK);

    }

    @GetMapping(value = "/fitnesCentarSale/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalaDTO>> getSaleZaFitnesCentar(@PathVariable("id") Long id) {

        List<Sala> salaList = this.salaService.pronadjiSveZaFitnesCentar(id);

        List<SalaDTO> salaDTOS = new ArrayList<>();

        for (Sala sala : salaList) {
            SalaDTO salaDTO = new SalaDTO(sala.getId(), sala.getKapacitet(), sala.getOznakaSale());
            salaDTOS.add(salaDTO);
        }

        return  new ResponseEntity<>(salaDTOS, HttpStatus.OK);
    }

    /////isparvi
    @PostMapping(value = "/dodaj/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalaFcDTO> createSala(@PathVariable Long id, @RequestBody SalaFcDTO salaFcDTO) throws Exception {

        Sala sala = new Sala(salaFcDTO.getKapacitet(), salaFcDTO.getOznakaSale());

        Sala newSala = salaService.create(sala);

        SalaFcDTO newSalaFcDTO = new SalaFcDTO(newSala.getId(), newSala.getKapacitet(), newSala.getOznakaSale(), newSala.getFitnescentarsala().getId());

        return new ResponseEntity<>(newSalaFcDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalaDTO> updateSala(@PathVariable Long id, @RequestBody SalaDTO salaDTO) throws Exception {

        Sala sala = new Sala(salaDTO.getKapacitet(), salaDTO.getOznakaSale());

        sala.setId(id);

        Sala updatedSala = salaService.update(sala);

        SalaDTO updatedSalaDTO= new SalaDTO(updatedSala.getId(), updatedSala.getKapacitet(), updatedSala.getOznakaSale());

        return new ResponseEntity<>(updatedSalaDTO, HttpStatus.OK);
    }

    //brisanje
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id) {

        this.salaService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
