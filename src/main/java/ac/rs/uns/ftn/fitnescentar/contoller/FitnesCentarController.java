package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.FitnesCentar;
import ac.rs.uns.ftn.fitnescentar.model.dto.FitnesCentarDTO;
import ac.rs.uns.ftn.fitnescentar.service.FitnesCentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fitnesCentri")
public class FitnesCentarController {

    private final FitnesCentarService fitnesCentarService;

    @Autowired
    public FitnesCentarController(FitnesCentarService fitnesCentarService) {
        this.fitnesCentarService = fitnesCentarService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnesCentarDTO> getFitnesCentar(@PathVariable("id") Long id) {

        //metoda iz servisa dobavlja fitnes centar po id-ju
        FitnesCentar fitnesCentar = this.fitnesCentarService.findOne(id);

        //pravimo objekat koji cemo vratiti kao odgovor
        FitnesCentarDTO fitnesCentarDTO =  new FitnesCentarDTO();
        fitnesCentarDTO.setId(fitnesCentar.getId());
        fitnesCentarDTO.setNaziv(fitnesCentar.getNaziv());
        fitnesCentarDTO.setAdresa(fitnesCentar.getAdresa());
        fitnesCentarDTO.setBrojTelefonaCentrale(fitnesCentar.getBrojTelefonaCentrale());
        fitnesCentarDTO.setEmail(fitnesCentar.getEmail());

        return new ResponseEntity<>(fitnesCentarDTO, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FitnesCentarDTO>> getFitnesCentri() {

        //dobijamo sve fitnes centre
        List<FitnesCentar> fitnesCentarList = this.fitnesCentarService.findAll();

        //pravimo listu koju cemo vratiti kao odgovor na zahtev
        List<FitnesCentarDTO> fitnesCentarDTOS = new ArrayList<>();

        //kreiramo FitnesCentarDTO za svaki fitnes centar koji je pronasla metoda findAll() i ubacujemo ga u listu fitnes centara
        for(FitnesCentar fitnesCentar : fitnesCentarList) {
            FitnesCentarDTO fitnesCentarDTO =  new FitnesCentarDTO(fitnesCentar.getId(), fitnesCentar.getNaziv(), fitnesCentar.getAdresa(), fitnesCentar.getBrojTelefonaCentrale(), fitnesCentar.getEmail());
            fitnesCentarDTOS.add(fitnesCentarDTO);
        }

        return new ResponseEntity<>(fitnesCentarDTOS, HttpStatus.OK);

    }

    //kreiranje novog fitnes centra
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnesCentarDTO> createFitnesCentar(@RequestBody FitnesCentarDTO fitnesCentarDTO) throws Exception {

        //kreiramo novi objekat uzimanjem podataka iz pristiglog DTO
        FitnesCentar fitnesCentar = new FitnesCentar(fitnesCentarDTO.getNaziv(), fitnesCentarDTO.getAdresa(), fitnesCentarDTO.getBrojTelefonaCentrale(), fitnesCentarDTO.getEmail());

        //kreiramo novi fitnes centar pozivanjem metode servisa
        FitnesCentar newFitnesCentar = fitnesCentarService.create(fitnesCentar);

        //mapiramo novi fitnes centar na DTO objekat koji vracamo kroz body odgovora
        FitnesCentarDTO newFitnesCentarDTO = new FitnesCentarDTO(newFitnesCentar.getId(), newFitnesCentar.getNaziv(), newFitnesCentar.getAdresa(), newFitnesCentar.getBrojTelefonaCentrale(), newFitnesCentar.getEmail());

        //201 CREATED i podaci o novom objektu
        return new ResponseEntity<>(newFitnesCentarDTO, HttpStatus.CREATED);
    }

    //azuriranje
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnesCentarDTO> updateFitnesCentar(@PathVariable Long id, @RequestBody FitnesCentarDTO fitnesCentarDTO) throws Exception {

        // Kreiramo objekat klase Trening, tako što za vrednosti atributa uzimamo
        // vrednosti iz primljenog DTO objekta
        FitnesCentar fitnesCentar = new FitnesCentar(fitnesCentarDTO.getNaziv(), fitnesCentarDTO.getAdresa(), fitnesCentarDTO.getBrojTelefonaCentrale(), fitnesCentarDTO.getEmail());

        // Pošto menjamo postojeći objekat, u zahtevu ćemo dobiti i njegov ID
        fitnesCentar.setId(id);

        // Pozivanjem metode servisa ažuriramo podatke o zaposlenom
        FitnesCentar updatedFitnesCentar = fitnesCentarService.update(fitnesCentar);

        // Mapiramo ažurirani trening na DTO objekat koji vraćamo kroz body odgovora
        FitnesCentarDTO updatedFitnesCentarDTO= new FitnesCentarDTO(updatedFitnesCentar.getId(), updatedFitnesCentar.getNaziv(), updatedFitnesCentar.getAdresa(), updatedFitnesCentar.getBrojTelefonaCentrale(), updatedFitnesCentar.getEmail());

        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o ažuriranom zaposlenom
        return new ResponseEntity<>(updatedFitnesCentarDTO, HttpStatus.OK);
    }

    //brisanje
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFitnesCentar(@PathVariable Long id) {
        // Pozivanjem metode servisa brišemo zaposlenog po ID-ju
        this.fitnesCentarService.delete(id);

        // Vraćamo odgovor 204 NO_CONTENT koji signalizira uspešno brisanje
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
