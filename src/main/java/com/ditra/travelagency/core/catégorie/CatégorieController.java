package com.ditra.travelagency.core.catégorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatégorieController {

    @Autowired
    CatégorieServices catégorieServices;

    @PostMapping("/cat")
    public ResponseEntity<?> creatCat (@RequestBody Catégorie catégorie)
    {
        return catégorieServices.CreatCat(catégorie);
    }

    @GetMapping("/cats")
    public List<Catégorie> GetAllCat ()
    {
        return catégorieServices.GetAllCat();

    }

    @GetMapping ("/cat/{id}")
    public ResponseEntity<?> Getcat (@PathVariable int id)
    {
        return catégorieServices.GetCat(id);
    }


    @DeleteMapping("/cat/{id}")
    public ResponseEntity<?> Deletecat (@PathVariable int id)
    {
        return catégorieServices.DeleteCat(id);
    }









}
