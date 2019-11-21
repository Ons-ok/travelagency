package com.ditra.travelagency.core;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void creatUser (@RequestBody User user){
        userRepositroy.save(user);
    }
    //retourner tous les données dans la base
    @GetMapping("/users")
    public List<User> GetAllUser (){
        List<User> userList= userRepositroy.findAll();
        return userList;
    }

    @GetMapping("/user/{id}")
    public User GetUser (@PathVariable int id){
        Optional<User> userOptional= userRepositroy.findById(id);
        User user = userOptional.get();
        return user;

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
