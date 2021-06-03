package ac.rs.uns.ftn.fitnescentar.contoller;


import ac.rs.uns.ftn.fitnescentar.model.TipTreninga;
import ac.rs.uns.ftn.fitnescentar.model.Trening;
import ac.rs.uns.ftn.fitnescentar.model.dto.TreningDTO;
import ac.rs.uns.ftn.fitnescentar.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/treninzi", produces = MediaType.APPLICATION_JSON_VALUE)
public class TreningController {

    private final TreningService treningService;

    @Autowired
    public TreningController(TreningService treningService){
        this.treningService = treningService;
    }

    //jedan trening
    @GetMapping(value = "/{id}")
    public ResponseEntity<TreningDTO> getTrening(@PathVariable("id") Long id) {

        Trening trening = this.treningService.findOne(id);

        TreningDTO treningDTO = new TreningDTO();
        treningDTO.setId(trening.getId());
        treningDTO.setNaziv(trening.getNaziv());
        treningDTO.setOpis(trening.getOpis());
        treningDTO.setTrajanje(trening.getTrajanje());
        treningDTO.setTipTreninga(trening.getTipTreninga());

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

    //pretraga po nazivu
    @GetMapping(value="/{naziv}")
    public ResponseEntity<List<TreningDTO>> getTreninziByNaziv(@PathVariable("naziv") String naziv) {
        List<Trening> treninziListaNaziv = this.treningService.findByNaziv(naziv);

        List<TreningDTO> treninziNazivDTOS = new ArrayList<>();

        for(Trening trening :  treninziListaNaziv) {
            TreningDTO treningDTO =  new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());

            treninziNazivDTOS.add(treningDTO);
        }

        return new ResponseEntity<>(treninziNazivDTOS, HttpStatus.OK);
    }

    //pretraga po tipu
    @GetMapping(value="/{tipTreninga}")
    public ResponseEntity<List<TreningDTO>> getTreninziByNaziv(@PathVariable("tipTreninga") TipTreninga tipTreninga) {
        List<Trening> treninziListaTip = this.treningService.findByTipTreninga(tipTreninga);

        List<TreningDTO> treninziTipDTOS = new ArrayList<>();

        for(Trening trening :  treninziListaTip) {
            TreningDTO treningDTO =  new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());

            treninziTipDTOS.add(treningDTO);
        }

        return new ResponseEntity<>(treninziTipDTOS, HttpStatus.OK);
    }

    //pretraga po opisu
    @GetMapping(value="/{opis}")
    public ResponseEntity<List<TreningDTO>> getTreninziByOpis(@PathVariable("opis") String opis) {
        List<Trening> treninziListaOpis = this.treningService.findByOpis(opis);

        List<TreningDTO> treninziOpisDTOS = new ArrayList<>();

        for(Trening trening :  treninziListaOpis) {
            TreningDTO treningDTO =  new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTipTreninga(), trening.getTrajanje());

            treninziOpisDTOS.add(treningDTO);
        }

        return new ResponseEntity<>(treninziOpisDTOS, HttpStatus.OK);
    }

    //novi trening
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreningDTO> createTrening(@RequestBody TreningDTO treningDTO) throws Exception {

        Trening trening = new Trening(treningDTO.getNaziv(), treningDTO.getOpis(), treningDTO.getTipTreninga(), treningDTO.getTrajanje());

        Trening newTrening = treningService.create(trening);

        TreningDTO newTreningDTO = new TreningDTO(newTrening.getId(), newTrening.getNaziv(), newTrening.getOpis(), newTrening.getTipTreninga(), newTrening.getTrajanje());

        return new ResponseEntity<>(newTreningDTO, HttpStatus.CREATED);
    }

    //azuriranje
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreningDTO> updateTrening(@PathVariable Long id, @RequestBody TreningDTO treningDTO) throws Exception {

        // Kreiramo objekat klase Trening, tako što za vrednosti atributa uzimamo
        // vrednosti iz primljenog DTO objekta
        Trening trening = new Trening(treningDTO.getNaziv(), treningDTO.getOpis(), treningDTO.getTipTreninga(), treningDTO.getTrajanje());

        // Pošto menjamo postojeći objekat, u zahtevu ćemo dobiti i njegov ID
        trening.setId(id);

        // Pozivanjem metode servisa ažuriramo podatke o zaposlenom
        Trening updatedTrening = treningService.update(trening);

        // Mapiramo ažurirani trening na DTO objekat koji vraćamo kroz body odgovora
        TreningDTO updatedTreningDTO = new TreningDTO(updatedTrening.getId(), updatedTrening.getNaziv(), updatedTrening.getOpis(), updatedTrening.getTipTreninga(), updatedTrening.getTrajanje());

        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o ažuriranom zaposlenom
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
