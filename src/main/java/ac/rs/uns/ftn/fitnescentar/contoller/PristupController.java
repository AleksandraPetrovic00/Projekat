package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Uloge;
import ac.rs.uns.ftn.fitnescentar.model.dto.PristupDTO;
import ac.rs.uns.ftn.fitnescentar.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PristupController {
    private final KorisnikService korisnikService;

    @Autowired
    public PristupController(KorisnikService korisnikService){
        this.korisnikService=korisnikService;
    }

    @PostMapping(value = "/pristup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PristupDTO> pristupKorisnika(@RequestBody PristupDTO pristupDTO) throws Exception{
        if(pristupDTO.getUloga()== Uloge.ADMINISTRATOR){
            if(this.korisnikService.provera(pristupDTO.getId())){
                PristupDTO newPristupDTO = new PristupDTO(pristupDTO.getId(), pristupDTO.getUloga());
                return new ResponseEntity<>(newPristupDTO, HttpStatus.OK);
            }else{
                PristupDTO newPristupDTO = new PristupDTO(null, null);
                return new ResponseEntity<>(newPristupDTO, HttpStatus.FORBIDDEN);
            }
        }else if(pristupDTO.getUloga()==Uloge.CLAN){
            if(this.korisnikService.provera(pristupDTO.getId())) {
                PristupDTO newPristupDTO = new PristupDTO(pristupDTO.getId(), pristupDTO.getUloga());
                return new ResponseEntity<>(newPristupDTO, HttpStatus.OK);
            }else{
                PristupDTO newPristupDTO = new PristupDTO(null, null);
                return new ResponseEntity<>(newPristupDTO, HttpStatus.FORBIDDEN);
            }
        }else if(pristupDTO.getUloga()==Uloge.TRENER){
            if(this.korisnikService.provera(pristupDTO.getId())){
                PristupDTO newPristupDTO = new PristupDTO(pristupDTO.getId(), pristupDTO.getUloga());
                return new ResponseEntity<>(newPristupDTO, HttpStatus.OK);
            }else{
                PristupDTO newPristupDTO = new PristupDTO(null, null);
                return new ResponseEntity<>(newPristupDTO, HttpStatus.FORBIDDEN);
            }
        }else{
            PristupDTO newPristupDTO = new PristupDTO(null, null);
            return  new ResponseEntity<>(newPristupDTO, HttpStatus.FORBIDDEN);
        }
    }

}
