package it.epicode.beservice.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// con localhost8080 puoi accedere solo dal server, con * da ovunque
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cors")
public class MyCorsController {
    
    @GetMapping("/mycors")
    public String myCors() {    
        return "Hello CORS";
    }
}