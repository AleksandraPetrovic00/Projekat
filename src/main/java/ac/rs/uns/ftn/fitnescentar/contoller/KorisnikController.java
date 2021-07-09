package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.*;
import ac.rs.uns.ftn.fitnescentar.model.dto.*;
import ac.rs.uns.ftn.fitnescentar.service.FitnesCentarService;
import ac.rs.uns.ftn.fitnescentar.service.KorisnikService;
import ac.rs.uns.ftn.fitnescentar.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/korisnici")
public class KorisnikController {

    private final KorisnikService korisnikService;

    private final TerminService terminService;

    private final FitnesCentarService fitnesCentarService;

    @Autowired
    public KorisnikController(KorisnikService korisnikService, TerminService terminService, FitnesCentarService fitnesCentarService) {
        this.korisnikService = korisnikService;
        this.terminService = terminService;
        this.fitnesCentarService = fitnesCentarService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDTO> getKorisnik(@PathVariable("id") Long id) {

        //metoda iz servisa dobavlja fitnes centar po id-ju
        Korisnik korisnik = this.korisnikService.findOne(id);

        //pravimo objekat koji cemo vratiti kao odgovor
        KorisnikDTO korisnikDTO =  new KorisnikDTO();
        korisnikDTO.setId(korisnik.getId());
        korisnikDTO.setKorisnickoIme(korisnik.getKorisnickoIme());
        korisnikDTO.setLozinka(korisnik.getLozinka());
        korisnikDTO.setIme(korisnik.getIme());
        korisnikDTO.setPrezime(korisnik.getPrezime());
        korisnikDTO.setKontaktTelefon(korisnik.getKontaktTelefon());
        korisnikDTO.setEmailAdresa(korisnik.getEmailAdresa());
        korisnikDTO.setDatumRodjenja(korisnik.getDatumRodjenja());
        korisnikDTO.setUloga(korisnik.getUloga());
        korisnikDTO.setAktivan(korisnik.isAktivan());

        return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDTO>> getKorisnici() {

        List<Korisnik> korisnici = this.korisnikService.findAll();

        //pravimo listu koju cemo vratiti kao odgovor na zahtev
        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();

        for(Korisnik korisnik : korisnici) {
            KorisnikDTO korisnikDTO =  new KorisnikDTO(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKontaktTelefon(), korisnik.getEmailAdresa(), korisnik.getDatumRodjenja(), korisnik.getUloga(), korisnik.isAktivan());
            korisnikDTOS.add(korisnikDTO);
        }

        return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);

    }

    @GetMapping(value = "/zahtevi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDTO>> getZahtevi() {

        //dobijamo sve fitnes centre
        List<Korisnik> korisnici = this.korisnikService.findAll();

        //pravimo listu koju cemo vratiti kao odgovor na zahtev
        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();

        //kreiramo FitnesCentarDTO za svaki fitnes centar koji je pronasla metoda findAll() i ubacujemo ga u listu fitnes centara
        for(Korisnik korisnik : korisnici) {
            if(korisnik.getUloga()== Uloge.TRENER&&korisnik.isAktivan()==false) {
                KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKontaktTelefon(), korisnik.getEmailAdresa(), korisnik.getDatumRodjenja(), korisnik.getUloga(), korisnik.isAktivan());
                korisnikDTOS.add(korisnikDTO);
            }
            else{
                continue;
            }
        }

        return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);
    }

    //treneri
    @GetMapping(value = "/treneri", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDTO>> getTreneri() {

        //dobijamo sve fitnes centre
        List<Korisnik> korisnici = this.korisnikService.findAll();

        //pravimo listu koju cemo vratiti kao odgovor na zahtev
        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();

        //kreiramo FitnesCentarDTO za svaki fitnes centar koji je pronasla metoda findAll() i ubacujemo ga u listu fitnes centara
        for(Korisnik korisnik : korisnici) {
            if(korisnik.getUloga()== Uloge.TRENER) {
                KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKontaktTelefon(), korisnik.getEmailAdresa(), korisnik.getDatumRodjenja(), korisnik.getUloga(), korisnik.isAktivan());
                korisnikDTOS.add(korisnikDTO);
            }
            else{
                continue;
            }
        }

        return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);
    }

    //azuriranje statusa
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDTO> updateKorisnik(@PathVariable Long id, @RequestBody KorisnikDTO korisnikDTO) throws Exception {

        Korisnik korisnik = new Korisnik(korisnikDTO.getKorisnickoIme(), korisnikDTO.getLozinka(), korisnikDTO.getIme(), korisnikDTO.getPrezime(), korisnikDTO.getKontaktTelefon(), korisnikDTO.getEmailAdresa(), korisnikDTO.getDatumRodjenja(), korisnikDTO.getUloga(), korisnikDTO.isAktivan());

        korisnik.setId(id);

        Korisnik updatedKorisnik = korisnikService.update(korisnik);

        KorisnikDTO updatedKorisnikDTO= new KorisnikDTO(updatedKorisnik.getId(), updatedKorisnik.getKorisnickoIme(), updatedKorisnik.getLozinka(), updatedKorisnik.getIme(), updatedKorisnik.getPrezime(), updatedKorisnik.getKontaktTelefon(), updatedKorisnik.getEmailAdresa(), updatedKorisnik.getDatumRodjenja(), updatedKorisnik.getUloga(), updatedKorisnik.isAktivan());

        return new ResponseEntity<>(updatedKorisnikDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus odobriZahtev(@RequestBody List<Long> ids) throws Exception{
        for(Long id: ids){
            Korisnik korisnik = korisnikService.findOne(id);
            korisnik.setAktivan(true);
            korisnikService.update(korisnik);
        }
        return (HttpStatus.OK);
    }


    @PostMapping(value = "/registracija")
    public ResponseEntity<KorisnikDTO> register(@RequestBody KorisnikDTO korisnikDTO) throws Exception{

        Korisnik korisnik = new Korisnik(korisnikDTO.getKorisnickoIme(), korisnikDTO.getLozinka(), korisnikDTO.getIme(), korisnikDTO.getPrezime(), korisnikDTO.getKontaktTelefon(), korisnikDTO.getEmailAdresa(), korisnikDTO.getDatumRodjenja(), korisnikDTO.getUloga(), korisnikDTO.isAktivan());

        if(korisnik.getUloga()==Uloge.TRENER) {

            korisnik.setAktivan(false);
            //System.out.println(korisnik.getUloga());
            //System.out.println(korisnik.isAktivan());
        }else{
            korisnik.setAktivan(true);
            //System.out.println(korisnik.getUloga());
            //System.out.println(korisnik.isAktivan());
        }

        Korisnik newKorisnik = korisnikService.create(korisnik);

        KorisnikDTO newKorisnikDTO = new KorisnikDTO(newKorisnik.getId(), newKorisnik.getKorisnickoIme(), newKorisnik.getLozinka(), newKorisnik.getIme(), newKorisnik.getPrezime(), newKorisnik.getKontaktTelefon(), newKorisnik.getEmailAdresa(), newKorisnik.getDatumRodjenja(), newKorisnik.getUloga(), newKorisnik.isAktivan());

        //201 CREATED i podaci o novom objektu
        return new ResponseEntity<>(newKorisnikDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/registracija/{id}")
    public ResponseEntity<KorisnikRegistracijaDTO> registerTrener(@PathVariable("id")Long id, @RequestBody KorisnikRegistracijaDTO korisnikRegistracijaDTO) throws Exception{

        Korisnik korisnik = new Korisnik(korisnikRegistracijaDTO.getKorisnickoIme(), korisnikRegistracijaDTO.getLozinka(), korisnikRegistracijaDTO.getIme(), korisnikRegistracijaDTO.getPrezime(), korisnikRegistracijaDTO.getKontaktTelefon(), korisnikRegistracijaDTO.getEmailAdresa(), korisnikRegistracijaDTO.getDatumRodjenja(), korisnikRegistracijaDTO.getUloga(), korisnikRegistracijaDTO.isAktivan());

        if(korisnik.getUloga()==Uloge.TRENER) {

            korisnik.setAktivan(false);
            //System.out.println(korisnik.getUloga());
            //System.out.println(korisnik.isAktivan());
        }else{
            korisnik.setAktivan(true);
            //System.out.println(korisnik.getUloga());
            //System.out.println(korisnik.isAktivan());
        }

        FitnesCentar fitnesCentar = this.fitnesCentarService.findOne(id);
        korisnik.setFitnescentar_korisnik(fitnesCentar);
        Korisnik newKorisnik = korisnikService.create(korisnik);

        KorisnikRegistracijaDTO newKorisnikDTO = new KorisnikRegistracijaDTO(newKorisnik.getId(), newKorisnik.getKorisnickoIme(), newKorisnik.getLozinka(), newKorisnik.getIme(), newKorisnik.getPrezime(), newKorisnik.getKontaktTelefon(), newKorisnik.getEmailAdresa(), newKorisnik.getDatumRodjenja(), newKorisnik.getUloga(), newKorisnik.isAktivan(), id);

        //201 CREATED i podaci o novom objektu
        return new ResponseEntity<>(newKorisnikDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/administratorskaRegistracija/{id}")
    public ResponseEntity<KorisnikRegistracijaDTO> adminRegisterTrener(@PathVariable Long id, @RequestBody KorisnikRegistracijaDTO korisnikRegistracijaDTO) throws Exception{

        Korisnik korisnik = new Korisnik(korisnikRegistracijaDTO.getKorisnickoIme(), korisnikRegistracijaDTO.getLozinka(), korisnikRegistracijaDTO.getIme(), korisnikRegistracijaDTO.getPrezime(), korisnikRegistracijaDTO.getKontaktTelefon(), korisnikRegistracijaDTO.getEmailAdresa(), korisnikRegistracijaDTO.getDatumRodjenja(), korisnikRegistracijaDTO.getUloga(), korisnikRegistracijaDTO.isAktivan());

        korisnik.setAktivan(true);

        FitnesCentar fitnesCentar = this.fitnesCentarService.findOne(id);
        korisnik.setFitnescentar_korisnik(fitnesCentar);

        Korisnik newKorisnik = korisnikService.create(korisnik);

        KorisnikRegistracijaDTO newKorisnikDTO = new KorisnikRegistracijaDTO(newKorisnik.getId(), newKorisnik.getKorisnickoIme(), newKorisnik.getLozinka(), newKorisnik.getIme(), newKorisnik.getPrezime(), newKorisnik.getKontaktTelefon(), newKorisnik.getEmailAdresa(), newKorisnik.getDatumRodjenja(), newKorisnik.getUloga(), newKorisnik.isAktivan(), id);

        //201 CREATED i podaci o novom objektu
        return new ResponseEntity<>(newKorisnikDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/prijava")
    public ResponseEntity<KorisnikDTO> login(@RequestBody KorisnikPrijavaDTO korisnikPrijavaDTO){
        Korisnik korisnik = korisnikService.prijava(korisnikPrijavaDTO);

        if(korisnik==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKontaktTelefon(), korisnik.getEmailAdresa(), korisnik.getDatumRodjenja(), korisnik.getUloga(), korisnik.isAktivan());

            return new ResponseEntity<>(korisnikDTO, HttpStatus.CREATED);
        }
    }

    //brisanje
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteKorisnik(@PathVariable Long id) {
        // Pozivanjem metode servisa brišemo zaposlenog po ID-ju
        this.korisnikService.delete(id);

        // Vraćamo odgovor 204 NO_CONTENT koji signalizira uspešno brisanje
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //prijava treninga
    @GetMapping(value = "/prijaviTrening/{idKorisnik}/{idTermin}")
    public ResponseEntity<?> prijaviTrening(@PathVariable("idKorisnik") Long idKorisnik, @PathVariable("idTermin") Long idTermin) throws Exception{

        Korisnik korisnik = this.korisnikService.findOne(idKorisnik);

        Termin termin = this.terminService.findOne(idTermin);

        Set<Termin> prijavljeniTermini = korisnik.getPrijavljeniTermini();

        prijavljeniTermini.add(termin);
        termin.setBrojPrijavljenihClanova(termin.getBrojPrijavljenihClanova()+1);

        Date currentDate=new Date(System.currentTimeMillis());
        System.out.println(currentDate);
        if(termin.getVreme().before(currentDate)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {

            if (korisnik != null && termin.getBrojPrijavljenihClanova() < termin.getSala_termin().getKapacitet()) {
                this.korisnikService.create(korisnik);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    //prijavljeni treninzi
    @GetMapping(value = "/prijavljeniTermini/{id}")
    public ResponseEntity<List<TerminPrijavaDTO>> getPrijavljeniTreninzi(@PathVariable("id") Long id){
        Korisnik korisnik = this.korisnikService.findOne(id);
        Set<Termin> termins = korisnik.getPrijavljeniTermini();
        List<TerminPrijavaDTO> terminPrijavaDTOS = new ArrayList<>();

        for(Termin termin : termins){
            TerminPrijavaDTO terminPrijavaDTO = new TerminPrijavaDTO();
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

            terminPrijavaDTOS.add(terminPrijavaDTO);
        }

        return new ResponseEntity<>(terminPrijavaDTOS, HttpStatus.OK);
    }

    //odjava treninga
    @GetMapping(value = "/odjaviTrening/{idKorisnik}/{idTermin}")
    public ResponseEntity<?> odjaviTrening(@PathVariable("idKorisnik") Long idKorisnik, @PathVariable("idTermin") Long idTermin) throws Exception{

        Korisnik korisnik = this.korisnikService.findOne(idKorisnik);

        Termin termin = this.terminService.findOne(idTermin);

        Set<Termin> prijavljeniTermini = korisnik.getPrijavljeniTermini();

        prijavljeniTermini.remove(termin);
        termin.setBrojPrijavljenihClanova(termin.getBrojPrijavljenihClanova()-1);

        if(korisnik!=null) {
            this.korisnikService.create(korisnik);

            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //odradjeni treninzi
    @GetMapping(value = "/odradjeniTreninzi/{id}")
    public ResponseEntity<List<TerminPrijavaDTO>> getOdradjeniTreninzi(@PathVariable("id") Long id){
        Korisnik korisnik = this.korisnikService.findOne(id);
        Set<Termin> termins = korisnik.getPrijavljeniTermini();
        List<TerminPrijavaDTO> terminPrijavaDTOS = new ArrayList<>();

        Date currentDate=new Date(System.currentTimeMillis());

        System.out.println(currentDate);

        for(Termin termin : termins) {
            if (termin.getVreme().before(currentDate)) {
                TerminPrijavaDTO terminPrijavaDTO = new TerminPrijavaDTO();
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

                terminPrijavaDTOS.add(terminPrijavaDTO);
            }
        }

        return new ResponseEntity<>(terminPrijavaDTOS, HttpStatus.OK);
    }






}
