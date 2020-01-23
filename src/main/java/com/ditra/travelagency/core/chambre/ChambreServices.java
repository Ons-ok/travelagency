package com.ditra.travelagency.core.chambre;


import com.ditra.travelagency.core.catégorie.Catégorie;
import com.ditra.travelagency.core.catégorie.CatégorieRespitory;
import com.ditra.travelagency.core.type.Type;
import com.ditra.travelagency.core.type.TypeRespitory;
import com.ditra.travelagency.utils.ErrorResponseModes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreServices {
@Autowired
    ChambreRespitory chambreRespitory;
@Autowired
    CatégorieRespitory catégorieRespitory;
@Autowired
    TypeRespitory typeRespitory;



public ResponseEntity<?> CreatChambre (Chambre chambre)
{
    Optional <Catégorie> catégorieOptional = catégorieRespitory.findById(chambre.getCatégorie().getId());
    if (!catégorieOptional.isPresent())
        return new ResponseEntity<>(new ErrorResponseModes("catégorie not found"), HttpStatus.BAD_REQUEST);

    Optional <Type> typeOptional = typeRespitory.findById(chambre.getType().getId());
    if (!typeOptional.isPresent())
        return new ResponseEntity<>(new ErrorResponseModes("type not found"), HttpStatus.BAD_REQUEST);

    Optional<Chambre>chambreOptional=chambreRespitory.findByCatégorieAndType(chambre.getCatégorie() , chambre.getType());

    if (chambreOptional.isPresent()){
        ErrorResponseModes errorResponseModes= new ErrorResponseModes("chambre exist");
        return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
    }

    chambre = chambreRespitory.save(chambre);
    return new ResponseEntity<>(chambre, HttpStatus.OK);


}



    public List<Chambre> GetAllchambre (){
        List<Chambre> chambreList= chambreRespitory.findAll();
        return chambreList;
    }



}
