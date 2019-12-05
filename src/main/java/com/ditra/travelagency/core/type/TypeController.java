package com.ditra.travelagency.core.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
public class TypeController {
    @Autowired
    TypeServices typeServices;

    @PostMapping("/type")
    public ResponseEntity<?> creatType (@RequestBody Type type)
    {
        return typeServices.CreatType(type);
    }

    @GetMapping("/types")
    public List<Type> GetAlltype ()
    {
        return typeServices.GetAllType();

    }

    @GetMapping ("/type/{id}")
    public ResponseEntity<?> Gettype (@PathVariable int id)
    {
        return typeServices.GetType(id);
    }


    @DeleteMapping("/type/{id}")
    public ResponseEntity<?> Deletetype (@PathVariable int id)
    {
        return typeServices.DeleteType(id);
    }

}
