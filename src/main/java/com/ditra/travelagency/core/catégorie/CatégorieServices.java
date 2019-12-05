package com.ditra.travelagency.core.catégorie;

import com.ditra.travelagency.utils.ErrorResponseModes;
import com.ditra.travelagency.utils.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatégorieServices {
    @Autowired
    CatégorieRespitory catégorieRespitory;


    public ResponseEntity<?> CreatCat (Catégorie catégorie) {

        if (catégorie.getCategorie()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Categorie Required"), HttpStatus.BAD_REQUEST);
        if (catégorie.getDescription()==null)
            return new ResponseEntity<>(new ErrorResponseModes(" categorie description Required"), HttpStatus.BAD_REQUEST);

        Catégorie databasecatégorie = catégorieRespitory.save(catégorie);
        return  new ResponseEntity<>(databasecatégorie, HttpStatus.OK);
    }

    public List<Catégorie> GetAllCat (){
        List<Catégorie> catégorieList= catégorieRespitory.findAll();
        return catégorieList;
    }

    public ResponseEntity<?> GetCat ( int id){
        Optional<Catégorie> catégorieOptional= catégorieRespitory.findById(id);

        if (!catégorieOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong catégorie Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Catégorie catégorie = catégorieOptional.get();
        return new ResponseEntity<>(catégorie , HttpStatus.OK);

    }

    public ResponseEntity<?> DeleteCat (int id){
        Optional<Catégorie> catégorieOptional= catégorieRespitory.findById(id);
        if (!catégorieOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong categorie Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }
        catégorieRespitory.deleteById(id);
        ValidationResponse validationResponse = new ValidationResponse("categorie successfully deleted ");
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);

    }





}
