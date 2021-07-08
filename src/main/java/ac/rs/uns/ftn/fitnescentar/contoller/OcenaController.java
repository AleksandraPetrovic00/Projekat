package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.Ocena;
import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.Uloge;
import ac.rs.uns.ftn.fitnescentar.model.dto.KorisnikDTO;
import ac.rs.uns.ftn.fitnescentar.model.dto.OcenaDTO;
import ac.rs.uns.ftn.fitnescentar.model.dto.OcenaNewDTO;
import ac.rs.uns.ftn.fitnescentar.service.KorisnikService;
import ac.rs.uns.ftn.fitnescentar.service.OcenaService;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/ocene")
public class OcenaController {

    private final OcenaService ocenaService;

    private final KorisnikService korisnikService;

    private final TerminService terminService;

    @Autowired
    public OcenaController(OcenaService ocenaService, KorisnikService korisnikService, TerminService terminService) {
        this.ocenaService = ocenaService;
        this.korisnikService = korisnikService;
        this.terminService = terminService;
    }

    @GetMapping(value = "/sveocene", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OcenaNewDTO>> getOcene(){
        List<Ocena> ocene = this.ocenaService.findAll();

        List<OcenaNewDTO> ocenaNewDTOS = new ArrayList<>();

        for(Ocena ocena : ocene){
            OcenaNewDTO ocenaNewDTO = new OcenaNewDTO(ocena.getId(), ocena.getOcena(), ocena.getKorisnik_ocena().getId(), ocena.getTermin_ocena().getId());
            ocenaNewDTOS.add(ocenaNewDTO);
        }

        return new ResponseEntity<>(ocenaNewDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/odradjeniOcenjeni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OcenaDTO>> getOdradjeniOcenjeni(@PathVariable("id") Long id) {
        Korisnik korisnik = this.korisnikService.findOne(id);
        Set<Ocena> ocene = korisnik.getOcene();
        List<OcenaDTO> ocenaDTOS = new ArrayList<>();

        for (Ocena ocena : ocene) {

            OcenaDTO ocenaDTO = new OcenaDTO();
            ocenaDTO.setId(ocena.getId());
            ocenaDTO.setNaziv(ocena.getTermin_ocena().getTreningtermin().getNaziv());
            ocenaDTO.setTipTreninga(ocena.getTermin_ocena().getTreningtermin().getTipTreninga());
            ocenaDTO.setOpis(ocena.getTermin_ocena().getTreningtermin().getOpis());
            ocenaDTO.setVreme(ocena.getTermin_ocena().getVreme());
            ocenaDTO.setOznakaSale(ocena.getTermin_ocena().getSala_termin().getOznakaSale());
            ocenaDTO.setTrajanje(ocena.getTermin_ocena().getTreningtermin().getTrajanje());
            ocenaDTO.setCena(ocena.getTermin_ocena().getCena());
            ocenaDTO.setBrojPrijavljenihClanova(ocena.getTermin_ocena().getBrojPrijavljenihClanova());
            ocenaDTO.setImeTrenera(ocena.getTermin_ocena().getTreningtermin().getKorisnik_trening().getIme());
            ocenaDTO.setPrezimeTrenera(ocena.getTermin_ocena().getTreningtermin().getKorisnik_trening().getPrezime());
            ocenaDTO.setOcena(ocena.getOcena());

            ocenaDTOS.add(ocenaDTO);
        }
        return new ResponseEntity<>(ocenaDTOS, HttpStatus.OK);

    }

    @GetMapping(value = "/odradjeniNeocenjeni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OcenaDTO>> getOdradjeniNeocenjeni(@PathVariable("id") Long id) {
        Korisnik korisnik = this.korisnikService.findOne(id);
        Set<Ocena> ocene = korisnik.getOcene();
        List<OcenaDTO> ocenaDTOS = new ArrayList<>();

        for (Ocena ocena : ocene) {
            int o = ocena.getOcena();

            if (o==0) {
                OcenaDTO ocenaDTO = new OcenaDTO();
                ocenaDTO.setId(ocena.getId());
                ocenaDTO.setNaziv(ocena.getTermin_ocena().getTreningtermin().getNaziv());
                ocenaDTO.setTipTreninga(ocena.getTermin_ocena().getTreningtermin().getTipTreninga());
                ocenaDTO.setOpis(ocena.getTermin_ocena().getTreningtermin().getOpis());
                ocenaDTO.setVreme(ocena.getTermin_ocena().getVreme());
                ocenaDTO.setOznakaSale(ocena.getTermin_ocena().getSala_termin().getOznakaSale());
                ocenaDTO.setTrajanje(ocena.getTermin_ocena().getTreningtermin().getTrajanje());
                ocenaDTO.setCena(ocena.getTermin_ocena().getCena());
                ocenaDTO.setBrojPrijavljenihClanova(ocena.getTermin_ocena().getBrojPrijavljenihClanova());
                ocenaDTO.setImeTrenera(ocena.getTermin_ocena().getTreningtermin().getKorisnik_trening().getIme());
                ocenaDTO.setPrezimeTrenera(ocena.getTermin_ocena().getTreningtermin().getKorisnik_trening().getPrezime());
                ocenaDTOS.add(ocenaDTO);

            }

        }
        return new ResponseEntity<>(ocenaDTOS, HttpStatus.OK);
    }

    //oceni trening
    @PostMapping(value = "/oceniTrening/{idKorisnika}/{idTermina}")
    public ResponseEntity<OcenaNewDTO> oceniTrening(@RequestBody OcenaNewDTO ocenaNewDTO, @PathVariable("idKorisnika") Long idKorisnika, @PathVariable("idTermina")Long idTermina) throws Exception{

        Korisnik korisnik = this.korisnikService.findOne(idKorisnika);

        Termin termin = this.terminService.findOne(idTermina);

        Ocena ocena = new Ocena();
        ocena.setId(ocenaNewDTO.getId());
        ocena.setOcena(ocenaNewDTO.getOcena());
        ocena.setKorisnik_ocena(korisnik);
        ocena.setTermin_ocena(termin);

        Ocena newOcena = ocenaService.create(ocena);

        OcenaNewDTO newOcenaNewDTO = new OcenaNewDTO();
        newOcenaNewDTO.setId(newOcena.getId());
        newOcenaNewDTO.setOcena(newOcena.getOcena());
        newOcenaNewDTO.setIdKorisnik(newOcena.getKorisnik_ocena().getId());
        newOcenaNewDTO.setIdTermin(newOcena.getTermin_ocena().getId());

        return new ResponseEntity<>(newOcenaNewDTO, HttpStatus.CREATED);
    }

}


