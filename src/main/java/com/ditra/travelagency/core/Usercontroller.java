package com.ditra.travelagency.core;

import com.ditra.travelagency.utils.ErrorResponseModes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
public class Usercontroller {
    //lien entre app et herbernate
    @Autowired
    UserRepositroy userRepositroy;
    //requete Post pour ajouter un user sous le path Localhost/user
    @PostMapping("/user")
    public ResponseEntity<?> creatUser (@RequestBody User user)
    {
        if (user.getName()==null)
            return new ResponseEntity<>(new ErrorResponseModes("User name Required"),HttpStatus.BAD_REQUEST);
        if (user.getName().length() < 3)
            return new ResponseEntity<>(new ErrorResponseModes("Wrong User name " ),HttpStatus.BAD_REQUEST);
        if (user.getAge()==null)
            return new ResponseEntity<>(new ErrorResponseModes("User age Required"),HttpStatus.BAD_REQUEST);
        if (user.getAge() <= 0)
            return new ResponseEntity<>(new ErrorResponseModes("Wrong User age " ),HttpStatus.BAD_REQUEST);

        User databaseuser = userRepositroy.save(user);
        return  new ResponseEntity<>(databaseuser, HttpStatus.OK);
    }
    //retourner tous les données dans la base
    @GetMapping("/users")
    public List<User> GetAllUser (){
        List<User> userList= userRepositroy.findAll();
        return userList;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> GetUser (@PathVariable int id){
        Optional<User> userOptional= userRepositroy.findById(id);

        if (!userOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong user Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);


        }



        User user = userOptional.get();
        return new ResponseEntity<>(user , HttpStatus.OK);

    }

    @PutMapping("user/{id}")
    public void updateUser(@PathVariable int id , @RequestBody User updatedUser ){
        Optional<User> userOptional= userRepositroy.findById(id);
        User databaseUser = userOptional.get();
        databaseUser.setName(updatedUser.getName());
        databaseUser.setAge(updatedUser.getAge());

        userRepositroy.save(databaseUser);


    }






}
