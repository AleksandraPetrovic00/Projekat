package ac.rs.uns.ftn.fitnescentar.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String odobravannjeZahtevaTrenera(){return "odobravanjeZahtevaTrenera.html";}

    @GetMapping(value = "listaTreninga")
    public String listaTreninga(){return "Treninzi.html";}
}
