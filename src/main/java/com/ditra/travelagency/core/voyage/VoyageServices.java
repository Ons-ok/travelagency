package com.ditra.travelagency.core.voyage;

import com.ditra.travelagency.core.destination.Destination;
import com.ditra.travelagency.core.destination.DestinationController;
import com.ditra.travelagency.core.destination.DestinationRespitory;
import com.ditra.travelagency.core.destination.DestinationServices;
import com.ditra.travelagency.core.user.User;
import com.ditra.travelagency.utils.ErrorResponseModes;
import com.ditra.travelagency.utils.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {
    @Autowired
    VoyageRespitory voyageRespitory;
    @Autowired
    DestinationRespitory destinationRespitory;

    public ResponseEntity<?> creatVoyage (Voyage voyage)
    {
        if (voyage.getTitre()==null )
            return new ResponseEntity<>(new ErrorResponseModes("Voyage name Required"), HttpStatus.BAD_REQUEST);
        if (voyage.getDescription()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Description Required"), HttpStatus.BAD_REQUEST);
        if (voyage.getNbPlaces()==0)
            return new ResponseEntity<>(new ErrorResponseModes("Number of places Required"), HttpStatus.BAD_REQUEST);
        if (voyage.getNbPlaces()<0)
            return new ResponseEntity<>(new ErrorResponseModes("wrong nb of places"), HttpStatus.BAD_REQUEST);
        if (voyage.getPrix()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Price Required"), HttpStatus.BAD_REQUEST);
        if (voyage.getDate()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Date Required"), HttpStatus.BAD_REQUEST);

        Optional<Destination> destinationOptional= destinationRespitory.findById(voyage.getDestination().getId());
        if (!destinationOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong destination Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Voyage databasevoyage=voyageRespitory.save(voyage);
        return new ResponseEntity<>(databasevoyage, HttpStatus.OK);


    }
    public List<Voyage> GetAllvoyages (){
        List<Voyage> voyageList= voyageRespitory.findAll();
        return voyageList;
    }
    public ResponseEntity<?>GetVoyage(int id){
        Optional<Voyage> voyageOptional= voyageRespitory.findById(id);

        if (!voyageOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong voyage Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Voyage voyage = voyageOptional.get();
        return new ResponseEntity<>(voyage , HttpStatus.OK);
    }
    public ResponseEntity<?>UpdateVoyage(int id , Voyage updatedVoyage){
        Optional<Voyage> voyageOptional= voyageRespitory.findById(id);


        if (!voyageOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong voyage Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Voyage databaseVoyage = voyageOptional.get();

        if (updatedVoyage.getTitre() != null)
        {
            if (updatedVoyage.getTitre().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModes("wrong name"), HttpStatus.BAD_REQUEST);
            databaseVoyage.setTitre(updatedVoyage.getTitre());
        }

        if (updatedVoyage.getDescription() != null)
        {
            if (updatedVoyage.getDescription().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModes("wrong Description"), HttpStatus.BAD_REQUEST);
            databaseVoyage.setDescription(updatedVoyage.getDescription());
        }
        if (updatedVoyage.getNbPlaces() != null)
        {
            if (updatedVoyage.getNbPlaces() < 0)

            return new ResponseEntity<>(new ErrorResponseModes("wrong number of places"), HttpStatus.BAD_REQUEST);
            databaseVoyage.setNbPlaces(updatedVoyage.getNbPlaces());
        }
        if (updatedVoyage.getPrix() != null)
        {
            if (updatedVoyage.getNbPlaces() < 0)

                return new ResponseEntity<>(new ErrorResponseModes("wrong price"), HttpStatus.BAD_REQUEST);
            databaseVoyage.setPrix(updatedVoyage.getPrix());
        }



        voyageRespitory.save(databaseVoyage);
        return  new ResponseEntity<>(databaseVoyage, HttpStatus.OK);



    }
    public ResponseEntity<?>DeleteVoyage(int id){
        Optional<Voyage> voyageOptional = voyageRespitory.findById(id);
        if (!voyageOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong voyage Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }
        voyageRespitory.deleteById(id);
        ValidationResponse validationResponse = new ValidationResponse("Voyage successfully deleted ");
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);




    }











}
