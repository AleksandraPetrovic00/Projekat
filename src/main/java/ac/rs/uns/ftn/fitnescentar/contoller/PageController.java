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
}
