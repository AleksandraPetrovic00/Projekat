package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.Uloge;
import ac.rs.uns.ftn.fitnescentar.model.dto.KorisnikDTO;
import ac.rs.uns.ftn.fitnescentar.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/korisnici")
public class KorisnikController {

    private final KorisnikService korisnikService;

    @Autowired
    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
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

        //dobijamo sve fitnes centre
        List<Korisnik> korisnici = this.korisnikService.findAll();

        //pravimo listu koju cemo vratiti kao odgovor na zahtev
        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();

        //kreiramo FitnesCentarDTO za svaki fitnes centar koji je pronasla metoda findAll() i ubacujemo ga u listu fitnes centara
        for(Korisnik korisnik : korisnici) {
            KorisnikDTO korisnikDTO =  new KorisnikDTO(korisnik.getId(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getKontaktTelefon(), korisnik.getEmailAdresa(), korisnik.getDatumRodjenja(), korisnik.getUloga(), korisnik.isAktivan());
            korisnikDTOS.add(korisnikDTO);
        }

        return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);

    }

    @GetMapping(value = "zahtevi", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "/registracija")
    public ResponseEntity<KorisnikDTO> register(@RequestBody KorisnikDTO korisnikDTO) throws Exception {

        Korisnik korisnik = new Korisnik(korisnikDTO.getKorisnickoIme(), korisnikDTO.getLozinka(), korisnikDTO.getIme(), korisnikDTO.getPrezime(), korisnikDTO.getKontaktTelefon(), korisnikDTO.getEmailAdresa(), korisnikDTO.getDatumRodjenja(), korisnikDTO.getUloga(), korisnikDTO.isAktivan());

        Korisnik newKorisnik = korisnikService.create(korisnik);

        KorisnikDTO newKorisnikDTO = new KorisnikDTO(newKorisnik.getId(), newKorisnik.getKorisnickoIme(), newKorisnik.getLozinka(), newKorisnik.getIme(), newKorisnik.getPrezime(), newKorisnik.getKontaktTelefon(), newKorisnik.getEmailAdresa(), newKorisnik.getDatumRodjenja(), newKorisnik.getUloga(), newKorisnik.isAktivan());

        //201 CREATED i podaci o novom objektu
        return new ResponseEntity<>(newKorisnikDTO, HttpStatus.CREATED);
    }


    //brisanje
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteKorisnik(@PathVariable Long id) {
        // Pozivanjem metode servisa brišemo zaposlenog po ID-ju
        this.korisnikService.delete(id);

        // Vraćamo odgovor 204 NO_CONTENT koji signalizira uspešno brisanje
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
