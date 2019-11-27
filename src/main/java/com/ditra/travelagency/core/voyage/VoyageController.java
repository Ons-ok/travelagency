package com.ditra.travelagency.core.voyage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoyageController {
    @Autowired
    VoyageServices voyageServices;


    @PostMapping("/voyage")
     public ResponseEntity<?>creatVoyage(@RequestBody Voyage voyage){return voyageServices.creatVoyage(voyage);}

     @GetMapping("/voyages")
     public List<Voyage> GetAllvoyages (){return voyageServices.GetAllvoyages();}
     @GetMapping("/voyage/{id}")
     public ResponseEntity<?>GetVoyage (@PathVariable int id){return voyageServices.GetVoyage(id);}
     @PutMapping("voyage/{id}")
     public ResponseEntity<?> updateVoyage(@PathVariable int id , @RequestBody Voyage updatedVoyage ){
        return voyageServices.UpdateVoyage(id, updatedVoyage);
    }


     @DeleteMapping ("voyage/{id}")
     public ResponseEntity<?> DeleteUser (@PathVariable int id){
        return voyageServices.DeleteVoyage(id);

    }

}
