package com.ditra.travelagency.core.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {
    @Autowired
    DestinationServices destinationServices;

    @PostMapping("/destination")
    public ResponseEntity<?> creatDestination (@RequestBody Destination destination)
    {
        return destinationServices.creatDestination(destination);
    }


    //retourner tous les données dans la base
    @GetMapping("/destinations")
    public List<Destination> GetAllUser (){
        return destinationServices.GetAllDestination();
    }

    @GetMapping("/destination/{id}")
    public ResponseEntity<?> GetUser (@PathVariable int id){
        return destinationServices.GetDestination(id);

    }

    @PutMapping("destination/{id}")
    public ResponseEntity<?> updatedDestination(@PathVariable int id , @RequestBody Destination updatedDestination ){
        return destinationServices.UpdateDestination(id, updatedDestination);

    }

    @DeleteMapping ("destination/{id}")
    public ResponseEntity<?> DeleteDestination (@PathVariable int id){
        return destinationServices.DeleteDestination(id);

    }









}
