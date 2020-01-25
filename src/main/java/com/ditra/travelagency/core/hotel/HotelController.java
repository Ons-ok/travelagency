package com.ditra.travelagency.core.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelServices hotelServices;
    @PostMapping("/hotel")
    public ResponseEntity<?> creatHotel (@RequestBody Hotel hotel)
    {
        return hotelServices.creatHotel(hotel);
    }

    @GetMapping("/hotels")
    public List<Hotel> GetAllHotel (){
        return  hotelServices.GetAllHotel();
    }

    @GetMapping ("/hotel/{id}")
    public ResponseEntity<?> GetHotel(@PathVariable int id)
    {
        return hotelServices.GetHotel(id);
    }

    @PutMapping ("/hotel/{id}/addRoom")
    public ResponseEntity<?> UpdateHotel(@PathVariable int id, @RequestBody Hotel hotel) {
        return hotelServices.Updatehotel(id, hotel);
    }

    @PutMapping ("hotel/{id}/deleteRoom/{Cid}")
    public ResponseEntity<?> DeleteChambre(@PathVariable int id, @PathVariable int Cid){
        return hotelServices.DeleteChambre(id, Cid);
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<?>DeleteHotel(@PathVariable int id){
        return hotelServices.DeleteHotel(id);
    }




}
