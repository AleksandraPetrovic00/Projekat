package ac.rs.uns.ftn.fitnescentar.contoller;

import ac.rs.uns.ftn.fitnescentar.model.Korisnik;
import ac.rs.uns.ftn.fitnescentar.model.Uloge;
import ac.rs.uns.ftn.fitnescentar.model.dto.KorisnikDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PageController {

    @GetMapping(value = "registracijaClana")
    public String regClana(){
        return "registracijaKorisnika.html";
    }

    @GetMapping(value = "registracijaTrenera")
    public String regTrenera(){
        return "registracijaTrenera.html";
    }

    @GetMapping(value = "addCentar")
    public String addCentar(){
        return "dodavanjeFitnessCentra.html";
    }

    @GetMapping(value = "listaFitnesCentri")
    public String listaFitnesCentara(){return "fitnesCentri.html";}

    @GetMapping(value = "home")
    public String home() {return "home.html";}

    @GetMapping(value = "login")
    public String login(){return "login.html";}

    @GetMapping(value = "odobravanjeZahtevaTrenera")
    public String odobravanjeZahtevaTrenera(@RequestBody KorisnikDTO korisnikDTO)
    {
        Korisnik korisnik = new Korisnik(korisnikDTO.getKorisnickoIme(), korisnikDTO.getLozinka(), korisnikDTO.getIme(), korisnikDTO.getPrezime(), korisnikDTO.getKontaktTelefon(), korisnikDTO.getEmailAdresa(), korisnikDTO.getDatumRodjenja(), korisnikDTO.getUloga(), korisnikDTO.isAktivan());
        if(korisnik.getUloga()==Uloge.ADMINISTRATOR) {
            return "odobravanjeZahtevaTrenera.html";
        }
        else{
            return "error.html";
        }
    }

    @GetMapping(value = "listaTreninga")
    public String listaTreninga(){return "Treninzi.html";}

    @GetMapping(value = "admin")
    public String admin(){return "admin.html";}
}
