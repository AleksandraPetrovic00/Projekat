package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.dto.TerminTrDTO;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/termini", produces = MediaType.APPLICATION_JSON_VALUE)
public class TerminController {

    private final TerminService terminService;

    @Autowired
    public TerminController(TerminService terminService){
        this.terminService = terminService;
    }

    @GetMapping(value = "/svitreninzi")
    public ResponseEntity<List<TerminTrDTO>> getSviTreninzi(){
        List<Termin> termini = this.terminService.sviTreninzi();

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : termini) {
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());
            terminTrDTOS.add(terminTrDTO);

        }
        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/naziv/{naziv}")
    public ResponseEntity<List<TerminTrDTO>> getTreninziByNaziv(@PathVariable("naziv") String naziv) {
        List<Termin> termini = this.terminService.pronadjiPoNazivu(naziv);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin :  termini) {
            TerminTrDTO terminTrDTO =  new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/tip")
    public ResponseEntity<List<TerminTrDTO>> getTreninziByTipTreninga(@RequestParam TipTreninga tipTreninga) {
        List<Termin> termini = this.terminService.pronadjiPoTipuTreninga(tipTreninga);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin :  termini) {
            TerminTrDTO terminTrDTO =  new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/opis/{opis}")
    public ResponseEntity<List<TerminTrDTO>> getTreninziByOpis(@PathVariable("opis") String opis) {
        List<Termin> termini = this.terminService.pronadjiPoOpisu(opis);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin :  termini) {
            TerminTrDTO terminTrDTO =  new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    //terminn sa nazivom treninga i cenom(trening po ceni)
    @GetMapping(value = "/cena/{cena}")
    public ResponseEntity<List<TerminTrDTO>> getTerminiPoCeni(@PathVariable("cena") double cena){

        List<Termin> termins = this.terminService.pronadjiPoCeni(cena);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : termins) {
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/vreme/{vreme}")
    public ResponseEntity<List<TerminTrDTO>> getTerminiPoVremenuPosle(@PathVariable("vreme")Date vreme){

        List<Termin> termins = this.terminService.pronadjiPoVremenuPosle(vreme);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : termins) {
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/cenarastuce")
    public ResponseEntity<List<TerminTrDTO>> getTreninziCenaRastuce(){
        List<Termin> terminiList = this.terminService.sviPoCeniRastce();

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : terminiList){
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/cenaopadajuce")
    public ResponseEntity<List<TerminTrDTO>> getTreninziCenaOpadajuce(){
        List<Termin> terminiList = this.terminService.sviPoCeniOpadajuce();

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : terminiList){
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/vremerastuce")
    public ResponseEntity<List<TerminTrDTO>> getTreninziVremeRastuce(){
        List<Termin> terminiList = this.terminService.sviPoVremenuRastuce();

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : terminiList){
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/vremeopadajuce")
    public ResponseEntity<List<TerminTrDTO>> getTreninziVremeOpadajuce(){
        List<Termin> terminiList = this.terminService.sviPoVremenuOpadajuce();

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : terminiList){
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/nazivcena")
    public  ResponseEntity<List<TerminTrDTO>> getTerminiPoCeniINazivu(@RequestParam double cena,@RequestParam String naziv){

        List<Termin> termins = this.terminService.pronadjiPoCeniINazivu(cena, naziv);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : termins) {
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/sortiranje")
    public  ResponseEntity<List<TerminTrDTO>> getTerminiSortiraj(){

        List<Termin> termins = this.terminService.sviPoID();

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : termins) {
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }





}
