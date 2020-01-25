package com.ditra.travelagency.core.hotel;

import com.ditra.travelagency.core.chambre.Chambre;
import com.ditra.travelagency.core.chambre.ChambreRespitory;
import com.ditra.travelagency.utils.ErrorResponseModes;
import com.ditra.travelagency.utils.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServices {
    @Autowired
    HotelRespitory hotelRespitory;
    @Autowired
    ChambreRespitory chambreRespitory;



    public ResponseEntity<?> creatHotel (Hotel hotel)
    {

        if (hotel.getAdresse()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Hotel adresse Required"), HttpStatus.BAD_REQUEST);
        if (hotel.getDescription()==null)
            return new ResponseEntity<>(new ErrorResponseModes(" Hotel description Required"), HttpStatus.BAD_REQUEST);
        if (hotel.getNom()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Hotel name Required"), HttpStatus.BAD_REQUEST);
        if (hotel.getEtoile()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Hotel etoile Required"), HttpStatus.BAD_REQUEST);
        if (hotel.getTelephone()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Hotel telephone Required"), HttpStatus.BAD_REQUEST);



//        for (int i=0 ; i< hotel.getChambres().size(); i++ ) {
//            Optional<Chambre> chambreOptional = chambreRespitory.findById(hotel.getChambres().get(i).getId());
//            if (!chambreOptional.isPresent())
//                return new ResponseEntity<>(new ErrorResponseModes("wrong room id"), HttpStatus.BAD_REQUEST);
//        }

        for(Chambre chambre : hotel.getChambres()) {
            Optional<Chambre> chambreOptional = chambreRespitory.findById(chambre.getId());
            if (!chambreOptional.isPresent())
                return new ResponseEntity<>(new ErrorResponseModes("wrong room id"), HttpStatus.BAD_REQUEST);
        }




        Hotel databasehotel = hotelRespitory.save(hotel);
        return  new ResponseEntity<>(databasehotel, HttpStatus.OK);
    }

    public List<Hotel> GetAllHotel (){
        List<Hotel> hotelList= hotelRespitory.findAll();
        return hotelList;
    }

    public ResponseEntity<?> GetHotel ( int id){
        Optional<Hotel> hotelOptional= hotelRespitory.findById(id);

        if (!hotelOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong hotel Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Hotel hotel = hotelOptional.get();
        return new ResponseEntity<>(hotel , HttpStatus.OK);

    }

    public ResponseEntity<?> DeleteHotel (int id){
        Optional<Hotel> hotelOptional= hotelRespitory.findById(id);
        if (!hotelOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong hotel Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }
        hotelRespitory.deleteById(id);
        ValidationResponse validationResponse = new ValidationResponse("hotel successfully deleted ");
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);



    }


    public ResponseEntity<?> Updatehotel(int id, Hotel updateHotel) {

        Optional<Hotel> hotelOptional=hotelRespitory.findById(id);
        if (!hotelOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong hotel Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Hotel databaseHotel = hotelOptional.get();


        for(Chambre chambre : updateHotel.getChambres()) {

            Optional<Chambre> chambreOptional = chambreRespitory.findById(chambre.getId());
            if (!chambreOptional.isPresent())
                return new ResponseEntity<>(new ErrorResponseModes("wrong room id"), HttpStatus.BAD_REQUEST);

            databaseHotel.addChambre(chambre);
        }

        hotelRespitory.save(databaseHotel);
        ValidationResponse validationResponse = new ValidationResponse("hotel successfully updated ");
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);

    }


    public ResponseEntity<?> DeleteChambre(int id, int Cid) {

        Optional<Hotel> hotelOptional=hotelRespitory.findById(id);

        if (!hotelOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong hotel Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Hotel databaseHotel = hotelOptional.get();
        boolean exist = false;
        for (Chambre chambre :databaseHotel.getChambres() ){
            if (chambre.getId()==Cid)  {
                databaseHotel.getChambres().remove(chambre);
                exist = true;
                break;
            }
        }

        if (exist) {
            hotelRespitory.save(databaseHotel);
            ValidationResponse validationResponse = new ValidationResponse("Room successfully deleted ");
            return new ResponseEntity<>(validationResponse, HttpStatus.OK);
        }
        else {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong room Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }



    }
}
