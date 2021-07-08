package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.Ocena;
import ac.rs.uns.ftn.fitnescentar.model.Termin;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.dto.*;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import ac.rs.uns.ftn.fitnescentar.service.KorisnikService;
import ac.rs.uns.ftn.fitnescentar.service.TreningService;
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

    @Autowired
    public TerminController(TerminService terminService, KorisnikService korisnikService, TreningService treningService){
        this.terminService = terminService;
        this.korisnikService = korisnikService;
        this.treningService = treningService;
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
        terminPrijavaDTO.setImeTrenera(termin.getTreningtermin().getKorisnik_trening().getIme());
        terminPrijavaDTO.setPrezimeTrenera(termin.getTreningtermin().getKorisnik_trening().getPrezime());
        return new ResponseEntity<>(terminPrijavaDTO, HttpStatus.OK);

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

    /*@PostMapping(value = "/noviTermin/{idTrenera}/{idTermina}")
    public ResponseEntity<OcenaNewDTO> oceniTrening(@RequestBody OcenaNewDTO ocenaNewDTO, @PathVariable("idTrenera") Long idTrenera, @PathVariable("idTermina")Long idTermina) throws Exception{

        Korisnik korisnik = this.korisnikService.findOne(idTrenera);

        Termin termin = this.terminService.findOne(idTermina);

        Termin termin1 = new Ocena();
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
    }*/

}
