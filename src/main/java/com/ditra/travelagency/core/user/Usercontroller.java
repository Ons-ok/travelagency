package com.ditra.travelagency.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Usercontroller {
    //lien entre app et herbernate
    @Autowired
    UserServices userServices;
    //requete Post pour ajouter un user sous le path Localhost/user

    @PostMapping("/auth/register")
    public ResponseEntity<?> creatUser (@RequestBody User user)
    {
        return userServices.creatUser(user);
    }


    //retourner tous les données dans la base
    @GetMapping("/users")
    public List<User> GetAllUser (){
       return userServices.GetAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> GetUser (@PathVariable int id){
       return userServices.GetUser(id);

    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id , @RequestBody User updatedUser ){
      return userServices.updateUser(id, updatedUser);

    }

    @DeleteMapping ("user/{id}")
    public ResponseEntity<?> DeleteUser (@PathVariable int id){
        return userServices.DeleteUser(id);

    }





}
