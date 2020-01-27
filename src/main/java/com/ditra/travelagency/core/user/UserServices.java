package com.ditra.travelagency.core.user;


import com.ditra.travelagency.core.user.models.SignInRequestModel;
import com.ditra.travelagency.core.user.models.SignInResponseModel;
import com.ditra.travelagency.utils.ErrorResponseModes;
import com.ditra.travelagency.utils.JwtUtils;
import com.ditra.travelagency.utils.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    UserRepositroy userRepositroy;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<?> creatUser (User user)
    {
        if (user.getName()==null)
            return new ResponseEntity<>(new ErrorResponseModes("User name Required"), HttpStatus.BAD_REQUEST);
        if (user.getName().length() < 3)
            return new ResponseEntity<>(new ErrorResponseModes("Wrong User name " ),HttpStatus.BAD_REQUEST);
        if (user.getAge()==null)
            return new ResponseEntity<>(new ErrorResponseModes("User age Required"),HttpStatus.BAD_REQUEST);
        if (user.getAge() <= 0)
            return new ResponseEntity<>(new ErrorResponseModes("Wrong User age " ),HttpStatus.BAD_REQUEST);

        String password1= passwordEncoder().encode(user.getPassword());
        user.setPassword(password1);


        User databaseuser = userRepositroy.save(user);
        return  new ResponseEntity<>(databaseuser, HttpStatus.OK);
    }

    public List<User> GetAllUser (){
        List<User> userList= userRepositroy.findAll();
        return userList;
    }

    public ResponseEntity<?> GetUser ( int id){
        Optional<User> userOptional= userRepositroy.findById(id);

        if (!userOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong user Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        User user = userOptional.get();
        return new ResponseEntity<>(user , HttpStatus.OK);

    }

    public ResponseEntity<?> updateUser(int id , User updatedUser ){
        Optional<User> userOptional= userRepositroy.findById(id);


        if (!userOptional.isPresent()){
            ErrorResponseModes errorResponseModes= new ErrorResponseModes("Wrong user Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }

        User databaseUser = userOptional.get();

        if (updatedUser.getName() != null)
        {
            if (updatedUser.getName().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModes("wrong name"), HttpStatus.BAD_REQUEST);
            databaseUser.setName(updatedUser.getName());
        }

        if (updatedUser.getAge()!=null)
        {
            if (updatedUser.getAge() < 0)
                return new ResponseEntity<>(new ErrorResponseModes("wrong age"), HttpStatus.BAD_REQUEST);
            databaseUser.setAge(updatedUser.getAge());
        }

        userRepositroy.save(databaseUser);
        return  new ResponseEntity<>(databaseUser, HttpStatus.OK);

    }


    public ResponseEntity<?> DeleteUser (int id){
        Optional<User> userOptional= userRepositroy.findById(id);
        if (!userOptional.isPresent()) {
            ErrorResponseModes errorResponseModes = new ErrorResponseModes("Wrong user Id");
            return new ResponseEntity<>(errorResponseModes, HttpStatus.BAD_REQUEST);
        }
        userRepositroy.deleteById(id);
        ValidationResponse validationResponse = new ValidationResponse("User successfully deleted ");
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional=userRepositroy.findByUsername(username);


        if (!userOptional.isPresent())
            return null;

        String password=userOptional.get().getPassword();


        return new org.springframework.security.core.userdetails.User(username, password, AuthorityUtils.NO_AUTHORITIES);

    }

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> signin(SignInRequestModel signInRequestModel) {

        String username=signInRequestModel.getUsername();
        String password=signInRequestModel.getPassword();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );

        String token = jwtUtils.genratedtoken(username);
        return  new ResponseEntity<>(new SignInResponseModel(token), HttpStatus.OK);


    }
}
