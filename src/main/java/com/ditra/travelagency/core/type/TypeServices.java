package com.ditra.travelagency.core.type;

import com.ditra.travelagency.utils.ErrorResponseModes;
import com.ditra.travelagency.utils.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServices {

    @Autowired
    TypeRespitory typeRespitory;

    public ResponseEntity<?> CreatType (Type type)
    {

        if (type.getType()==null)
            return new ResponseEntity<>(new ErrorResponseModes("Type Required"), HttpStatus.BAD_REQUEST);
        if (type.getDescription()==null)
            return new ResponseEntity<>(new ErrorResponseModes(" Type description Required"), HttpStatus.BAD_REQUEST);

        Type databasetype = typeRespitory.save(type);
        return  new ResponseEntity<>(databasetype, HttpStatus.OK);
    }

    public List<Type> GetAllType (){
        List<Type> typeList= typeRespitory.findAll();
        return typeList;
    }

    public ResponseEntity<?> GetType ( int id){
        Optional<Type> typeOptional= typeRespitory.findById(id);

        if (!typeOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong type Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        Type type = typeOptional.get();
        return new ResponseEntity<>(type , HttpStatus.OK);

    }

    public ResponseEntity<?> DeleteType (int id){
        Optional<Type> typeOptional= typeRespitory.findById(id);
        if (!typeOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong type Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }
        typeRespitory.deleteById(id);
        ValidationResponse validationResponse = new ValidationResponse("type successfully deleted ");
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);



    }





}



