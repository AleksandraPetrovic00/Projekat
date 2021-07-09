package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.Sala;
import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.Trening;
import ac.rs.uns.ftn.fitnescentar.model.dto.SalaDTO;
import ac.rs.uns.ftn.fitnescentar.model.dto.TreningDTO;
import ac.rs.uns.ftn.fitnescentar.model.dto.TreningNewDTO;
import ac.rs.uns.ftn.fitnescentar.service.KorisnikService;
import ac.rs.uns.ftn.fitnescentar.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/treninzi", produces = MediaType.APPLICATION_JSON_VALUE)
public class TreningController {

    private final TreningService treningService;
    private final KorisnikService korisnikService;

    @Autowired
    public TreningController(TreningService treningService, KorisnikService korisnikService){
        this.treningService = treningService;
        this.korisnikService = korisnikService;
    }

    //jedan trening
    @GetMapping(value = "/{id}")
    public ResponseEntity<TreningDTO> getTrening(@PathVariable("id") Long id) {

        Trening trening = this.treningService.findOne(id);

        TreningDTO treningDTO =  new TreningDTO();
        treningDTO.setId(trening.getId());
        treningDTO.setNaziv(trening.getNaziv());
        treningDTO.setOpis(trening.getOpis());
        treningDTO.setTipTreninga(trening.getTipTreninga());
        treningDTO.setTrajanje(trening.getTrajanje());

        return new ResponseEntity<>(treningDTO, HttpStatus.OK);
    }

    //svi treninzi
    @GetMapping
    public ResponseEntity<List<TreningDTO>> getTreninzi(){
        List<Trening> treninziList = this.treningService.findAll();

        List<TreningDTO> treninziDTOS = new ArrayList<>();

        for(Trening trening : treninziList) {
            TreningDTO treningDTO = new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());
            treninziDTOS.add(treningDTO);
        }

        return new ResponseEntity<>(treninziDTOS, HttpStatus.OK);
    }

    //svi treninzi nekog trenera
    @GetMapping(value = "/trener/{id}")
    public ResponseEntity<List<TreningDTO>> getTreninziByTrener(@PathVariable("id")Long id){
        List<Trening> treninzi = this.treningService.pretragaPoIdTrenera(id);

        List<TreningDTO> treningDTOS = new ArrayList<>();

        for(Trening trening : treninzi){
            TreningDTO treningDTO = new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());

            treningDTOS.add(treningDTO);
        }
        return new ResponseEntity<>(treningDTOS, HttpStatus.OK);
    }

    //pretraga po nazivu
    @GetMapping(value="/naziv/{naziv}")
    public ResponseEntity<List<TreningDTO>> getTreninziByNaziv(@PathVariable("naziv") String naziv) {
        List<Trening> treninziListaNaziv = this.treningService.pretragaPoNazivu(naziv);

        List<TreningDTO> treninziNazivDTOS = new ArrayList<>();

        for(Trening trening :  treninziListaNaziv) {
            TreningDTO treningDTO =  new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());

            treninziNazivDTOS.add(treningDTO);
        }

        return new ResponseEntity<>(treninziNazivDTOS, HttpStatus.OK);
    }

    //pretraga po tipu
    @GetMapping(value="/tip/{tipTreninga}")
    public ResponseEntity<List<TreningDTO>> getTreninziByTipTreninga(@PathVariable("tipTreninga") TipTreninga tipTreninga) {
        List<Trening> treninziListaTip = this.treningService.pretragaPoTipu(tipTreninga);

        List<TreningDTO> treninziTipDTOS = new ArrayList<>();

        for(Trening trening :  treninziListaTip) {
            TreningDTO treningDTO =  new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());

            treninziTipDTOS.add(treningDTO);
        }

        return new ResponseEntity<>(treninziTipDTOS, HttpStatus.OK);
    }

    //pretraga po opisu
    @GetMapping(value="/opis/{opis}")
    public ResponseEntity<List<TreningDTO>> getTreninziByOpis(@PathVariable("opis") String opis) {
        List<Trening> treninziListaOpis = this.treningService.pretragaPoOpisu(opis);

        List<TreningDTO> treninziOpisDTOS = new ArrayList<>();

        for(Trening trening :  treninziListaOpis) {
            TreningDTO treningDTO =  new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());

            treninziOpisDTOS.add(treningDTO);
        }

        return new ResponseEntity<>(treninziOpisDTOS, HttpStatus.OK);
    }

    //novi trening
    @PostMapping(value = "/dodaj/{idTrenera}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreningNewDTO> createTrening(@PathVariable("idTrenera")Long idTrenera, @RequestBody TreningDTO treningDTO) throws Exception {

        Trening trening = new Trening(treningDTO.getNaziv(), treningDTO.getOpis(), treningDTO.getTipTreninga(), treningDTO.getTrajanje());
        Korisnik korisnik = korisnikService.findOne(idTrenera);

        trening.setKorisniktrening(korisnik);
        Trening newTrening = treningService.create(trening);

        TreningNewDTO treningNewDTO = new TreningNewDTO(newTrening.getId(), newTrening.getNaziv(), newTrening.getOpis(), newTrening.getTipTreninga(), newTrening.getTrajanje(), idTrenera);

        return new ResponseEntity<>(treningNewDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreningDTO> updateTrening(@PathVariable Long id, @RequestBody TreningDTO treningDTO) throws Exception {

        Trening trening = new Trening(treningDTO.getNaziv(), treningDTO.getOpis(), treningDTO.getTipTreninga(), treningDTO.getTrajanje());

        trening.setId(id);

        Trening updatedTrening = treningService.update(trening);

        TreningDTO updatedTreningDTO= new TreningDTO(updatedTrening.getId(), updatedTrening.getNaziv(), updatedTrening.getOpis(), updatedTrening.getTipTreninga(), updatedTrening.getTrajanje());

        return new ResponseEntity<>(updatedTreningDTO, HttpStatus.OK);
    }


    //brisanje
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTrening(@PathVariable Long id) {
        // Pozivanjem metode servisa brišemo zaposlenog po ID-ju
        this.treningService.delete(id);

        // Vraćamo odgovor 204 NO_CONTENT koji signalizira uspešno brisanje
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
