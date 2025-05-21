package com.harsh.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
public class UserController {

       @Autowired // wired controller from the UserService
       private UserService userService;   // Use UserService from service layer

       @GetMapping("/api/users")
       public ResponseEntity<List<User>> getAllUsers(){
              // one way to use the response-entity with controlling the http status code
              // return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
              return ResponseEntity.ok(userService.fetchAllUsers());
                 // Use Service Layer
       }
       /*
        @PostMapping("/api/users")
        public List<User> createUser(@RequestBody  User user){    // without using Service Layer
              return userService.addUser(user);
       }
        */

       // fetch user by id
       @GetMapping("/api/users/{id}")
       public  ResponseEntity<User> getUser(@PathVariable Long id) {
//               User user = userService.fetchSingleUserById(id);
//               if(user == null )  return ResponseEntity.notFound().build();
//              return ResponseEntity.ok(user);

              // use java stream
              return userService.fetchSingleUserById(id)
                      .map(ResponseEntity::ok)
                      .orElseGet(()->ResponseEntity.notFound().build());
       }

       // controller to call  update specific user method for service layer
       @PutMapping("/api/users/{id}")
       public  ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user){
              boolean updated = userService.updateUserById(id, user);
              if (updated) {
                     return ResponseEntity.ok("User updated successfully");
              } else {
                     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for the update");
              }
       }


       @PostMapping("/api/users")
       public ResponseEntity<String> createUser(@RequestBody  User user){       // using Service Layer
              userService.addUser(user);
              return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
       }

}
