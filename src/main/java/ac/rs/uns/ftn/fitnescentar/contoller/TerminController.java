package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.dto.TerminDTO;
import ac.rs.uns.ftn.fitnescentar.model.dto.TerminTrDTO;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/termini", produces = MediaType.APPLICATION_JSON_VALUE)
public class TerminController {

    private final TerminService terminService;

    @Autowired
    public TerminController(TerminService terminService){
        this.terminService = terminService;
    }

    //terminn sa nazivom treninga i cenom(trening po ceni)
    @GetMapping(value = "/cena/{cena}")
    public ResponseEntity<List<TerminDTO>> getTerminiPoCeni(@PathVariable("cena") double cena){

        List<Termin> termins = this.terminService.pronadjiPoCeni(cena);

        List<TerminDTO> terminDTOS = new ArrayList<>();

        for(Termin termin : termins) {
            TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getBrojPrijavljenihClanova(), termin.getVreme(), termin.getCena());

            terminDTOS.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/vreme/{vreme}")
    public ResponseEntity<List<TerminDTO>> getTerminiPoVremenuPosle(@PathVariable("vreme") double vreme){

        List<Termin> termins = this.terminService.pronadjiPoVremenuPosle(vreme);

        List<TerminDTO> terminDTOS = new ArrayList<>();

        for(Termin termin : termins) {
            TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getBrojPrijavljenihClanova(), termin.getVreme(), termin.getCena());

            terminDTOS.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
    }

    //??
    /*@GetMapping(value = "/nazivcena/{cena}{naziv}")
    public  ResponseEntity<List<TerminTrDTO>> getTerminiPoCeniINazivu(@PathVariable("cena")double cena, String naziv){

        List<Termin> termins = this.terminService.pronadjiPoCeniINazivu(cena, naziv);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : termins) {
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getBrojPrijavljenihClanova(),
                    termin.getVreme(), termin.getCena(), naziv);

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }*/





}
