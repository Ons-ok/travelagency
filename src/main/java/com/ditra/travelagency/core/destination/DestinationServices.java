package com.ditra.travelagency.core.destination;

import com.ditra.travelagency.utils.ErrorResponseModes;
import com.ditra.travelagency.utils.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DestinationServices {
    @Autowired
    DestinationRespitory destinationRespitory;

    public ResponseEntity<?> creatDestination(Destination destination) {
        if (destination.getTitre() == null)
            return new ResponseEntity<>(new ErrorResponseModes("Titre Required"), HttpStatus.BAD_REQUEST);
        if (destination.getDescription() == null)
            return new ResponseEntity<>(new ErrorResponseModes("Description Required"), HttpStatus.BAD_REQUEST);


        Destination databasedestination = destinationRespitory.save(destination);
        return new ResponseEntity<>(databasedestination, HttpStatus.OK);
    }
    public List<Destination> GetAllDestination (){
        List<Destination> destinationList= destinationRespitory.findAll();
        return destinationList;
    }
    public ResponseEntity<?>GetDestination(int id){
        Optional<Destination> destinationOptional= destinationRespitory.findById(id);

        if (!destinationOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong destination Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Destination destination = destinationOptional.get();
        return new ResponseEntity<>(destination , HttpStatus.OK);
    }
    public ResponseEntity<?>UpdateDestination(int id , Destination updatedDestination){
        Optional<Destination> destinationOptional= destinationRespitory.findById(id);


        if (!destinationOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong destination Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Destination databaseDestination = destinationOptional.get();

        if (updatedDestination.getTitre() != null)
        {
            if (updatedDestination.getTitre().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModes("wrong name"), HttpStatus.BAD_REQUEST);
            databaseDestination.setTitre(updatedDestination.getTitre());
        }

        if (updatedDestination.getDescription() != null)
        {
            if (updatedDestination.getDescription().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModes("wrong Description"), HttpStatus.BAD_REQUEST);
            databaseDestination.setDescription(updatedDestination.getDescription());
        }


        destinationRespitory.save(databaseDestination);
        return  new ResponseEntity<>(databaseDestination, HttpStatus.OK);



    }
    public ResponseEntity<?>DeleteDestination(int id){
        Optional<Destination> destinationOptional = destinationRespitory.findById(id);
        if (!destinationOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong destination Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }
        destinationRespitory.deleteById(id);
        ValidationResponse validationResponse = new ValidationResponse("Destination successfully deleted ");
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);}












}