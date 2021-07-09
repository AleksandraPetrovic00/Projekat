package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.*;
import ac.rs.uns.ftn.fitnescentar.model.dto.*;
import ac.rs.uns.ftn.fitnescentar.service.*;
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
    private final KorisnikService korisnikService;
    private final TreningService treningService;
    private final SalaService salaService;

    @Autowired
    public TerminController(TerminService terminService, KorisnikService korisnikService, TreningService treningService, SalaService salaService){
        this.terminService = terminService;
        this.korisnikService = korisnikService;
        this.treningService = treningService;
        this.salaService = salaService;
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

    @GetMapping(value = "/trener/{idTrener}")
    public ResponseEntity<List<TerminTrDTO>> getSviZaTrenera(@PathVariable("idTrener")Long idTrener){
        List<Termin> termini = this.terminService.sviZaTrenera(idTrener);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin : termini){
            TerminTrDTO terminTrDTO = new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());
            terminTrDTOS.add(terminTrDTO);
        }
        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminPrijavaDTO> getTermin(@PathVariable("id") Long id) {

        Termin termin = this.terminService.findOne(id);

        TerminPrijavaDTO terminPrijavaDTO =  new TerminPrijavaDTO();
        terminPrijavaDTO.setId(termin.getId());
        terminPrijavaDTO.setNaziv(termin.getTreningtermin().getNaziv());
        terminPrijavaDTO.setTipTreninga(termin.getTreningtermin().getTipTreninga());
        terminPrijavaDTO.setOpis(termin.getTreningtermin().getOpis());
        terminPrijavaDTO.setVreme(termin.getVreme());
        terminPrijavaDTO.setOznakaSale(termin.getSala_termin().getOznakaSale());
        terminPrijavaDTO.setTrajanje(termin.getTreningtermin().getTrajanje());
        terminPrijavaDTO.setCena(termin.getCena());
        terminPrijavaDTO.setBrojPrijavljenihClanova(termin.getBrojPrijavljenihClanova());
        terminPrijavaDTO.setImeTrenera(termin.getTreningtermin().getKorisniktrening().getIme());
        terminPrijavaDTO.setPrezimeTrenera(termin.getTreningtermin().getKorisniktrening().getPrezime());
        return new ResponseEntity<>(terminPrijavaDTO, HttpStatus.OK);

    }

    @PostMapping(value = "/dodaj/{idTrening}/{idSala}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminNewDTO> createTermin(@PathVariable("idTrening") Long idTrening, @PathVariable("idSala")Long idSala, @RequestBody TerminNewDTO terminNewDTO) throws Exception {

        Termin termin = new Termin(terminNewDTO.getBrojPrijavljenihClanova(), terminNewDTO.getVreme(), terminNewDTO.getCena());

        Trening trening = treningService.findOne(idTrening);
        Sala sala = salaService.findOne(idSala);

        termin.setTreningtermin(trening);
        termin.setSala_termin(sala);

        Termin newTermin = terminService.create(termin);

        TerminNewDTO terminNewDTO1 = new TerminNewDTO(newTermin.getId(), newTermin.getBrojPrijavljenihClanova(), newTermin.getCena(), newTermin.getVreme(), idSala, idTrening);

        return new ResponseEntity<>(terminNewDTO1, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{idTermin}/{idSala}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminUpdateDTO> updateTermin(@PathVariable("idTermin") Long idTermin, @PathVariable("idSala")Long idSala, @RequestBody TerminUpdateDTO terminUpdateDTO) throws Exception {

        Termin termin = new Termin(terminUpdateDTO.getBrojPrijavljenihClanova(), terminUpdateDTO.getVreme(), terminUpdateDTO.getCena());

        termin.setId(idTermin);
            Sala sala = salaService.findOne(idSala);
            termin.setSala_termin(sala);

            Termin updatedTermin = terminService.update(termin);

            TerminUpdateDTO terminUpdateDTO1 = new TerminUpdateDTO(updatedTermin.getId(), updatedTermin.getBrojPrijavljenihClanova(), updatedTermin.getCena(), updatedTermin.getVreme(), idSala);

            if(terminUpdateDTO1.getBrojPrijavljenihClanova()==0) {
                return new ResponseEntity<>(terminUpdateDTO1, HttpStatus.OK);
            }else{
                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
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

    @GetMapping(value="/naziv/{naziv}/{opis}/{cena}/{vreme}")
    public ResponseEntity<List<TerminTrDTO>> getTreninziByNazivOpisCenaVreme(@PathVariable("naziv") String naziv, @PathVariable("opis")String opis, @PathVariable("cena")double cena, @PathVariable("vreme")Date vreme) {
        List<Termin> termini = this.terminService.pretragaPoNazivuOpisuCeniVremenu(naziv, opis, cena, vreme);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin :  termini) {
            TerminTrDTO terminTrDTO =  new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/naziv/{naziv}/{opis}/{cena}/{vreme}/tip")
    public ResponseEntity<List<TerminTrDTO>> getTreninziByNazivOpisCenaVreme(@PathVariable("naziv") String naziv, @PathVariable("opis")String opis, @PathVariable("cena")double cena, @PathVariable("vreme")Date vreme, @RequestParam TipTreninga tipTreninga) {
        List<Termin> termini = this.terminService.pretragaPoNazivuOpisuCeniVremenuTipu(naziv, opis, cena, vreme, tipTreninga);

        List<TerminTrDTO> terminTrDTOS = new ArrayList<>();

        for(Termin termin :  termini) {
            TerminTrDTO terminTrDTO =  new TerminTrDTO(termin.getId(), termin.getTreningtermin().getNaziv(), termin.getTreningtermin().getTipTreninga(), termin.getTreningtermin().getOpis(), termin.getVreme(), termin.getSala_termin().getOznakaSale(), termin.getTreningtermin().getTrajanje(), termin.getCena(), termin.getBrojPrijavljenihClanova());

            terminTrDTOS.add(terminTrDTO);
        }

        return new ResponseEntity<>(terminTrDTOS, HttpStatus.OK);
    }

}
