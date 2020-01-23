package com.ditra.travelagency.core.chambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChambreController {
    @Autowired
    ChambreServices chambreServices;

    @PostMapping("/chambre")
    public ResponseEntity<?> CreatChambre (@RequestBody Chambre chambre)
    {
        return chambreServices.CreatChambre(chambre);
    }

    @GetMapping("/chambres")
    public List<Chambre> GetAllChambre ()
    {
        return chambreServices.GetAllchambre();

    }

}
