package com.ama_mind.ama_mind_app_DB.controller;

import com.ama_mind.ama_mind_app_DB.model.User;
import com.ama_mind.ama_mind_app_DB.service.UserService;
import lombok.AllArgsConstructor;
import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    
    private final UserService UserService;
    private final String template = "Hello :)  this is VErsion 0.0.1";
   
    
    
    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    
    @GetMapping
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> User =  UserService.getUser();
        return new ResponseEntity<>(User, HttpStatus.OK);
    }

    @GetMapping("/{key}")
    public ResponseEntity<User> getUserByKey(@PathVariable String key){

        User user = UserService.getUserByKey(key);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createCustomer(@Valid @RequestBody User us) {
        User createdUser = UserService.createUser(us);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        UserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
